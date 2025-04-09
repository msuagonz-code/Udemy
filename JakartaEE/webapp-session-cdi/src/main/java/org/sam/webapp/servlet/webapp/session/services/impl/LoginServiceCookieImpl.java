package org.sam.webapp.servlet.webapp.session.services.impl;

import jakarta.enterprise.inject.Alternative;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.sam.webapp.servlet.webapp.session.services.LoginService;

import java.util.Arrays;
import java.util.Optional;

// @Alternative
public class LoginServiceCookieImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies() != null ? request.getCookies() : new Cookie[0];
        return Arrays.stream(cookies)
                .filter(cookie -> "username".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
