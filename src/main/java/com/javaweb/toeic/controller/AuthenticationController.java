package com.javaweb.toeic.controller;

import com.javaweb.toeic.enums.Role;
import com.javaweb.toeic.exception.AlreadyExistsException;
import com.javaweb.toeic.exception.DataNotFoundException;
import com.javaweb.toeic.exception.ExpiredToken;
import com.javaweb.toeic.exception.PendingUserExistsException;
import com.javaweb.toeic.model.request.RegisterRequestDTO;
import com.javaweb.toeic.model.request.SignInRequestDTO;
import com.javaweb.toeic.model.response.TokenResponse;
import com.javaweb.toeic.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequestDTO request) {
        try {
            return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
        }
        catch (PendingUserExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(HttpServletRequest  request) {
        return new ResponseEntity<>(authenticationService.refresh(request), HttpStatus.OK);
    }
    @PostMapping("/signOut")
    public ResponseEntity<String> signOut(HttpServletRequest request) {
        return new ResponseEntity<>(authenticationService.signOut(request), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        try {
            return new ResponseEntity<>(authenticationService.register(registerRequestDTO, Role.STUDENT), HttpStatus.OK);
        }
        catch (AlreadyExistsException  e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (RoleNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (PendingUserExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/resend-email/{email}")
    public ResponseEntity<String> resendEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(authenticationService.resendEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/confirm-register/{token}")
    public ResponseEntity<String> ConfirmRegister(@PathVariable String token){
        try {
            return new ResponseEntity<>(authenticationService.confirm(token), HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (ExpiredToken e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email) {
        try {
            return new ResponseEntity<>(authenticationService.forgotPassword(email), HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}