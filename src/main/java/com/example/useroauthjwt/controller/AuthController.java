package com.example.useroauthjwt.controller;

import com.example.useroauthjwt.dto.ReissueResponse;
import com.example.useroauthjwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/reissue")
    public ResponseEntity<ReissueResponse> reissue(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(ReissueResponse.of(authService.reissue(request, response)));
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpServletRequest request) {
        return ResponseEntity.ok(authService.logout(request));
    }

}
