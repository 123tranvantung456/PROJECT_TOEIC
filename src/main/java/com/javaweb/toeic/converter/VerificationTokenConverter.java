package com.javaweb.toeic.converter;

import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.entity.VerificationEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class VerificationTokenConverter {
    @Value("${confirm.expired.minutes}")
    private int confirmExpiredMinutes;
    public VerificationEntity toVerificationEntity (UserEntity userEntity) {
        VerificationEntity verificationEntity = new VerificationEntity();
        verificationEntity.setUser(userEntity);
        verificationEntity.setExpiryDate(LocalDateTime.now().plusMinutes(confirmExpiredMinutes));
        verificationEntity.setToken(UUID.randomUUID().toString());
        return verificationEntity;
    }
}
