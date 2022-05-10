package com.example.useroauthjwt.dto;

import com.example.useroauthjwt.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String nickname;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }

}
