package ui;

import modele.Abonat;
import modele.Articol;
import servicii.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DialogReturneaza extends JDialog {

    private Biblioteca biblioteca;
    private JComboBox<Abonat> abonatiComboBox;
    private JComboBox<Articol> articoleImprumutateComboBox;

    public DialogReturneaza(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        setTitle("Returneaza Articol");
        setModal(true);
        setSize(400, 200);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel abonatLabel = new JLabel("Selecteaza Abonat:");
        abonatiComboBox = new JComboBox<>();
        for (Abonat abonat : biblioteca.getListaAbonati()) {
            abonatiComboBox.addItem(abonat);
        }
        abonatiComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateArticoleImprumutateComboBox();
            }
        });

        JLabel articolLabel = new JLabel("Selecteaza Articol de Returnat:");
        articoleImprumutateComboBox = new JComboBox<>();

        JButton returneazaButton = new JButton("Returneaza");
        returneazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returneazaArticol();
            }
        });

        add(abonatLabel);
        add(abonatiComboBox);
        add(articolLabel);
        add(articoleImprumutateComboBox);
        add(new JLabel());
        add(returneazaButton);

        if (biblioteca.getListaAbonati().size() > 0) {
            updateArticoleImprumutateComboBox();
        }
    }

    private void updateArticoleImprumutateComboBox() {
        Abonat selectedAbonat = (Abonat) abonatiComboBox.getSelectedItem();
        articoleImprumutateComboBox.removeAllItems();
        if (selectedAbonat != null) {
            List<Articol> articoleImprumutate = selectedAbonat.getArticoleImprumutate();
            if (articoleImprumutate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Abonatul nu are articole imprumutate.");
            } else {
                for (Articol articol : articoleImprumutate) {
                    articoleImprumutateComboBox.addItem(articol);
                }
            }
        }
    }

    private void returneazaArticol() {
        Abonat selectedAbonat = (Abonat) abonatiComboBox.getSelectedItem();
        Articol selectedArticol = (Articol) articoleImprumutateComboBox.getSelectedItem();

        if (selectedAbonat != null && selectedArticol != null) {
            biblioteca.returnareArticol(selectedArticol, selectedAbonat);
            JOptionPane.showMessageDialog(this, "Articol returnat cu succes!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selectati un abonat si un articol de returnat.");
        }
    }
}