package ui;

import modele.Abonat;
import modele.Carte;
import servicii.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogImprumuta extends JDialog {
    private Biblioteca biblioteca;
    private JComboBox<Abonat> abonatiComboBox;
    private JComboBox<Carte> cartiComboBox;

    public DialogImprumuta(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        setTitle("Imprumuta Articol");
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

        JLabel articolLabel = new JLabel("Selecteaza Articol:");
        cartiComboBox = new JComboBox<>();
        for (Carte carte : biblioteca.getListaCartiDisponibile()) {
            cartiComboBox.addItem(carte);
        }

        JButton imprumutaButton = new JButton("Imprumuta");
        imprumutaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprumutaArticol();
            }
        });

        add(abonatLabel);
        add(abonatiComboBox);
        add(articolLabel);
        add(cartiComboBox);
        add(new JLabel());
        add(imprumutaButton);
    }

    private void imprumutaArticol() {
        Abonat selectedAbonat = (Abonat) abonatiComboBox.getSelectedItem();
        Carte selectedCarte = (Carte) cartiComboBox.getSelectedItem();

        if (selectedAbonat != null && selectedCarte != null) {
            if (selectedCarte.esteDisponibil()) {
                biblioteca.imprumutareArticol(selectedCarte, selectedAbonat);
                JOptionPane.showMessageDialog(this, "Articol imprumutat cu succes!");
                refreshCartiComboBox();
                if (cartiComboBox.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Nu mai sunt articole disponibile pentru împrumut.");
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Articolul nu este disponibil pentru imprumut.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Va rugam sa selectati un abonat si un articol.");
        }
    }

    private void refreshCartiComboBox() {
        cartiComboBox.removeAllItems();
        for (Carte carte : biblioteca.getListaCartiDisponibile()) {
            cartiComboBox.addItem(carte);
        }
    }
}