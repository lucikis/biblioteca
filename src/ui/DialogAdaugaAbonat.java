package ui;

import modele.Abonat;
import servicii.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogAdaugaAbonat extends JDialog {

    private Biblioteca biblioteca;
    private JTextField numeField;
    private JTextField cnpField;

    public DialogAdaugaAbonat(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        setTitle("Adauga Abonat");
        setModal(true);
        setSize(300, 200);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel numeLabel = new JLabel("Nume Abonat:");
        numeField = new JTextField();

        JLabel cnpLabel = new JLabel("CNP:");
        cnpField = new JTextField();

        JButton adaugaButton = new JButton("Adauga");
        adaugaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaugaAbonat();
            }
        });

        add(numeLabel);
        add(numeField);
        add(cnpLabel);
        add(cnpField);
        add(new JLabel());
        add(adaugaButton);
    }

    private void adaugaAbonat() {
        String nume = numeField.getText();
        String cnp = cnpField.getText();
        if (!nume.isEmpty() && !cnp.isEmpty()) {
            Abonat abonat = new Abonat(nume, cnp, new ArrayList<>());
            biblioteca.inregistreazaAbonat(abonat);
            JOptionPane.showMessageDialog(this, "Abonat adaugat cu succes!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Completati toate campurile.");
        }
    }
}
