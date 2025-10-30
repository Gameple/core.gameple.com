package com.gameple.core.api.controller.v1;

import com.gameple.core.api.controller.v1.request.OAuthAuthorizeRequest;
import com.gameple.core.api.controller.v1.request.CreateUserRequest;
import com.gameple.core.api.controller.v1.response.CreateUserResponse;
import com.gameple.core.api.controller.v1.response.OAuthAuthorizeResponse;
import com.gameple.core.api.controller.v1.response.UserTokenInfo;
import com.gameple.core.domain.UserService;
import com.gameple.core.helper.jwt.RefreshCookieProvider;
import com.gameple.core.helper.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    private final RefreshCookieProvider refreshCookieProvider;

    @PostMapping("/users")
    public ApiResponse<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest newUser) {
        String userEmail = userService.createUser(newUser);
        return ApiResponse.success(CreateUserResponse.builder()
                .email(userEmail)
                .build());
    }

    @PostMapping("/oauth/authorize")
    public ApiResponse<OAuthAuthorizeResponse> oAuthAuthorize(@Valid @RequestBody OAuthAuthorizeRequest authenticateUserInfo) {
        String callback = userService.oAuthAuthorize(authenticateUserInfo);
        return ApiResponse.success(OAuthAuthorizeResponse.builder()
                .callback(callback)
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
