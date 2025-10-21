package com.gameple.core.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gameple_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255, name = "password_hash")
    private String passwordHash;

    @Builder
    public User(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
