package com.example.useroauthjwt.repository;

import com.example.useroauthjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
}
