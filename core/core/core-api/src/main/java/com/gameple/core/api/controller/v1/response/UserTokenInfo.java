package com.gameple.core.api.controller.v1.response;

import lombok.Builder;
import lombok.Data;

@Data
public class UserTokenInfo {

    private String accessToken;

    private String refreshToken;

    @Builder
    public UserTokenInfo(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
