package org.sam;

import org.sam.models.Product;
import org.sam.repositories.ProductRepository;
import org.sam.repositories.impl.ProductRepositoryImpl;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class JdbcSwingCrudProject extends JFrame {

    private Container panel;
    private JTextField nameField = new JTextField();
    private JTextField priceField = new JTextField();
    private JTextField quantityField = new JTextField();
    private ProductTableModel tableModel = new ProductTableModel();
    private int row;
    private Long id;
    private Long lastId;
    private ProductRepository productRepository;

    private static final String GUARDAR = "Guardar";

    public JdbcSwingCrudProject() {
        super("Swing: GUI con base de Datos Oracle SQL");
        productRepository = new ProductRepositoryImpl();
        id = 0L;

        this.panel = getContentPane();
        panel.setLayout(new BorderLayout(20, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 20, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JButton buttonSave = new JButton(GUARDAR);

        formPanel.add(new JLabel("Nombre: "));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Precio: "));
        formPanel.add(priceField);

        formPanel.add(new JLabel("Cantidad: "));
        formPanel.add(quantityField);

        JLabel emoji = new JLabel("🚀");
        emoji.setFont(new java.awt.Font("Segoe UI Emoji", java.awt.Font.PLAIN, 18)); // Fuente compatible

        formPanel.add(emoji);
        formPanel.add(buttonSave);

        buttonSave.addActionListener(e -> {
           String name = nameField.getText();
           int price = 0;
           int quantity = 0;

           try{
                price = Integer.parseInt(priceField.getText());
           } catch (NumberFormatException ex) {}

           try {
               quantity = Integer.parseInt(quantityField.getText());
           } catch (NumberFormatException ex) {}

           List<String> errors = new ArrayList<>();
           if(name.isBlank()){
               errors.add("Debe ingresar el nombre!");
           }

           if(price == 0){
               errors.add("Debe ingresar un precio valido");
           }

           if(quantity == 0){
               errors.add("Debe ingresar una cantidad valida");
           }

           if(errors.size() > 0){
                JOptionPane.showMessageDialog(null, errors.toArray(),
                        "Error en la validación!",
                        JOptionPane.ERROR_MESSAGE);
           }else{

                if(id == 0){ // Insert
                    id = lastId + 1; // nuevo ID
                    Object[] product = new Object[]{id, name, price, quantity, "Eliminar"};
                    tableModel.getRows().add(product);
                    tableModel.fireTableDataChanged();
                } else if ( id > 0) { // Update
                    tableModel.setValueAt(id, row, 0);
                    tableModel.setValueAt(name, row, 1);
                    tableModel.setValueAt(price, row, 2);
                    tableModel.setValueAt(quantity, row, 3);
                }

               productRepository.save(new Product(id, name, price, quantity));
               reset();
           }

        });

        JPanel tablePanel = new JPanel(new FlowLayout());
        JTable jtable = new JTable();
        jtable.setModel(tableModel);
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = jtable.rowAtPoint(e.getPoint());
                int colum = jtable.columnAtPoint(e.getPoint());

                if(row > -1 && colum == 4){

                int option = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas eliminar el registro "+
                        tableModel.getValueAt(row, 1).toString()
                        +"?", "Atención Eliminar Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if(option == JOptionPane.OK_OPTION){
                        productRepository.delete((long) tableModel.getValueAt(row, 0));
                        tableModel.getRows().remove(row);
                        tableModel.fireTableDataChanged();
                    }

                    reset();

                }else if(row > -1 && colum > -1){
                    id = (long) tableModel.getValueAt(row, 0);
                    nameField.setText(tableModel.getValueAt(row, 1).toString());
                    priceField.setText(tableModel.getValueAt(row, 2).toString());
                    quantityField.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        JScrollPane scroll = new JScrollPane(jtable);
        tablePanel.add(scroll);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void reset() {
        id = 0l;
        row = -1;
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }

    public static void main(String[] args) {
        new JdbcSwingCrudProject();
    }

    private class ProductTableModel extends AbstractTableModel {


        private String[] columns = new String[]{"Id", "Nombre", "Precio", "Cantidad", "Eliminar"};
        private List<Object[]> rows = new ArrayList<>();

        public ProductTableModel() {
        ProductRepository productRepository = new ProductRepositoryImpl();
            List<Product> listaProdictos = productRepository.findAll();
            lastId = (long) listaProdictos.size();

            for (Product product: listaProdictos){
                Object[] row = {product.getId(), product.getName(), product.getPrice(), product.getQuantity(), "Eliminar"};
                rows.add(row);
            }

        }

        public List<Object[]> getRows() {
            return rows;
        }

        public String getColumnName(int index) {
            return columns[index];
        }

        @Override
        public int getRowCount() {
            return this.rows.size();
        }

        @Override
        public int getColumnCount() {
            return this.columns.length;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            this.rows.get(rowIndex)[columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return this.rows.get(rowIndex)[columnIndex];
        }
    }
}
