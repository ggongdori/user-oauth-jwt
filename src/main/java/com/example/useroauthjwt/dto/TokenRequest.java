package com.example.useroauthjwt.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;

}
