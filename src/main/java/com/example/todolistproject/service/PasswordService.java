package com.example.todolistproject.service;

import com.example.todolistproject.dto.CreatorRequestDTO;
import com.example.todolistproject.dto.CreatorResponseDTO;
import com.example.todolistproject.dto.SessionDTO;

public interface PasswordService {

    void register(CreatorRequestDTO request);
    CreatorResponseDTO login(CreatorRequestDTO request);
    void logout(SessionDTO request);
}
