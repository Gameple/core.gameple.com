package com.gameple.core.entity;

import com.gameple.core.enums.LoginLogType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "gameple_user_login_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private LoginLogType status;

    @Column(updatable = false, name = "login_at")
    private Instant loginAt;

    @Column(nullable = false, length = 255)
    private String createdIpAddress;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    @Builder
    public UserLoginLog(Long userId, String createdIpAddress, LoginLogType loginLogType, Instant loginAt) {
        this.userId = userId;
        this.createdIpAddress = createdIpAddress;
        this.status = loginLogType;
        this.loginAt = loginAt;
    }
}
