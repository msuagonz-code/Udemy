package org.sam.sound;

public enum Sonidos {
    FART("fart"),
    HELL_NO("Hell-No"),
    MY_LIFE_BE_LIKE("my-life-be-like"),
    WHAT_THE_DOG_DOING("what-the-dog-doing");

    private String nombre;
    private String direccion;

    Sonidos(String nombre){
        this.nombre = nombre;
        this.direccion = "C:\\Java\\SounDeck\\src\\main\\resources\\";
    }

    public String nombre() {
        return nombre;
    }

    public String direccion() {
        return direccion;
    }

    public String direccionCompleta(){
        return this.direccion() +""+this.nombre()+".mp3";
    }

    // Método estático para recuperar el enum a partir del path
    public static Sonidos getByNombre(String nombre) {
        System.out.println("nombre = " + nombre);
        for (Sonidos sound : Sonidos.values()) {
            if (sound.nombre().equals(nombre)) {
                return sound;
            }
        }
        throw new IllegalArgumentException("No se encontró un sonido para el nombre: " + nombre);
    }
}
