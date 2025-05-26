package org.sam.ejemplos.thread;

public class NombreThread extends Thread{

    public NombreThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Se inicia el método run del hilo: " + this.getName());

        for(int i = 1; i <= 10; i++){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(" Película: "+ this.getName() + " "+ i);
        }

        System.out.println("Finaliza el thread " + this.getName());
    }
}
