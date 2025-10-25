package com.gameple.core.api.controller.v1;

import com.gameple.core.api.controller.v1.request.AuthenticateUserRequest;
import com.gameple.core.api.controller.v1.request.CreateUserRequest;
import com.gameple.core.api.controller.v1.response.AuthenticateUserResponse;
import com.gameple.core.api.controller.v1.response.CreateUserResponse;
import com.gameple.core.api.controller.v1.response.UserTokenInfo;
import com.gameple.core.domain.UserService;
import com.gameple.core.helper.jwt.RefreshCookieProvider;
import com.gameple.core.helper.response.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/user/authenticate")
    public ApiResponse<AuthenticateUserResponse> authenticateUser(@Valid @RequestBody AuthenticateUserRequest authenticateUserInfo, HttpServletResponse response) {
        UserTokenInfo userTokenInfo = userService.authenticateUser(authenticateUserInfo);
        Cookie refreshCookie = refreshCookieProvider.createRefreshCookie(userTokenInfo.getRefreshToken());
        response.addCookie(refreshCookie);
        return ApiResponse.success(AuthenticateUserResponse.builder()
                .accessToken(userTokenInfo.getAccessToken())
                .build());
    }

    @PostMapping("/user/token-refresh")
    public ApiResponse<AuthenticateUserResponse> refreshUserToken(@CookieValue(name = RefreshCookieProvider.REFRESH_TOKEN_KEY) String refreshToken) {
        String newAccessToken = userService.refreshUserToken(refreshToken);
        return ApiResponse.success(AuthenticateUserResponse.builder()
                .accessToken(newAccessToken)
                .build());
    }
}
