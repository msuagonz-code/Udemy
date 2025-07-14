package org.sam.ejemploexecutor;

import org.sam.ejemplosync.Panaderia;
import org.sam.ejemplosync.runnable.Consumidor;
import org.sam.ejemplosync.runnable.Panadero;

import java.util.concurrent.*;

public class EjemploExecutorProductorConsumidor {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        //ExecutorService ejecutor = Executors.newFixedThreadPool(3);
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        System.out.println("Tamaño del pool: " + ejecutor.getPoolSize());
        System.out.println("Cantidad de tareas en cola: " + ejecutor.getQueue().size());

        // Monitor o recurso compartido
        Panaderia panaderia = new Panaderia();

        Runnable productor = new Panadero(panaderia);
        Runnable consumidor = new Consumidor(panaderia);

        Future<?> future1 = ejecutor.submit(productor);
        Future<?> future2 = ejecutor.submit(consumidor);


        System.out.println("Tamaño del pool: " + ejecutor.getPoolSize());
        System.out.println("Cantidad de tareas en cola: " + ejecutor.getQueue().size());

        ejecutor.shutdown();
        System.out.println("Continuando con la ejecución del main");

    }
}
