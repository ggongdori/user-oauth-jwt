package com.example.useroauthjwt.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    private String nickname;
    private String email;
}
