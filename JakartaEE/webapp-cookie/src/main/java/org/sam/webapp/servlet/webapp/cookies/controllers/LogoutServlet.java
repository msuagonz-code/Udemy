package org.sam.webapp.servlet.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.servlet.webapp.cookies.services.LoginService;
import org.sam.webapp.servlet.webapp.cookies.services.impl.LoginServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService loginService = new LoginServiceImpl();
        Optional<String> username = loginService.getUsername(req);
        if(username.isPresent()){
            Cookie usernameCookie = new Cookie("username", "");
            usernameCookie.setMaxAge(0);
            resp.addCookie(usernameCookie);
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
