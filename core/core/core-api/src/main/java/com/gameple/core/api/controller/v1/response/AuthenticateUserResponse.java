package com.gameple.core.api.controller.v1.response;

import lombok.Builder;
import lombok.Data;

@Data
public class AuthenticateUserResponse {

    private final UserTokenInfo userTokenInfo;

    @Builder
    public AuthenticateUserResponse(UserTokenInfo userTokenInfo) {
        this.userTokenInfo = userTokenInfo;
    }
}
