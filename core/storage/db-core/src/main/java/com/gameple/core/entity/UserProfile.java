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

    @Column(unique = true, nullable = false, length = 255)
    private String nickName;

    @Column(length = 255)
    private String imageUrl;

    @Builder
    public UserProfile(String nickName, String imageUrl) {
        this.nickName = nickName;
        this.imageUrl = imageUrl;
    }
}
