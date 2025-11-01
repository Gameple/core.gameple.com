package com.gameple.core.api.controller.v1.response;

import lombok.Builder;
import lombok.Data;

@Data
public class TokenRefreshResponse {

    private String accessToken;

    @Builder
    public TokenRefreshResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
