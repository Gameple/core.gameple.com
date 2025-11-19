package com.gameple.core.api.controller.v1;

import com.gameple.core.api.controller.v1.request.OAuthAuthorizeRequest;
import com.gameple.core.api.controller.v1.request.OAuthCallbackRequest;
import com.gameple.core.api.controller.v1.request.TokenRefreshRequest;
import com.gameple.core.api.controller.v1.response.*;

import com.gameple.core.domain.AuthService;
import com.gameple.core.helper.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/oauth/token-refresh")
    public ApiResponse<TokenRefreshResponse> refreshUserToken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {
        String accessToken = authService.refreshUserToken(tokenRefreshRequest.getRefreshToken());
        return ApiResponse.success(TokenRefreshResponse.builder()
                .accessToken(accessToken)
                .build());
    }

    @PostMapping("/oauth/authorize")
    public ApiResponse<OAuthAuthorizeResponse> oAuthAuthorize(@Valid @RequestBody OAuthAuthorizeRequest oAuthAuthorizeRequest) {
        String callback = authService.oAuthAuthorize(oAuthAuthorizeRequest);
        return ApiResponse.success(OAuthAuthorizeResponse.builder()
                .callback(callback)
                .build());
    }

    @PostMapping("/oauth/callback")
    public ApiResponse<OAuthCallbackResponse> oAuthCallback(@Valid @RequestBody OAuthCallbackRequest oAuthCallbackRequest) {
        UserTokenInfo userTokenInfo = authService.findUserTokens(oAuthCallbackRequest);
        return ApiResponse.success(OAuthCallbackResponse.builder()
                .tokenInfo(userTokenInfo)
                .build());
    }
}
