package org.sam.webapp.servlet.webapp.cookies.services.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.sam.webapp.servlet.webapp.cookies.services.LoginService;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies() != null ? request.getCookies() : new Cookie[0];
        return Arrays.stream(cookies)
                .filter(cookie -> "username".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
