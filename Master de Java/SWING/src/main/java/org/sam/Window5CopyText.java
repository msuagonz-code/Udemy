package org.sam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window5CopyText extends JFrame{

    JTextField fieldValue, result;

    public Window5CopyText() throws HeadlessException {
        super("Ejemplo de Copiar");
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Valor: "));
        fieldValue = new JTextField(10);
        panel.add(fieldValue);

        JButton boton = new JButton("Copiar");
        boton.addActionListener(new CopyTextActionListener());
        panel.add(boton);

        result = new JTextField(10);
        panel.add(result);

        setSize(new Dimension(600, 100));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Window5CopyText();
    }

    private class CopyTextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String value = fieldValue.getText();
            result.setText(value);
        }
    }
}
