package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import modele.Abonat;
import servicii.Biblioteca;

public class DialogAbonati extends JDialog {

    private Biblioteca biblioteca;

    public DialogAbonati(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("Lista Abonatilor");
        setModal(true);
        setSize(600, 400);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        List<Abonat> listaAbonati = biblioteca.getListaAbonati();

        if (listaAbonati.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nu exista abonati inregistrati in momentul de fata.", "Informare", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }

        String[] columnNames = {"Nume", "CNP", "Numar Articole Imprumutate"};
        Object[][] data = new Object[listaAbonati.size()][columnNames.length];

        for (int i = 0; i < listaAbonati.size(); i++) {
            Abonat abonat = listaAbonati.get(i);
            data[i][0] = abonat.getNumeAbonat();
            data[i][1] = abonat.getCNP();
            data[i][2] = abonat.getArticoleImprumutate().size();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }
}