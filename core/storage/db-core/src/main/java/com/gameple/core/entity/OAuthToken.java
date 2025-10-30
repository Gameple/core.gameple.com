package com.gameple.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "gameple_client_oauth_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthToken extends BaseEntity {

    @Column(nullable = false, unique = true, length = 64)
    private String callback;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true, length = 256)
    private String accessToken;

    @Builder
    public OAuthToken(String callback, Long userId, String accessToken) {
        this.callback = callback;
        this.userId = userId;
        this.accessToken = accessToken;
    }
}
