package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.enums.TokenType;
import com.javaweb.toeic.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.timeout}")
    private String jwtTimeout;

    @Value("${jwt.timeoutRefresh}")
    private String jwtTimeoutRefresh;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.refreshKey}")
    private String refreshKey;

    private final Set<String> blackListToken = new HashSet<>();

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }

    @Override
    public String extractUsername(String token, TokenType tokenType) {
        return extractClaims(token, tokenType, Claims::getSubject);
    }

    @Override
    public boolean isValid(String token, TokenType tokenType, UserDetails userDetails) {
        final String username = extractUsername(token, tokenType);
        return username.equals(userDetails.getUsername())  && !isTokenExpired(token, tokenType) && !isTokenBlackListed(token);
    }

    @Override
    public void addBlackList(String token) {
        System.out.println("blackList: ");
        for (String item : blackListToken) {
            System.out.println(item);
        }
        blackListToken.add(token);
    }

    @Override
    public boolean isTokenBlackListed(String token) {
        return blackListToken.contains(token);
    }

    // hàm để tao và return về token cho client
    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtTimeout)))
                .signWith(getKey(TokenType.ACCESS_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtTimeoutRefresh)))
                .signWith(getKey(TokenType.REFRESH_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey(TokenType type) {
        byte[] keyBytes = null;
        if (type.equals(TokenType.ACCESS_TOKEN)){
            keyBytes = Decoders.BASE64.decode(secretKey);
        }
        else if (type.equals(TokenType.REFRESH_TOKEN)){
            keyBytes = Decoders.BASE64.decode(refreshKey);
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaims(String token, TokenType tokenType , Function<Claims, T> claimsResolver) {
        final Claims claims = extraAllClaims(token, tokenType);
        return claimsResolver.apply(claims);
    }

    private Claims extraAllClaims(String token, TokenType tokenType) {
        return Jwts.
                parserBuilder()
                .setSigningKey(getKey(tokenType)).build().parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token, TokenType tokenType) {
        return extractExpiration(token, tokenType).before(new Date());
    }

    private Date extractExpiration(String token, TokenType tokenType) {
        return extractClaims(token, tokenType, Claims::getExpiration);
    }
}

// claims : khong muon hien thi ra ngoai ma chi hien thi o dag ma hoa
// setIssuedAt : ngay tao
// setExpiration : ngay het han
// 1000 * 60 * 60 * 24 : tuong ung 1 ngay.
// token het han => 403
// nen tach ra refresh và access thành đc mã hóa khác nhau để đảm bảo mục đích sử dụng khác nhau