package com.movie.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.backend.model.entity.UserEntity;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByDeletedAtIsNull();
    boolean existsByEmailAndDeletedAtIsNull(String email);
}
