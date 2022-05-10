package com.example.useroauthjwt.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    private String grantType;

    private String accessToken;

    private String refreshToken;

    private long accessTokenExpireDate;

    private long refreshTokenExpireDate;

}
