package com.example.practicesecurity.service;

import com.example.practicesecurity.dto.JoinDTO;
import com.example.practicesecurity.entity.UserEntity;
import com.example.practicesecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    public void joinProcess(JoinDTO joinDTO){

        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(joinDTO.getPassword());
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
