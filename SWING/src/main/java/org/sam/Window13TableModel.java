package org.sam;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window13TableModel extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new Window13TableModel();
    }

    public Window13TableModel() throws HeadlessException {
        super("Tabla con registros");

        JTable table = new JTable(new UserTableModel());
        JScrollPane scroll = new JScrollPane(table);
        
        JPanel panel = new JPanel();
        panel.add(scroll);

        getContentPane().add(panel);

        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private static class UserTableModel extends AbstractTableModel {

        private String[] columns = {"Id", "name", "lastname", "email"};
        private Object[][] rows;

        public UserTableModel() {
            this.rows = new Object[5][4];
            rows[0] = new Object[]{1, "Andres", "Guzman", "andres@gmail.com"};
            rows[1] = new Object[]{2, "Veronica", "Andreina", "vero@gmail.com"};
            rows[2] = new Object[]{3, "Valeria", "Andrea", "valeria@gmail.com"};
            rows[3] = new Object[]{4, "Stefany", "Vaca", "stefanyv@gmail.com"};
            rows[4] = new Object[]{5, "Manuel", "Alfonzo", "sam@gmail.com"};
        }

        @Override
        public int getRowCount() {
            return rows.length;
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rows[rowIndex][columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
    }
}
