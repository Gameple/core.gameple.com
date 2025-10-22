package com.gameple.core.api.controller.v1.response;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateUserResponse {

    private String email;

    @Builder
    public CreateUserResponse(String email) {
        this.email = email;
    }
}
