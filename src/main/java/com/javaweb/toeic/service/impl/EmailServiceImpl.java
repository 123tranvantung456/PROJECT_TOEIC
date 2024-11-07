package com.javaweb.toeic.service.impl;

import com.javaweb.toeic.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Override
    @Async
    public void sendEmailForRegister(String to, String token) {
        final String subject = "Xác nhận đăng kí tài khoản";
        final String confirmationUrl = "http://localhost:6969/auth/confirm-register/" + token;
        String message = "Nhấn vào liên kết dưới đây để xác nhận tài khoản của bạn (lưu ý đường link này chỉ có hiệu lực trong vòng 3 phút):\n" + confirmationUrl;
        send(to, subject, message);
    }

    @Override
    @Async
    public void sendEmailForForgotPassword(String to, String password) {
        final String subject = "Mật khẩu mới";
        final String message = "Mật khẩu mới của bạn là : " + password;
        send(to, subject, message);
    }

    private void send(String to, String subject, String message){
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(to); // Địa chỉ email người nhận
            helper.setSubject(subject); // Tiêu đề email
            helper.setText(message, true); // Nội dung email

            mailSender.send(mimeMessage);// Gửi email
            System.out.println("ok");
        } catch (MessagingException e) {
            throw new IllegalStateException("Không thể gửi email xác nhận");
        }
    }
}
