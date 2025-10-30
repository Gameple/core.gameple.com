package com.gameple.core.api.controller.v1.response;

import lombok.Builder;
import lombok.Data;

@Data
public class OAuthAuthorizeResponse {

    private String callback;

    @Builder
    public OAuthAuthorizeResponse(String callback) {
        this.callback = callback;
    }
}
