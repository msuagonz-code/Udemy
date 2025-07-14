package org.sam;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Window9Calculator2 extends JFrame implements ActionListener {

    Container contenedorFlow;
    String total = "";
    String operation = "";
    int aux = 0;

    public static final String OPERADOR_SUMA = "+";
    public static final String OPERADOR_RESTA = "-";
    public static final String OPERADOR_MULTIPLICACION = "*";
    public static final String OPERADOR_DIVISION = "/";
    public static final String OPERADOR_RESULTADO = "=";
    public static final String OPERADOR_RESET = "AC";

    JLabel result;
    JLabel view;

    public Window9Calculator2() throws HeadlessException {
        super("Caluladora");
        Container contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout(5,5));

        // 2 filas
        // 1 columna
        // separacion horizontal 5
        // separacion vertical 5
        JPanel panelTop = new JPanel( new GridLayout(2, 1, 5, 5));
        JPanel panelCenter = new JPanel( new GridLayout(5, 3, 5, 5));
        JPanel panelRight = new JPanel( new GridLayout(5, 1, 5, 5));

        contenedor.add(panelTop, BorderLayout.NORTH);
        contenedor.add(panelCenter, BorderLayout.CENTER);
        contenedor.add(panelRight, BorderLayout.EAST);

        panelTop.add(this.view = new JLabel("", SwingConstants.RIGHT));
        panelTop.add(this.result = new JLabel("0", SwingConstants.RIGHT));

        this.view.setFont(new Font("JetBrainsMonoNL Nerd Font Propo", Font.PLAIN, 16));
        this.result.setFont(new Font("JetBrainsMonoNL Nerd Font Propo", Font.BOLD, 20));

        Border outSideBorde = panelTop.getBorder();
        Border insideBorder = new EmptyBorder(10, 10, 10,10);
        panelTop.setBorder(new CompoundBorder(outSideBorde, insideBorder));

        JButton botonAC = new JButton(OPERADOR_RESET);
        JButton botonSumar = new JButton(OPERADOR_SUMA);
        JButton botonRestar = new JButton(OPERADOR_RESTA);
        JButton botonMultiplicar = new JButton(OPERADOR_MULTIPLICACION);
        JButton botonDividir = new JButton(OPERADOR_DIVISION);
        JButton botonResultado = new JButton(OPERADOR_RESULTADO);

        panelCenter.add(botonAC);
        panelCenter.add(new JLabel());
        panelCenter.add(new JLabel());
        for(int i = 9; i >= 0; i--){
            JButton botonNumero = new JButton(String.valueOf(i));
            panelCenter.add(botonNumero);
            botonNumero.addActionListener(event -> {
                String valor = event.getActionCommand();
                total = total.concat(valor);
                this.view.setText(this.view.getText().concat(valor));
                System.out.println("total = " + total);
            });
        }

        panelRight.add(botonSumar);
        panelRight.add(botonRestar);
        panelRight.add(botonMultiplicar);
        panelRight.add(botonDividir);
        panelRight.add(botonResultado);

        botonSumar.addActionListener(this);
        botonRestar.addActionListener(this);
        botonMultiplicar.addActionListener(this);
        botonDividir.addActionListener(this);

        botonResultado.addActionListener(event -> {

            String op = this.operation;
            int value = 0;
            int numA = this.aux;
            int numB = Integer.parseInt(this.total);

            // Map de metodos
            Map<String, BiFunction<Integer, Integer, Integer>> operaciones = new HashMap<>();
            operaciones.put(OPERADOR_SUMA, Window9Calculator2::suma);
            operaciones.put(OPERADOR_RESTA, Window9Calculator2::resta);
            operaciones.put(OPERADOR_MULTIPLICACION, Window9Calculator2::multiplicacion);
            operaciones.put(OPERADOR_DIVISION, Window9Calculator2::division);

            value = operaciones.getOrDefault(op, (a, b) -> 0).apply(numA, numB);

            total = String.valueOf(value);
            result.setText(total);
        });

        botonAC.addActionListener(event -> {
            this.total = "";
            this.operation = "";
            this.aux = 0;
            this.view.setText("");
            this.result.setText("0");
        });

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Window9Calculator2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.operation = e.getActionCommand();
        this.aux = Integer.parseInt(this.total);
        this.total = "";
        this.view.setText(this.view.getText().concat(this.operation));
    }

    // Métodos que serán referenciados
    public static int suma(int a, int b) {
        return a + b;
    }

    public static int resta(int a, int b) {
        return a - b;
    }

    public static int multiplicacion(int a, int b) {
        return a * b;
    }

    public static int division(int a, int b) {
        return b != 0 ? a / b : 0;  // Evitar división por cero
    }

}
