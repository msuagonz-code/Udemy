package org.sam.ejemploexecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EjemploExecutor {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ejecutor = Executors.newSingleThreadExecutor();

        Runnable tarea = () -> {
            System.out.println("Inicio de la tarea...");
            try {
                System.out.println("Nombre del thread: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            System.out.println("Finaliza la tarea...");

        };

        ejecutor.submit(tarea);
        ejecutor.shutdown();
        System.out.println("Continuando con la ejecución del main 1");
        ejecutor.awaitTermination(2 , TimeUnit.SECONDS);
        System.out.println("Continuando con la ejecución del main 2");
    }
}
