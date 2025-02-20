package org.sam.runnable;

public class ViajeTarea implements Runnable{

    String nombre;

    public ViajeTarea(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " - " + getNombre());
        }

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Finalmente me voy de viaje a " + nombre);
    }
}
