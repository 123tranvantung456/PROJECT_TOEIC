package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.VerificationTokenConverter;
import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.entity.VerificationEntity;
import com.javaweb.toeic.repository.VerificationTokenRepository;
import com.javaweb.toeic.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenConverter verificationTokenConverter;
    private final VerificationTokenRepository verificationTokenRepository;
    @Override
    public VerificationEntity CreateToken(UserEntity userEntity) {
        return verificationTokenRepository.save(verificationTokenConverter.toVerificationEntity(userEntity));
    }
}
