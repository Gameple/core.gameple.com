package com.gameple.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@Table(name = "gameple_country_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CountryInfo extends BaseEntity {

    @Column(length = 2, nullable = false, unique = true)
    private String iso2; // ISO 3166-1 alpha-2

    @Column(length = 3)
    private String iso3;  // ISO 3166-1 alpha-3 (optional)

    @Column(length = 100, nullable = false)
    private String name;  // 영어 국가명

    @Column(length = 100, nullable = false)
    private String nativeName;  // 현지 표기명 (예: 대한민국)

    @Column(length = 10, nullable = false)
    private String phoneCode;  // 국제 전화번호 코드 (예: +82)

    @Column(length = 10, nullable = false)
    private String locale;  // 권장 Locale (예: ko-KR)

    @Column(nullable = false)
    @ColumnDefault("0")
    private int sortOrder; // 정렬 우선순위
}
