package com.example.useroauthjwt.entity;

import com.example.useroauthjwt.dto.UserUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;

    @Builder.Default
    private boolean emailCheck = false;

    @Builder.Default
    private boolean deleted = false;

    private String email;
    private ProviderType providerType;
    private String nickname;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void update(UserUpdateRequest userUpdateRequest) {
        this.email = userUpdateRequest.getEmail();
        this.nickname = userUpdateRequest.getNickname();
    }
}
