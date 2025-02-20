package org.sam;

import org.sam.sound.AudioPlayer;
import org.sam.sound.Sonidos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    private AudioPlayer player; // Mantén una instancia de AudioPlayer

    public Main() throws HeadlessException {
        super("Sound Deck");
        Container contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout(5,5));

        JButton boton1 = new JButton(Sonidos.HELL_NO.nombre());
        JButton boton2 = new JButton(Sonidos.FART.nombre());
        JButton boton3 = new JButton(Sonidos.MY_LIFE_BE_LIKE.nombre());
        JButton boton4 = new JButton(Sonidos.WHAT_THE_DOG_DOING.nombre());

        JPanel panelCenter = new JPanel( new GridLayout(2, 2, 5, 5));
        contenedor.add(panelCenter, BorderLayout.CENTER);

        panelCenter.add(boton1);
        panelCenter.add(boton2);
        panelCenter.add(boton3);
        panelCenter.add(boton4);

        boton1.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);
        boton4.addActionListener(this);
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