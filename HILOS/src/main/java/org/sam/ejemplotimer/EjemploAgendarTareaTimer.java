package org.sam.ejemplotimer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class EjemploAgendarTareaTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Tarea realizada en " + new Date() +
                        " nombre del thread: "+ Thread.currentThread().getName());
                System.out.println("Finaliza el tiempo ");
                timer.cancel();
            }
        }, 5000);

        System.out.println("Agendamos tarea para 5 seg más...");
    }
}
