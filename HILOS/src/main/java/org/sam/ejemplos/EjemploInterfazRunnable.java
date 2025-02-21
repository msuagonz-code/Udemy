package org.sam.ejemplos;

import org.sam.ejemplos.runnable.ViajeTarea;

public class EjemploInterfazRunnable {
    public static void main(String[] args) {
        new Thread(new ViajeTarea("Islandia")).start();
        new Thread(new ViajeTarea("Chipre")).start();
        new Thread(new ViajeTarea("Indonesia")).start();
        new Thread(new ViajeTarea("Tasmania")).start();
        new Thread(new ViajeTarea("Australia")).start();
        new Thread(new ViajeTarea("China")).start();
    }
}
