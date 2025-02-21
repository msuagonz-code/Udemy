package org.sam.ejemplotimer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class EjemploAgendarTareaTimerPeriodo {
    public static void main(String[] args) {

        AtomicInteger contadorAtomico = new AtomicInteger(3);
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                int contador = contadorAtomico.getAndDecrement();
                if(contador > 0) {
                    System.out.println("Tarea "+contador+" periodica en: " + new Date() +
                            " nombre del thread: " + Thread.currentThread().getName());
                }else {
                    System.out.println("Finaliza el tiempo ");
                    timer.cancel();
                }
            }
        }, 500, 1000);

        System.out.println("Agendamos tarea para 5 seg más...");
    }
}
