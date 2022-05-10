package com.example.useroauthjwt.service;

import com.example.useroauthjwt.dto.TokenResponse;
import com.example.useroauthjwt.jwt.JwtFilter;
import com.example.useroauthjwt.jwt.JwtProvider;
import com.example.useroauthjwt.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final JwtProvider jwtProvider;
    private final JwtFilter jwtFilter;
    private final RedisTemplate redisTemplate;

    private final static String REFRESH_TOKEN = "refresh_token";

    /**
     * 헤더에 담긴 AccessToken과 쿠키로 담은 RefreshToken을 검증하여
     * AccessToken과 RefreshToken을 재발급 합니다.
     *
     * @return TokenResponse 토큰 반환 정보
     */
    @Transactional
    public TokenResponse reissue(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = jwtFilter.getAccessToken(request);

        if (!jwtProvider.ExpiredToken(accessToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        String userId = jwtProvider.parseClaims(accessToken).getSubject();
        // redis로 부터 refreshToken 가져오기
        String redisToken = (String) redisTemplate.opsForValue().get("RT:" + userId);

        // 쿠키로 부터 refreshToken 가져오기
        String refreshToken = CookieUtil.getCookie(request, REFRESH_TOKEN)
                .map(Cookie::getValue)
                .orElse((null));

        if (ObjectUtils.isEmpty(redisToken) || !redisToken.equals(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 리프레쉬 토큰입니다.");
        }

        TokenResponse tokenResponse = jwtProvider.createTokenResponse(userId);
        redisTemplate.opsForValue().set("RT:" + userId, tokenResponse.getRefreshToken(), tokenResponse.getRefreshTokenExpireDate(), TimeUnit.MILLISECONDS);

        int cookieMaxAge = (int) tokenResponse.getRefreshTokenExpireDate() / 60;
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtil.addCookie(response, REFRESH_TOKEN, tokenResponse.getRefreshToken(), cookieMaxAge);

        return tokenResponse;
    }

    public Boolean logout(HttpServletRequest request) {
        String accessToken = jwtFilter.getAccessToken(request);

        if (!jwtProvider.validationToken(accessToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        String userId = jwtProvider.parseClaims(accessToken).getSubject();

        if (redisTemplate.opsForValue().get("RT:" + userId) != null) {
            redisTemplate.delete("RT:" + userId);
        }

        Long expiration = jwtProvider.getExpiration(accessToken);
        redisTemplate.opsForValue().set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);

        return true;
    }

}
