package org.sam.sound;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public enum Sonidos {

    FART,
    HELL_NO,
    MY_LIFE_BE_LIKE,
    WHAT_THE_DOG_DOING,
    BRAH,
    FART2,
    BANG,
    NOP,
    FBI,
    AAOW,
    HEHE,
    OH_MY_GOD;

    public String direccionCompleta() {
        try {
            return Paths.get(getClass().getClassLoader().getResource(this + ".mp3").toURI()).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo estático para recuperar el enum a partir del path
    public static Sonidos getByNombre(String nombre) {
        for (Sonidos sound : Sonidos.values()) {
            if (sound.toString().equals(nombre)) {
                return sound;
            }
        }
        throw new IllegalArgumentException("No se encontró un sonido para el nombre: " + nombre);
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace("_", "-");
    }
}
