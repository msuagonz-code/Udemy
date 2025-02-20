package org.sam;

import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class Window4BorderLayout extends JFrame {

    public Window4BorderLayout() throws HeadlessException {
        super("Ejemplo de boton y Eventos");

        JPanel panel = new JPanel(new BorderLayout(10, 20));


            JButton boton = new JButton("Hell-NO");
            String audioPath = "C:\\Java\\SamDeck\\audios\\Hell-No.mp3";
            long time = 6;

            boton.addActionListener(event -> player(audioPath, time));

            //boton.setSize(100, 100);
            boton.setPreferredSize(new Dimension(100, 60));
            panel.add(boton, BorderLayout.CENTER);
            panel.add(new JButton("Norte"), BorderLayout.NORTH);
            panel.add(new JButton("Sur"), BorderLayout.SOUTH);
            panel.add(new JButton("Este"), BorderLayout.EAST);
            panel.add(new JButton("Oeste"), BorderLayout.WEST);


        setContentPane(panel);

        setSize(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Window4BorderLayout();
    }

    private static void player(String audioPath, long time){
        // Cargar VLCJ
        AudioPlayerComponent audioPlayer = new AudioPlayerComponent();

        // Reproducir audio
        audioPlayer.mediaPlayer().media().start(audioPath);

        // Esperar mientras suena
        try {
            Thread.sleep(time * 1000); // Ajusta según la duración del audio
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Liberar recursos
        audioPlayer.release();
    }
}
