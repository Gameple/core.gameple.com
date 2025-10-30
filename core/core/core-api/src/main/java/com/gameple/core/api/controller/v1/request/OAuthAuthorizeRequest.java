package com.gameple.core.api.controller.v1.request;

import com.gameple.core.enums.ClientType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OAuthAuthorizeRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private ClientType clientType;

    @NotBlank
    private String redirectUrl;
}
