package com.example.useroauthjwt.repository;

import com.example.useroauthjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByEmailAndEmailCheckIsTrue(String email);
    Optional<User> findByIdAndDeletedIsFalse(Long id);
}
