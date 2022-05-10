package com.example.useroauthjwt.service;

import com.example.useroauthjwt.repository.UserRepository;
import com.example.useroauthjwt.entity.CustomUserDetails;
import com.example.useroauthjwt.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("customUserDetailsService");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find username."));

        return CustomUserDetails.create(user);
    }

}
