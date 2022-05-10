package com.example.useroauthjwt.util;

import com.example.useroauthjwt.entity.User;
import com.example.useroauthjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User findCurrentUser() {
        User user = userRepository.findByUsername(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return user;
    }

}
