package org.sam.webapp.jsf3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.sam.webapp.jsf3.config.CustomFacesContext;

import java.util.Locale;
import java.util.ResourceBundle;

@ApplicationScoped
public class ProducerResources {

    @Produces
    @RequestScoped
    @CustomFacesContext
    public FacesContext beanFacesContext(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        return facesContext;
    }

    @Produces
    @Named("msg")
    public ResourceBundle beanBundle(){
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return ResourceBundle.getBundle("messages", locale);
    }

}
