package com.gameple.core.api.controller.v1.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OAuthCallbackRequest {

    @NotBlank
    private String callback;
}
