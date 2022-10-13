package com.example.todolistproject.service.impl;

import com.example.todolistproject.configuration.SessionConfiguration;
import com.example.todolistproject.dto.*;
import com.example.todolistproject.entity.Creator;
import com.example.todolistproject.entity.CreatorPassword;
import com.example.todolistproject.entity.CreatorSession;
import com.example.todolistproject.entity.repository.CreatorRepository;
import com.example.todolistproject.entity.repository.PasswordRepository;
import com.example.todolistproject.entity.repository.SessionRepository;
import com.example.todolistproject.service.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final SessionConfiguration config;
    private final PasswordRepository passwordRepository;
    private final SessionRepository sessionRepository;
    private final CreatorRepository creatorRepository;


    public void register(CreatorRequestDTO request) {
        if (creatorRepository.existsByName(request.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Creator blogUser = ConverterDTO.convertDTOToCreator(request);
        creatorRepository.save(blogUser);
        generateAndSavePassword(blogUser, request.getPassword());
    }

    public CreatorResponseDTO login(CreatorRequestDTO request) {
        Creator account = getMatchedAccount(request.getName(), request.getPassword());
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        CreatorSession session = CreatorSession.builder()
                .creator(account)
                .sessionId(UUID.randomUUID().toString())
                .build();
        session.setExpirationTime(config.getExpirationPeriod());

        sessionRepository.save(session);

        return CreatorResponseDTO.builder()
                .sessionId(session.getSessionId())
                .build();

    }

    public void generateAndSavePassword(Creator creator, String password) {
        String salt = BCrypt.gensalt();
        String encryptedPassword = BCrypt.hashpw(password, salt);

        CreatorPassword accountPassword = CreatorPassword.builder()
                .creator(creator)
                .salt(salt)
                .passwordHash(encryptedPassword)
                .build();

        passwordRepository.save(accountPassword);
    }

    public Creator getMatchedAccount(String name, String password) {
        Creator creator = creatorRepository.findByName(name);

        if (creator == null) {
            return null;
        }
        CreatorPassword accountPassword = passwordRepository.findByCreator(creator);

        var actualPasswordHash = BCrypt.hashpw(password, accountPassword.getSalt());

        return actualPasswordHash.equals(accountPassword.getPasswordHash()) ? creator : null;
    }


    @Transactional
    public void logout(SessionDTO request) {
        sessionRepository.deleteBySessionId(request.getSessionId());

    }
}
