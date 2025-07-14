package org.sam.ejemplotimer;

import javax.tools.Tool;
import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class EjemploAgendarTareaTimerPeriodo {
    public static void main(String[] args) {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
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
                    toolkit.beep();
                }
            }
        }, 500, 1000);

        System.out.println("Agendamos tarea para 10 seg más...");
    }
}
