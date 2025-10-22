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

    @Column(length = 255)
    private String imageUrl;

    @Builder
    public UserProfile(Long userId, String nickName) {
        this.userId = userId;
        this.nickName = nickName;
    }
}
