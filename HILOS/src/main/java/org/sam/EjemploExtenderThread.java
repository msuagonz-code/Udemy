package org.sam;

import org.sam.thread.NombreThread;

public class EjemploExtenderThread {

    public static void main(String[] args) throws InterruptedException {

        Thread hilo1 = new NombreThread("John Wick");
        Thread hilo2 = new NombreThread("A todo Gas");
        NombreThread hilo3 = new NombreThread("Terminator");

        Thread.sleep(2);

        hilo1.start(); // se invoca el hilo1
        hilo2.start(); // se invoca el hilo2
        hilo3.start(); // se invoca el hilo3

        System.out.println(hilo1.getState());
        System.out.println(hilo2.getState());
        System.out.println(hilo3.getState());
    }
}
