package com.javaweb.toeic.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TokenResponse implements Serializable {
    private String accessToken; // để truy cập vào hệ thống
    private String refreshToken; // để làm mới token
    private Long userId;
}

// thường thì access hết hạn thì login lại, 1 số dự án chỉ 5 10 p token hết hạn, người dùng muốn dùng thì phải login lại
// nhưng đối với thương mại điện tử thì thường để 1 2 h sẽ hết session, vd đang dùng mà ht thì sẽ đc sử dụng để gửi ngầm
// refresh để có token mới mà k cần phải login