package org.sam.ejemplosync;

import org.sam.ejemplosync.runnable.Consumidor;
import org.sam.ejemplosync.runnable.Panadero;

import java.util.concurrent.ThreadLocalRandom;

public class EjemploProductorConsumidorJava8 {

    public static void main(String[] args) {
        Panaderia panaderia = new Panaderia();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                panaderia.hornear("Pan nº:"+ i);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                panaderia.consumir();
            }
        }).start();
    }

}
