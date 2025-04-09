package org.sam.webapp.servlet.webapp.session.configs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.sam.webapp.servlet.webapp.session.interceptors.Logging;
import org.sam.webapp.servlet.webapp.session.interceptors.TransactionalJDBC;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@TransactionalJDBC
@Logging
@ApplicationScoped
@Stereotype
@Named
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
