package com.gameple.core.api.controller.v1;

import com.gameple.core.api.controller.v1.request.AuthenticateUserRequest;
import com.gameple.core.api.controller.v1.request.CreateUserRequest;
import com.gameple.core.api.controller.v1.response.AuthenticateUserResponse;
import com.gameple.core.api.controller.v1.response.CreateUserResponse;
import com.gameple.core.api.controller.v1.response.UserTokenInfo;
import com.gameple.core.domain.UserService;
import com.gameple.core.helper.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public ApiResponse<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest newUser) {
        String userEmail = userService.createUser(newUser);
        return ApiResponse.success(CreateUserResponse.builder()
                .email(userEmail)
                .build());
    }

    @PostMapping("/api/v1/user/authenticate")
    public ApiResponse<AuthenticateUserResponse> authenticateUser(@Valid @RequestBody AuthenticateUserRequest authenticateUserInfo) {
        UserTokenInfo userTokenInfo = userService.authenticateUser(authenticateUserInfo);
        return ApiResponse.success(AuthenticateUserResponse.builder()
                .userTokenInfo(userTokenInfo)
                .build());
    }
}
