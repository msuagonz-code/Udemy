package org.sam.ejemplos;

public class EjemploInterfazRunnableFuncional {
    public static void main(String[] args) {

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
        };

        new Thread(viajeFuncional, "Islandia").start();
        new Thread(viajeFuncional, "Chipre").start();
        new Thread(viajeFuncional, "Indonesia").start();
        new Thread(viajeFuncional, "Tasmania").start();
        new Thread(viajeFuncional, "Australia").start();
        new Thread(viajeFuncional, "China").start();

    }
}
