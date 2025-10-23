package com.gameple.core.api.controller.v1.response;

import lombok.Builder;
import lombok.Data;

@Data
public class AuthenticateUserResponse {

    private final String accessToken;

    @Builder
    public AuthenticateUserResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
