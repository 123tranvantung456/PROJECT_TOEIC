package com.javaweb.toeic.repository;

import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.entity.VerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationEntity, Long> {
    Optional<VerificationEntity> findByToken(String token);
}
