package org.sam.ejemplos;

import org.sam.ejemplos.runnable.ImprimirFrases;

public class EjemploSincronizacionThread {
    public static void main(String[] args) throws InterruptedException {

        new Thread(new ImprimirFrases("Hola ", "que tal?")).start();
        new Thread(new ImprimirFrases("¿como ", "te sientes?")).start();
        Thread.sleep(100);
        Thread v3 = new Thread(new ImprimirFrases("¿tienes ", "hambre?"));
        v3.start();
        Thread.sleep(100);
        System.out.println("v3.getState = " + v3.getState());
    }

    public synchronized static void imprimirFrases(String f1, String f2){
        System.out.print(f1);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(f2);
    }
}
