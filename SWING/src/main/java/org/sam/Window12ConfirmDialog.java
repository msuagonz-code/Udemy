package org.sam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window12ConfirmDialog extends JFrame implements ActionListener {

    Container contenedor;

    public static void main(String[] args) {
        new Window12ConfirmDialog();
    }

    public Window12ConfirmDialog() throws HeadlessException {
        super("Confirmar Antes de Ejecutar una Tarea");

        contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout());

        JPanel formPanel = new JPanel(new GridLayout(4,2, 5, 5));

        JLabel nombre = new JLabel("Nombre: ", JLabel.RIGHT);
        JTextField nombreField = new JTextField();
        formPanel.add(nombre);
        formPanel.add(nombreField);

        JLabel apellido = new JLabel("Apellido: ", JLabel.RIGHT);
        JTextField apellidoField = new JTextField();
        formPanel.add(apellido);
        formPanel.add(apellidoField);

        JLabel cif = new JLabel("CIF: ", JLabel.RIGHT);
        JTextField cifField = new JTextField();
        formPanel.add(cif);
        formPanel.add(cifField);

        JRadioButton morning = new JRadioButton("Grupo mañana", true);
        JRadioButton afternoon = new JRadioButton("Grupo tarde");
        formPanel.add(morning);
        formPanel.add(afternoon);

        int option = JOptionPane.showConfirmDialog(
                this,
                formPanel,
                "Introduzca los datos",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null
        );

        System.out.println("option = " + option);

        if(option == JOptionPane.OK_OPTION){
            System.out.println("Hemos seleccionado OK");
            String nameValue = nombreField.getText();
            String lastnameValue = apellidoField.getText();
            System.out.println("Nombre Completo: " + nameValue +" "+ lastnameValue);
            JOptionPane.showMessageDialog(this, "Ejecutado con éxito", "Alerta", JOptionPane.INFORMATION_MESSAGE, null);
        }else{
            System.out.println("Hemos seleccionado la opción: " + option);
            JOptionPane.showMessageDialog(this, "Ejecutado con error!", "Alerta", JOptionPane.ERROR_MESSAGE, null);
        }

        setSize(new Dimension(400, 200));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
