package com.javaweb.toeic.service;

import com.javaweb.toeic.enums.Role;
import com.javaweb.toeic.exception.AlreadyExistsException;
import com.javaweb.toeic.exception.DataNotFoundException;
import com.javaweb.toeic.exception.ExpiredToken;
import com.javaweb.toeic.exception.PendingUserExistsException;
import com.javaweb.toeic.model.request.RegisterRequestDTO;
import com.javaweb.toeic.model.request.SignInRequestDTO;
import com.javaweb.toeic.model.response.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;

import javax.management.relation.RoleNotFoundException;

public interface AuthenticationService {
    TokenResponse authenticate(SignInRequestDTO signInRequestDTO);

    TokenResponse refresh(HttpServletRequest httpServletRequest);

    String signOut(HttpServletRequest request);

    String register(RegisterRequestDTO registerRequestDTO, Role role) throws AlreadyExistsException, RoleNotFoundException, PendingUserExistsException;

    String confirm(String token) throws DataNotFoundException, AlreadyExistsException, ExpiredToken;

    String resendEmail(String email) throws DataNotFoundException, AlreadyExistsException;

    String forgotPassword(String email) throws DataNotFoundException;
}
