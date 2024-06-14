package com.movie.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.model.entity.UserEntity;
import com.movie.backend.repository.UserRepository;
import com.movie.backend.service.notice.AlreadyRegisterUserException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    
    @Transactional
    public void register(String email) {
        if (userRepository.existsByEmailAndDeletedAtIsNull(email)) {
            throw new AlreadyRegisterUserException(email);
        }

        userRepository.save(UserEntity.create(email));
    }
    
}
