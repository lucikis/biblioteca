package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

import modele.Carte;
import servicii.Biblioteca;

public class DialogInventar extends JDialog {

    private Biblioteca biblioteca;

    public DialogInventar(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("Carti Disponibile");
        setModal(true);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        List<Carte> listaCartiDisponibile = biblioteca.getListaCartiDisponibile();

        if (listaCartiDisponibile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nu exista carti disponibile in momentul de fata.", "Informare", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }

        String[] columnNames = {"Titlu", "Autor(i)", "ISBN", "Editura", "Limba", "Data Publicare", "Exemplare Disponibile"};
        Object[][] data = new Object[listaCartiDisponibile.size()][columnNames.length];

        for (int i = 0; i < listaCartiDisponibile.size(); i++) {
            Carte carte = listaCartiDisponibile.get(i);

            data[i][0] = carte.getTitlu();
            data[i][1] = (carte.getAutori() != null ? String.join(", ", carte.getAutori()) : "Fara autori");
            data[i][2] = carte.getIsbn();
            data[i][3] = carte.getEditura();
            data[i][4] = carte.getLimba();
            data[i][5] = carte.getDataPublicare();
            data[i][6] = carte.getNumarExemplareDisponibile();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);

        JTextField searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                filter();
            }
            public void removeUpdate(DocumentEvent e) {
                filter();
            }
            public void changedUpdate(DocumentEvent e) {
                filter();
            }
            private void filter() {
                String text = searchField.getText();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(new JLabel("Cauta: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}