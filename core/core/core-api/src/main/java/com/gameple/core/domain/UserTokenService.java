package com.gameple.core.domain;

import com.gameple.core.entity.RefreshToken;
import com.gameple.core.entity.User;
import com.gameple.core.helper.jwt.JwtUtil;
import com.gameple.core.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserTokenService {

    private final JwtUtil jwtUtil;

    private final RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(User user) {
        return jwtUtil.generateToken(user.getEmail());
    }

    public String generateRefreshToken(User user) {
        String refreshToken = UUID.randomUUID().toString();

        RefreshToken tokenEntity = RefreshToken.builder()
                .userId(user.getId())
                .token(refreshToken)
                .build();

        refreshTokenRepository.save(tokenEntity);
        return refreshToken;
    }
}
