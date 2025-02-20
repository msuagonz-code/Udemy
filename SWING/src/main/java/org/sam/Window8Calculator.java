package org.sam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Window8Calculator extends JFrame implements ActionListener {

    Container container;
    JTextField numberA, numberB, result;

    Window8Calculator() throws HeadlessException {
        super("Calculadora");
        container = getContentPane();
        container.setLayout(new BorderLayout());
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);


        JMenu menu = new JMenu("Operaciones");
        JMenuItem itemSuma = new JMenuItem(Operaciones.SUMA.name());
        JMenuItem itemResta = new JMenuItem(Operaciones.RESTA.name());
        JMenuItem itemMultiplicacion = new JMenuItem(Operaciones.MULTIPLICACION.name());
        JMenuItem itemDivision = new JMenuItem(Operaciones.DIVISION.name());

        menu.add(itemSuma);
        menu.add(itemResta);
        menu.add(itemMultiplicacion);
        menu.add(itemDivision);

        JMenu menuSalir = new JMenu("Menu salir");
        JMenuItem itemSalir = new JMenuItem("Salir");
        menuSalir.add(itemSalir);

        bar.add(menu);
        bar.add(menuSalir);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel(" Numero 1: "));
        panel.add(numberA = new JTextField(3));
        panel.add(new JLabel(" Numero 2: "));
        panel.add(numberB = new JTextField(3));
        panel.add(new JLabel(" Resultado: "));
        panel.add(result = new JTextField(5));
        result.setEnabled(false);

        itemSuma.addActionListener(this);
        itemResta.addActionListener(this);
        itemMultiplicacion.addActionListener(this);
        itemDivision.addActionListener(this);
        itemSalir.addActionListener(this);

        container.add(panel, BorderLayout.CENTER);

        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Window8Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operacion = e.getActionCommand();
        int numero1 = Integer.valueOf(numberA.getText().trim());
        int numero2 = Integer.valueOf(numberB.getText().trim());
        int value;

        //Usando un Enum
        //value = Operaciones.SUMA.calcular(numero1, numero2);

        // Usando Map
        value = this.operaciones(operacion, numero1, numero2);
        result.setText(String.valueOf(value));

    }

    public int operaciones(String operacion, int n1, int n2){
        /*
        * Map almacena una clave K y un valor V, en este caso
        * K es el String ej: SUMA
        * V es una expresion lambda BiFunction
        * */

        // Usando map.of a partir de jdk 9
        /*
        Map<String, BiFunction<Integer, Integer, Integer>> operaciones = Map.of(
                "SUMA", (a, b) -> a + b,
                "RESTA", (a, b) -> a - b,
                "MULTIPLICACION", (a, b) -> a * b,
                "DIVISION", (a, b) -> b != 0 ? a / b : 0
        );
        */

        //JDK8 style
        Map<String, BiFunction<Integer, Integer, Integer>> operaciones = new HashMap<>();
        operaciones.put("SUMA", (a, b) -> a + b);
        operaciones.put("RESTA", (a, b) -> a - b);
        operaciones.put("MULTIPLICACION", (a, b) -> a * b);
        operaciones.put("DIVISION", (a, b) -> b != 0 ? a / b : 0);

        //operaciones.getOrDefault(operacion, (a, b) -> 0)
        // getOrDefault(operacion, (a, b) -> 0) busca la clave operacion, y en caso que no exista
        // ejecuta la expresion (a, b) -> 0
        // en caso de conseguir la clave ejecuta el BiFunction con apply(n1, n2)
        return  operaciones.getOrDefault(operacion, (a, b) -> 0).apply(n1, n2);
    }
}
