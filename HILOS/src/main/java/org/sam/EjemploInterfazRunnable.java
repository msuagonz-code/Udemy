package org.sam;

import org.sam.runnable.ViajeTarea;

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
