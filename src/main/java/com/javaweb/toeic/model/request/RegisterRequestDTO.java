package com.javaweb.toeic.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    @NotBlank(message = "username is not null")
    @Size(min = 3, max = 20, message = "username must be between 3 and 20 character")
    private String username;
    @NotBlank(message = "password is not null")
    @Size(min = 6, max = 30, message = "password must be between 6 and 30 character")
    private String password;
    @NotBlank(message = "email is not null")
    private String email;
}
