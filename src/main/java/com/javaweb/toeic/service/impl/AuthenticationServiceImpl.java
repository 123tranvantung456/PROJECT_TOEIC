package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.converter.UserConverter;
import com.javaweb.toeic.entity.RoleEntity;
import com.javaweb.toeic.entity.UserEntity;
import com.javaweb.toeic.entity.VerificationEntity;
import com.javaweb.toeic.enums.Role;
import com.javaweb.toeic.enums.Status;
import com.javaweb.toeic.enums.TokenType;
import com.javaweb.toeic.exception.*;
import com.javaweb.toeic.model.request.RegisterRequestDTO;
import com.javaweb.toeic.model.request.SignInRequestDTO;
import com.javaweb.toeic.model.response.TokenResponse;
import com.javaweb.toeic.repository.RoleRepository;
import com.javaweb.toeic.repository.UserRepository;
import com.javaweb.toeic.repository.VerificationTokenRepository;
import com.javaweb.toeic.service.*;
import com.javaweb.toeic.utils.PasswordUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;
    private final UserService userService;
    private final PasswordUtil passwordUtil;
    @Value("${confirm.expired.minutes}")
    private int confirmExpiredMinutes;

    @Override
    public TokenResponse authenticate(SignInRequestDTO signInRequestDTO) {
        // Tìm người dùng từ cơ sở dữ liệu
        var user = userRepository.findByUsername(signInRequestDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Invalid username or password"));
        // Xác thực username và password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDTO.getUsername(),
                        signInRequestDTO.getPassword(), user.getAuthorities())
        );

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        // Trả về TokenResponse
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
    }

    @Override
    public TokenResponse refresh(HttpServletRequest request) {
        String refreshToken = request.getHeader("x-token"); // ?????????????????????????????????????????
        if(StringUtils.isBlank(refreshToken)) {
            throw new InvalidDataException("Token must not be blank");
        }

        // extract user from token
        final String username = jwtService.extractUsername(refreshToken, TokenType.REFRESH_TOKEN);
        // check it into db
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        if (!jwtService.isValid(refreshToken, TokenType.REFRESH_TOKEN, user)){
            throw new InvalidDataException("Invalid token"); // ko hop le ve mat thoi gian hay secretKey
        }
        // tao moi accesstoken va giu nguyen refresh token
        String accessToken = jwtService.generateToken(user);
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
    }

    @Override
    public String signOut(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        jwtService.addBlackList(token);
        return "sign out success";
    }

    @Override
    @Transactional
    public String register(RegisterRequestDTO registerRequestDTO, Role role) throws AlreadyExistsException, RoleNotFoundException, PendingUserExistsException {
        if (userRepository.existsByUsernameAndEmailAndStatus(registerRequestDTO.getUsername(), registerRequestDTO.getEmail(), Status.PENDING)){
            throw new PendingUserExistsException("User already registered but pending verification");
        }
        if (userRepository.existsByUsernameOrEmail(registerRequestDTO.getUsername(), registerRequestDTO.getEmail())){
            throw new AlreadyExistsException("User already exists");
        }

        UserEntity userEntity = userConverter.toUserEntity(registerRequestDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setStatus(Status.PENDING);
        RoleEntity roleEntity = roleRepository.findByCode(role)
                .orElseThrow( () -> new RoleNotFoundException("Role not found"));
        userEntity.setRole(roleEntity);
        emailService.sendEmailForRegister(userEntity.getEmail(),
                verificationTokenService.CreateToken(userRepository.save(userEntity)).getToken());
        return "check email";
    }

    @Override
    public String confirm(String token) throws DataNotFoundException, AlreadyExistsException, ExpiredToken {
        VerificationEntity verification = verificationTokenRepository.findByToken(token).orElseThrow(
                () -> new DataNotFoundException("Verification token not found")
        );
        if (verification.getUser() == null) throw new DataNotFoundException("user not found");
        if(verification.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new ExpiredToken("expired token");
        }
        if(verification.getUser().getStatus() != Status.PENDING) {
            throw new AlreadyExistsException("already confirm");
        }
        userService.setStatus(verification.getUser(), Status.ACTIVE);
        return "confirm success";
    }

    @Override
    public String resendEmail(String email) throws DataNotFoundException, AlreadyExistsException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new DataNotFoundException("not found user")
        );
        if(userEntity.getStatus() != Status.PENDING) {
            throw new AlreadyExistsException("acc already confirm");
        }
        VerificationEntity verification = userEntity.getVerification();
        if(verification == null){
            emailService.sendEmailForRegister(email, verificationTokenService.CreateToken(userEntity).getToken());
        } else {
            verification.setToken(UUID.randomUUID().toString());
            verification.setExpiryDate(LocalDateTime.now().plusMinutes(confirmExpiredMinutes));
            emailService.sendEmailForRegister(email, verificationTokenRepository.save(verification).getToken());
        }
        return "resend email success";
    }

    @Override
    public String forgotPassword(String email) throws DataNotFoundException {
        var user = userRepository.findByEmail(email).orElseThrow(
                () -> new DataNotFoundException("not found user")
        );
        String newPassword = passwordUtil.generateRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        emailService.sendEmailForForgotPassword(email, newPassword);
        return "check email";
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

// kiểu var : được trình bien dịch xác định kiểu thực sự khi biên dịch, ví dụ var number = 5; // Suy ra kiểu là int