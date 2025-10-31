package com.gameple.core.api.controller.v1.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
public class UserTokenInfo {

    private String accessToken;

    private String refreshToken;

    private Instant refreshTokenExpiryAt;

    @Builder
    public UserTokenInfo(String accessToken, String refreshToken, Instant refreshTokenExpiryAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiryAt = refreshTokenExpiryAt;
    }
}
