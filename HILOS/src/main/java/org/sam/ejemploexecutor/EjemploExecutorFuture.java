package org.sam.ejemploexecutor;

import java.util.concurrent.*;

public class EjemploExecutorFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService ejecutor = Executors.newSingleThreadExecutor();

        Runnable tarea = () -> {
            System.out.println("Inicio de la tarea...");
            try {
                System.out.println("Nombre del thread: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            System.out.println("Finaliza la tarea...");

        };

        Callable<String> tarea2 = () -> {
            System.out.println("Inicio de la tarea...");
            try {
                System.out.println("Nombre del thread: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            System.out.println("Finaliza la tarea...");
        return "\r\n\r\nAlgun resultado importante de la tarea...\r\n";
        };

        Future<String> future = ejecutor.submit(tarea2);
        ejecutor.shutdown();
        System.out.println("Continuando con la ejecución del main 1");

        //System.out.println("Is done?: " + future.isDone());
        while(!future.isDone()){
            System.out.println("Ejecutando la tarea en el while");
            TimeUnit.MILLISECONDS.sleep(1500);
        }
        //System.out.println("resultado: " + future.get(5, TimeUnit.SECONDS));
        System.out.println("Resultado de la tarea: " + future.get());
        System.out.println("Is done?: " + future.isDone());
        System.out.println("Ya termino future.get() Continuamos... ");
    }
}
