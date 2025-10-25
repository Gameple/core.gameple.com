package com.gameple.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gameple_user_profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(unique = true, nullable = false, length = 255)
    private String nickName;

    @Column(length = 2)
    private String countryCode;

    @Column(length = 255)
    private String imageUrl;

    @Builder
    public UserProfile(Long userId, String nickName, String countryCode) {
        this.userId = userId;
        this.nickName = nickName;
        this.countryCode = countryCode;
    }
}
