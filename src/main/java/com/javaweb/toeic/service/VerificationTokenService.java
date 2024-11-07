package com.javaweb.toeic.service;

import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.entity.VerificationEntity;

public interface VerificationTokenService {
    VerificationEntity CreateToken(UserEntity userEntity);
}
