package com.gameple.core.api.controller.v1.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthenticateUserRequest {

    @Email
    private String email;

    @NotBlank
    private String password;
}
