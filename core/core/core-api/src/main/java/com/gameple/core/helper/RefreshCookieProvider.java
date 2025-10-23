package com.gameple.core.helper;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class RefreshCookieProvider {

    public static final String REFRESH_TOKEN_KEY = "refreshToken";

    public Cookie createRefreshCookie(String token) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_KEY, token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(3 * 24 * 60 * 60);
        cookie.setAttribute("SameSite", "None");
        return cookie;
    }
}
