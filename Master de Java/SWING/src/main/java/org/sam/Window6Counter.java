package org.sam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window6Counter extends JFrame implements ActionListener{

    private Integer contador = 0;
    private JButton botonAumentar = new JButton("click aumentar");
    private JButton botonDisminuir = new JButton("click disminuir");
    private JLabel result = new JLabel("Countador 0");

    public Window6Counter() throws HeadlessException {
        super("Ejemplo Contador");

        Container panel = getContentPane();
        panel.add(botonAumentar);
        panel.add(botonDisminuir);
        
        panel.add(result);
        panel.setLayout(new FlowLayout());
        botonAumentar.addActionListener(this);

        botonDisminuir.addActionListener(this);
        

        setSize(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Window6Counter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().toString().equals("click aumentar")){
            contador++;
        }else{
            contador--;
        }

        result.setText("Countador "+ String.valueOf(contador));
    }
}
