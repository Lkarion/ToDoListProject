package com.example.todolistproject.controller;

import com.example.todolistproject.dto.CreatorRequestDTO;
import com.example.todolistproject.dto.CreatorResponseDTO;
import com.example.todolistproject.dto.SessionDTO;
import com.example.todolistproject.service.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PasswordController {

    private final PasswordService passwordService;

    /**
     * Authenticated users
     * /tasks POST (create a task)
     * /tasks GET (see all tasks)
     * /tasks/{id} GET (see task by ID)
     * /tasks/{id}/complete (set isDone to true)
     * 1. It is strictly prohibited to use creatorId in any DTO.
     *
     *     CreatorId should be picked from session, using `@AuthenticatedPrincipal` in `@Controller`
     *
     * 2. CreatorSession has expiration time
     *
     *     Expiration time should be set in `application.properties`
     *
     *     Use `@Value` annotation to pick the value.
     *
     *     Filter logic should ignore sessions, beyond expiration time
     *
     *     I.e., if expiration time is set to 11:24 and it is 12:01 now → the session should be ignored
     */

    @PostMapping("/registration")
    public void register(@RequestBody CreatorRequestDTO request) {
        passwordService.register(request);
    }
    @PostMapping("/login")
    public CreatorResponseDTO login(@RequestBody CreatorRequestDTO request) {
        return passwordService.login(request);
    }
    @PutMapping("/session_logout")
    public void logout(@AuthenticationPrincipal SessionDTO request) {
        passwordService.logout(request);
    }
}
