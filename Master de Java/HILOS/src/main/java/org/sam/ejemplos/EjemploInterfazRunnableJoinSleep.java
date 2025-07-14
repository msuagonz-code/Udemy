package org.sam.ejemplos;

public class EjemploInterfazRunnableJoinSleep {
    public static void main(String[] args) throws InterruptedException {

        //hilo del main
        Thread mainThread = Thread.currentThread();

        /*
        * Clase Anonima
        * */
        Runnable viaje = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i + " - " + Thread.currentThread().getName());
                }

                long time = (long) (Math.random() * 1000);

                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Espero: " + time + " ms" + " -> Finalmente me voy de viaje a " + Thread.currentThread().getName());
            }
        };

        /*
        new Thread(viaje, "Islandia").start();
        new Thread(viaje, "Chipre").start();
        new Thread(viaje, "Indonesia").start();
        new Thread(viaje, "Tasmania").start();
        new Thread(viaje, "Australia").start();
        new Thread(viaje, "China").start();
       */

        /*
        * Funcional
        * */
        Runnable viajeFuncional = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i + " - " + Thread.currentThread().getName());
            }

            long time = (long) (Math.random() * 1000);

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Espero: " + time + " ms" + " -> Finalmente me voy de viaje a " + Thread.currentThread().getName());
            System.out.println("Estado de main: "+ mainThread.getState());
        };

        Thread v1 = new Thread(viajeFuncional, "Islandia");
        Thread v2 = new Thread(viajeFuncional, "Chipre");
        Thread v3 = new Thread(viajeFuncional, "Indonesia");
        Thread v4 = new Thread(viajeFuncional, "Tasmania");
        Thread v5 = new Thread(viajeFuncional, "Australia");
        Thread v6 = new Thread(viajeFuncional, "China");

        v1.start();
        v2.start();
        v3.start();
        v4.start();
        v5.start();
        v6.start();

        v1.join();
        v2.join();
        v3.join();
        v4.join();
        v5.join();
        v6.join();

        System.out.println("\r\nContinuando con el metodo "+ mainThread.getName());
    }
}
