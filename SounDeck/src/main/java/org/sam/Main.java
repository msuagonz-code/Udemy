package org.sam;

import org.sam.sound.AudioPlayer;
import org.sam.sound.Sonidos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class Main extends JFrame implements ActionListener {

    private AudioPlayer player; // Mantén una instancia de AudioPlayer

    public Main() throws HeadlessException {
        super("Sound Deck");
        Container contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout(5,5));
        JPanel panelCenter = new JPanel( new GridLayout(4, 3, 5, 5));
        contenedor.add(panelCenter, BorderLayout.CENTER);

        for(Sonidos sonido: Sonidos.values()){
            JButton boton = new JButton(sonido.toString());
            panelCenter.add(boton);
            boton.addActionListener(this);
        }

        this.player = new AudioPlayer();

        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (!player.isRunning()) {
                player.play(Sonidos.getByNombre(comando).direccionCompleta());
        } else {
            player.stop();
        }
    }
}