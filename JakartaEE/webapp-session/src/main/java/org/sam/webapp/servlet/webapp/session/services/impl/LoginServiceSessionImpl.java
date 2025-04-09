package org.sam.webapp.servlet.webapp.session.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.sam.webapp.servlet.webapp.session.services.LoginService;

import java.util.Optional;

public class LoginServiceSessionImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username != null){
            return Optional.of(username);
        }

        return Optional.empty();
    }
}
