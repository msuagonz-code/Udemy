package org.sam.ejemploexecutor;

import java.util.concurrent.*;

public class EjemploExecutorFuture2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        //ExecutorService ejecutor = Executors.newFixedThreadPool(3);
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        System.out.println("Tamaño del pool: " + ejecutor.getPoolSize());
        System.out.println("Cantidad de tareas en cola: " + ejecutor.getQueue().size());

        Callable<String> tarea = () -> {
            System.out.println("Inicio de la tarea 1...");
            try {
                System.out.println("Nombre del thread: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            System.out.println("Finaliza la tarea 1...");
        return "\r\n\r\nAlgun resultado importante de la tarea...\r\n";
        };

        Callable<Integer> tarea2 = () -> {
            System.out.println("Iniciando Tarea 2 ...");
            TimeUnit.SECONDS.sleep(3);
            return 10;
        };

        Future<String> future = ejecutor.submit(tarea);
        Future<String> future2 = ejecutor.submit(tarea);
        Future<Integer> future3 = ejecutor.submit(tarea2);

        System.out.println("Tamaño del pool: " + ejecutor.getPoolSize());
        System.out.println("Cantidad de tareas en cola: " + ejecutor.getQueue().size());

        ejecutor.shutdown();
        System.out.println("Continuando con la ejecución del main 1");

        //System.out.println("Is done?: " + future.isDone());
        while(!(future.isDone() && future2.isDone() && future3.isDone())){
            System.out.println(String.format("Resultado 1: %s, Resultado 2: %s, Resultado 3: %s",
                    future.isDone()? "Finalizó":"En proceso",
                    future2.isDone()? "Finalizó":"En proceso",
                    future3.isDone()? "Finalizó":"En proceso"));
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        //System.out.println("resultado: " + future.get(5, TimeUnit.SECONDS));
        System.out.println("Resultado de la tarea 1: " + future.get());
        System.out.println("Is done?: " + future.isDone());

        System.out.println("Resultado de la tarea 2: " + future2.get());
        System.out.println("Is done?: " + future2.isDone());

        System.out.println("Resultado de la tarea 3: " + future3.get());
        System.out.println("Is done?: " + future3.isDone());

        System.out.println("Ya termino future.get() Continuamos... ");
    }
}
