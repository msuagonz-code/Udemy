package org.sam;

import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class Window2FlowLayout extends JFrame {

    public Window2FlowLayout() throws HeadlessException {
        super("Ejemplo de boton y Eventos");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));

        for(int i = 1; i <= 10; i ++){
            JButton boton = new JButton("Hell-NO ".concat(String.valueOf(i)));
            String audioPath = "C:\\Java\\SamDeck\\audios\\Hell-No.mp3";
            long time = 6;

            boton.addActionListener(event -> player(audioPath, time));

            boton.setPreferredSize(new Dimension(100, 50));
            panel.add(boton);
        }
        setContentPane(panel);

        setSize(600, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Window2FlowLayout();
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
