package com.example.todolistproject.configuration;

import com.example.todolistproject.dto.SessionDTO;
import com.example.todolistproject.entity.CreatorSession;
import com.example.todolistproject.entity.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final SessionRepository sessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        // header -> sessionId
        if (header == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SessionDTO sessionDTO = SessionDTO.builder()
                .sessionId(header)
                .build();
        CreatorSession session = sessionRepository.findBySessionId(header);
        if (session == null || session.getExpirationTime().isBefore(LocalDateTime.now())) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication key = new UsernamePasswordAuthenticationToken(
                sessionDTO,
                null,
                new ArrayList<>()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(key);

        filterChain.doFilter(request, response);
    }
}
