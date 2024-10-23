package ui;

import modele.Carte;
import servicii.Biblioteca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DialogAfisareCarti extends JDialog {
    private Biblioteca biblioteca;
    private JTable table;
    private DefaultTableModel tableModel;

    public DialogAfisareCarti(Frame parent, Biblioteca biblioteca) {
        super(parent, "Lista de Carți", true);
        this.biblioteca = biblioteca;

        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Titlu");
        tableModel.addColumn("Autor(i)");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("Editura");
        tableModel.addColumn("Data Publicarii");
        tableModel.addColumn("Limba");
        tableModel.addColumn("Descriere");

        populateTable();

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(800, 400));
        pack();
    }

    private void populateTable() {
        List<Carte> listaImprumutabil = biblioteca.getListaCartiDisponibile();

        for (Carte imprumutabil : listaImprumutabil) {
            tableModel.addRow(new Object[]{
                    imprumutabil.getTitlu(),
                    String.join(", ", imprumutabil.getAutori()),
                    imprumutabil.getIsbn(),
                    imprumutabil.getEditura(),
                    imprumutabil.getDataPublicare(),
                    imprumutabil.getLimba(),
                    imprumutabil.getDescriere()
            });
        }
    }
}