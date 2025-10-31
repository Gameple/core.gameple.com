package com.gameple.core.api.controller.v1;

import com.gameple.core.api.controller.v1.request.OAuthAuthorizeRequest;
import com.gameple.core.api.controller.v1.request.CreateUserRequest;
import com.gameple.core.api.controller.v1.request.OAuthCallbackRequest;
import com.gameple.core.api.controller.v1.response.CreateUserResponse;
import com.gameple.core.api.controller.v1.response.OAuthAuthorizeResponse;

import com.gameple.core.api.controller.v1.response.OAuthCallbackResponse;
import com.gameple.core.api.controller.v1.response.UserTokenInfo;
import com.gameple.core.domain.UserService;
import com.gameple.core.helper.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ApiResponse<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        String userEmail = userService.createUser(createUserRequest);
        return ApiResponse.success(CreateUserResponse.builder()
                .email(userEmail)
                .build());
    }

    @PostMapping("/oauth/authorize")
    public ApiResponse<OAuthAuthorizeResponse> oAuthAuthorize(@Valid @RequestBody OAuthAuthorizeRequest oAuthAuthorizeRequest) {
        String callback = userService.oAuthAuthorize(oAuthAuthorizeRequest);
        return ApiResponse.success(OAuthAuthorizeResponse.builder()
                .callback(callback)
                .build());
    }

    @PostMapping("/oauth/callback")
    public ApiResponse<OAuthCallbackResponse> oAuthCallback(@Valid @RequestBody OAuthCallbackRequest oAuthCallbackRequest) {
        UserTokenInfo userTokenInfo = userService.findUserTokens(oAuthCallbackRequest);
        return ApiResponse.success(OAuthCallbackResponse.builder()
                .tokenInfo(userTokenInfo)
                .build());
    }

//    @PostMapping("/user/token-refresh")
//    public ApiResponse<OAuthAuthorizeResponse> refreshUserToken(String refreshToken) {
//        String newAccessToken = userService.refreshUserToken(refreshToken);
//        return ApiResponse.success(AuthenticateUserResponse.builder()
//                .accessToken(newAccessToken)
//                .build());
//    }
}
