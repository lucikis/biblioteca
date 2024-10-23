package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import modele.Abonat;
import modele.Articol;
import servicii.Biblioteca;

public class DialogAfisareImprumuturi extends JDialog {

    private Biblioteca biblioteca;

    public DialogAfisareImprumuturi(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("Lista Imprumuturilor");
        setModal(true);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        List<LoanEntry> loanEntries = new ArrayList<>();

        for (Abonat abonat : biblioteca.getListaAbonati()) {
            for (Articol articol : abonat.getArticoleImprumutate()) {
                loanEntries.add(new LoanEntry(abonat, articol));
            }
        }

        if (loanEntries.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nu exista imprumuturi inregistrate in momentul de fata.", "Informare", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }

        String[] columnNames = {"Titlu Articol", "ISBN", "Nume Abonat", "CNP Abonat"};
        Object[][] data = new Object[loanEntries.size()][columnNames.length];

        for (int i = 0; i < loanEntries.size(); i++) {
            LoanEntry entry = loanEntries.get(i);
            Articol articol = entry.getArticol();
            Abonat abonat = entry.getAbonat();

            data[i][0] = articol.getTitlu();
            data[i][1] = articol.getCod();
            data[i][2] = abonat.getNumeAbonat();
            data[i][3] = abonat.getCNP();
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

        JScrollPane scrollPane = new JScrollPane(table);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public class LoanEntry {
        private Abonat abonat;
        private Articol articol;

        public LoanEntry(Abonat abonat, Articol articol) {
            this.abonat = abonat;
            this.articol = articol;
        }

        public Abonat getAbonat() {
            return abonat;
        }

        public Articol getArticol() {
            return articol;
        }
    }
}