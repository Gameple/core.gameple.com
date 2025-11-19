package com.gameple.core.api.controller.v1;

import com.gameple.core.api.controller.v1.request.CreateUserRequest;
import com.gameple.core.api.controller.v1.response.CreateUserResponse;
import com.gameple.core.domain.AuthService;
import com.gameple.core.helper.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final AuthService authService;

    @PostMapping("/user")
    public ApiResponse<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        String userEmail = authService.createUser(createUserRequest);
        return ApiResponse.success(CreateUserResponse.builder()
                .email(userEmail)
                .build());
    }
}
