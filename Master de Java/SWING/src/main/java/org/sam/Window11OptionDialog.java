package org.sam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window11OptionDialog extends JFrame implements ActionListener {

    Container panel;

    public Window11OptionDialog() throws HeadlessException {
        super("ventana de Dialogo con Opciones");

        panel = getContentPane();
        panel.setLayout(new FlowLayout());

        Object[] opciones = {
                "Si Ok",
                "No",
                "Quizás mas tarde"
        };

        int valor = JOptionPane.showOptionDialog(null,
                "Desea conituar?",
                "Confirmar Alguna Opcion",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        System.out.println("valor = " + valor);

        setVisible(true);
        setSize(new Dimension(300, 100));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Window11OptionDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
