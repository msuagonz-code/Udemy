package org.sam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window10MessageDialog extends JFrame implements ActionListener {

    Container panel;
    public Window10MessageDialog() throws HeadlessException {
        super("Ventana de Alerta Error");
        panel = getContentPane();
        panel.setLayout(new FlowLayout());

        JOptionPane.showMessageDialog(null, "los campos no pueden ser vacios", "Error de entrada", JOptionPane.ERROR_MESSAGE);

        setSize(new Dimension(600, 300));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Window10MessageDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
