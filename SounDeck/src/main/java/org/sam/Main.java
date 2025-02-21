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

        JButton boton1 = new JButton(Sonidos.HELL_NO.toString());
        JButton boton2 = new JButton(Sonidos.FART.toString());
        JButton boton3 = new JButton(Sonidos.MY_LIFE_BE_LIKE.toString());
        JButton boton4 = new JButton(Sonidos.WHAT_THE_DOG_DOING.toString());
        JButton boton5 = new JButton(Sonidos.BRAH.toString());
        JButton boton6 = new JButton(Sonidos.FART2.toString());
        JButton boton7 = new JButton(Sonidos.BANG.toString());
        JButton boton8 = new JButton(Sonidos.NOP.toString());
        JButton boton9 = new JButton(Sonidos.FBI.toString());
        JButton boton10 = new JButton(Sonidos.AAOW.toString());
        JButton boton11 = new JButton(Sonidos.HEHE.toString());
        JButton boton12 = new JButton(Sonidos.OH_MY_GOD.toString());

        JPanel panelCenter = new JPanel( new GridLayout(4, 3, 5, 5));
        contenedor.add(panelCenter, BorderLayout.CENTER);

        panelCenter.add(boton1);
        panelCenter.add(boton2);
        panelCenter.add(boton3);
        panelCenter.add(boton4);
        panelCenter.add(boton5);
        panelCenter.add(boton6);
        panelCenter.add(boton7);
        panelCenter.add(boton8);
        panelCenter.add(boton9);
        panelCenter.add(boton10);
        panelCenter.add(boton11);
        panelCenter.add(boton12);

        boton1.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);
        boton4.addActionListener(this);
        boton5.addActionListener(this);
        boton6.addActionListener(this);
        boton7.addActionListener(this);
        boton8.addActionListener(this);
        boton9.addActionListener(this);
        boton10.addActionListener(this);
        boton11.addActionListener(this);
        boton12.addActionListener(this);

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