package org.sam.ejemploexecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EjemploScheduleExecutorServicePeriodo {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        System.out.println("Alguna tarea en el main...");

        //CountDownLatch lock = new CountDownLatch(5);
        AtomicInteger contador = new AtomicInteger(5);
        Future<?> future = executor.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                //lock.countDown();
                contador.getAndDecrement();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hola mundo tarea...");

        }, 1000, 2000,TimeUnit.MILLISECONDS);

        //lock.await();
        //future.cancel(true);
        //TimeUnit.SECONDS.sleep(10); // Bloquea el Thread main por 10 segundos

        while(contador.get() >= 0){
            if (contador.get() == 0){
                future.cancel(true);
                break;
            }
        }

        System.out.println("Alguna otra tarea en el main....");
        executor.shutdown();
    }
}
