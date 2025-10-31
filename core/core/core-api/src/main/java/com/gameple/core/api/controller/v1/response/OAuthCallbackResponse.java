package com.gameple.core.api.controller.v1.response;

import lombok.Builder;
import lombok.Data;

@Data
public class OAuthCallbackResponse {

    private UserTokenInfo tokenInfo;

    @Builder
    public OAuthCallbackResponse(UserTokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
