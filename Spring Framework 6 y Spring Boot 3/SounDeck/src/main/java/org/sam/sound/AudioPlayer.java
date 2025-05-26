package org.sam.sound;

import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

public class AudioPlayer implements Runnable {
    private Thread thread;
    private volatile boolean running = false;
    private  AudioPlayerComponent componente;
    private  MediaPlayer mediaPlayer;
    private  String soundFilePath;

    public AudioPlayer() {
        this.componente = new AudioPlayerComponent();
        this.mediaPlayer = componente.mediaPlayer();
    }

    public synchronized void play(String soundFilePath) {
        this.soundFilePath = soundFilePath;
        if (this.isRunning()) {
            this.stop(); // Si ya está corriendo, lo detenemos antes de iniciar uno nuevo
        }

        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public synchronized void stop() {
        if (this.isRunning()) {
            this.running = false;

            // Detenemos el audio correctamente
            if (this.mediaPlayer != null && this.mediaPlayer.status().isPlaying()) {
                this.mediaPlayer.controls().stop();
            }

            // Esperamos a que el hilo termine correctamente
            if (this.thread != null) {
                try {
                    this.thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized boolean isRunning() {
        return this.running && this.mediaPlayer.status().isPlaying();
    }

    @Override
    public void run() {
        if (mediaPlayer != null && mediaPlayer.status() != null) {
            if(!mediaPlayer.status().isPlaying()){
                mediaPlayer.media().play(soundFilePath);
            }
        }else{
            this.componente = new AudioPlayerComponent();
            this.mediaPlayer = componente.mediaPlayer();
        }

        while (this.running && this.mediaPlayer.status().isPlaying()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido, salimos del bucle
                Thread.currentThread().interrupt();
                break;
            }
        }

        // Aseguramos que los recursos se liberen correctamente
        if (!this.running) {
            stop();
        }
    }
}
