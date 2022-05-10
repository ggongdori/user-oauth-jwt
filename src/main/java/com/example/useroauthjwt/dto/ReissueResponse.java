package com.example.useroauthjwt.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReissueResponse {

    private String grantType;

    private String accessToken;
    private Long accessTokenExpireDate;

    private Long refreshTokenExpireDate;

    public static ReissueResponse of(TokenResponse tokenResponse) {
        return ReissueResponse.builder()
                .grantType(tokenResponse.getGrantType())
                .accessToken(tokenResponse.getAccessToken())
                .accessTokenExpireDate(tokenResponse.getAccessTokenExpireDate())
                .refreshTokenExpireDate(tokenResponse.getRefreshTokenExpireDate())
                .build();
    }

}
