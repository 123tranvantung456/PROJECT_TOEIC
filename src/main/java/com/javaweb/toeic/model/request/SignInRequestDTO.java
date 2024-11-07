package com.javaweb.toeic.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDTO {
    @NotBlank(message = "username must be not null")
    private String username;
    @NotBlank(message = "password must be not null")
    private String password;
    // MORE OVER : PLATFORM : La 1 enum cho biet truy cap tu nen tang nao
    // deviceToken : nhan notify, version : dung trong mobile

}
