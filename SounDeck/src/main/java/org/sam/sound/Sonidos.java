package org.sam.sound;

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

    private String direccion = "C:\\Java\\SounDeck\\src\\main\\resources\\";

    public String getDireccion() {
        return direccion;
    }

    public String direccionCompleta(){
        return this.getDireccion() + this + ".mp3";
    }

    // Método estático para recuperar el enum a partir del path
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
