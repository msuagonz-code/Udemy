package org.sam;

import com.sun.jna.platform.win32.OaIdl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window7CurrencyConverter extends JFrame implements ActionListener {

    Container panel;
    JButton botonEu, botonUsd, botonReset;
    JTextField mount;

    public static final String CURRENCY_EURO = "Euro";
    public static final String CURRENCY_DOLAR = "Dolar";

    public Window7CurrencyConverter() throws HeadlessException {
        super("Conversor de monedas");
        panel = getContentPane();
        panel.setLayout(new FlowLayout());

        botonEu = new JButton(CURRENCY_EURO);
        botonUsd = new JButton(CURRENCY_DOLAR);
        botonReset = new JButton("Reset");

        botonEu.addActionListener(this);
        botonUsd.addActionListener(this);
        botonReset.addActionListener(this);

        mount = new JTextField(10);
        mount.setText(String.valueOf(00.00F));

        panel.add(mount);
        panel.add(new JLabel("Euros: "));
        panel.add(botonEu);
        panel.add(new JLabel(" Dolares: "));
        panel.add(botonUsd);
        panel.add(new JLabel("Resetear: "));
        panel.add(botonReset);

        setSize(600, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Float value = Float.valueOf(mount.getText());
        String textCurrency = e.getActionCommand();

        if(textCurrency.equals(CURRENCY_EURO)){
            value = (value / 1029.19F);
            panel.setBackground(Color.green);
        }else if(textCurrency.equals(CURRENCY_DOLAR)){
            value = (value / 976.38F);
            panel.setBackground(Color.blue);
        } else {
            value = 0.00F;
        }
        mount.setText(String.valueOf(value));
    }

    public static void main(String[] args) {
        new Window7CurrencyConverter();
    }
}
