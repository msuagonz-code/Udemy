package org.sam;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() throws HeadlessException {
        super("Ejemplo de boton y Eventos");

        JButton boton = new JButton("Hell-NO");
        String audioPath = "C:\\Java\\SWING\\audios\\Hell-No.mp3";
        long time = 6;

        boton.addActionListener(event -> player(audioPath));

        boton.setSize(100, 100);
        getContentPane().add(boton);
        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main();
    }

    private static void player(String audioPath){
        // Cargar VLCJ
        AudioPlayerComponent audioPlayer = new AudioPlayerComponent();

        // Reproducir audio
        audioPlayer.mediaPlayer().media().start(audioPath);

        // Esperar mientras suena
        try {
            Thread.sleep(audioPlayer.mediaPlayer().status().length()); // Ajusta según la duración del audio
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Liberar recursos
        audioPlayer.release();
    }
}
