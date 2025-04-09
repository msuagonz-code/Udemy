package org.sam.webapp.servlet.webapp.session.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final String CONFIG_FILE = "properties.properties";
    private static Properties properties = new Properties(); // Inicializa la variable properties

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo de configuración: " + CONFIG_FILE);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando configuración de la BD", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
