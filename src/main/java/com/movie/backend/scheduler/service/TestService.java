package com.movie.backend.scheduler.service;

import org.springframework.stereotype.Service;

import com.movie.backend.model.entity.UserEntity;
import com.movie.backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
    private final UserRepository userRepository;
    @Transactional
    public void saveUser(){
        userRepository.save(UserEntity.create("testee"));
    }
    
}
