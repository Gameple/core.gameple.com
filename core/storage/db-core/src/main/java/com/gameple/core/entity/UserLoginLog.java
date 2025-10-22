package com.gameple.core.entity;

import com.gameple.core.enums.LoginLogType;
import com.gameple.core.helper.AesEncryptConverter;
import com.gameple.core.helper.IpAddressHolder;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

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

    @Convert(converter = AesEncryptConverter.class)
    @Column(nullable = false, length = 255)
    private String createdIpAddress;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        this.createdIpAddress = IpAddressHolder.get();
    }

    @Builder
    public UserLoginLog(Long userId, LoginLogType loginLogType) {
        this.userId = userId;
        this.status = loginLogType;
    }
}
