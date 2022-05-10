package com.example.useroauthjwt.service;

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


}