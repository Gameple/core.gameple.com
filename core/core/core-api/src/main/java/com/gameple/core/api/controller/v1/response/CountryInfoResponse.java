package com.gameple.core.api.controller.v1.response;

import lombok.Builder;
import lombok.Data;

@Data
public class CountryInfoResponse {

    private Long id;

    private String iso2;

    private String iso3;

    private String name;

    private String nativeName;

    private String phoneCode;

    private String locale;

    private int sortOrder;

    @Builder
    public CountryInfoResponse(Long id, String iso2, String iso3, String name, String nativeName, String phoneCode, String locale, int sortOrder) {
        this.id = id;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.name = name;
        this.nativeName = nativeName;
        this.phoneCode = phoneCode;
        this.locale = locale;
        this.sortOrder = sortOrder;
    }
}
