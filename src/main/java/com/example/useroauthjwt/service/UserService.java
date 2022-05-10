package com.example.useroauthjwt.service;

import com.example.useroauthjwt.dto.UserUpdateRequest;
import com.example.useroauthjwt.entity.User;
import com.example.useroauthjwt.repository.UserRepository;
import com.example.useroauthjwt.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserUtil userUtil;

    /**
     * 전달받은 accessToken을 통해 현재 유저를 반환합니다.
     *
     * @return User 현재 유저
     */
    public User getUser() {
        return userUtil.findCurrentUser();
    }

    public Boolean isDuplicateEmail(String userEmail) {
        if (userRepository.existsByEmailAndEmailCheckIsTrue(userEmail)) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        } else {
            return false;
        }
    }

    @Transactional
    public User update(UserUpdateRequest userUpdateRequest) {
        User user = userUtil.findCurrentUser();
        // 닉네임 중복 확인
        if (userRepository.existsByNickname(userUpdateRequest.getNickname())) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        // 이메일 중복 확인
        if (userRepository.existsByEmail(userUpdateRequest.getEmail())) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }
        user.update(userUpdateRequest);
        return user;
    }

}