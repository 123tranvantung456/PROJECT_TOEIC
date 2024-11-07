package com.javaweb.toeic.service;

import com.javaweb.toeic.enums.TokenType;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(UserDetails userDetails);
    String extractUsername(String token, TokenType tokenType);
    boolean isValid(String token, TokenType tokenType, UserDetails userDetails);
    void addBlackList(String token);
    boolean isTokenBlackListed(String token);
}
