package com.javaweb.toeic.service;

public interface EmailService {
    void sendEmailForRegister(String to, String token);
    void sendEmailForForgotPassword(String to, String password);
}
