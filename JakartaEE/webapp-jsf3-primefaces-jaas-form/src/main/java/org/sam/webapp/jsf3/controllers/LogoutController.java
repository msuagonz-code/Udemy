package org.sam.webapp.jsf3.controllers;

import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Model
public class LogoutController {
    
    @Inject
    private FacesContext facesContext;
    
    public String logout() throws ServletException {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        request.logout();
        request.getSession().invalidate();
        facesContext.addMessage(null, new FacesMessage("Has cerrado la sesión con éxito!"));
        return "login.xhtml";
    }
}
