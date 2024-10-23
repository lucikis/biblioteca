package ui;

import servicii.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPrincipal extends JPanel {
    Biblioteca biblioteca = new Biblioteca();

    public PanelPrincipal() {
        setLayout(new BorderLayout());

        JMenuBar menuBar = createMenuBar();
        JPanel dashboardPanel = createDashboardPanel();

        add(menuBar, BorderLayout.NORTH);
        add(dashboardPanel, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu meniuInventar = new JMenu("Inventar");
        JMenuItem afisareCartiDisponibile = new JMenuItem("Afiseaza Cartile Disponibile");
        afisareCartiDisponibile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogInventar dialogCartiDisponibile = new DialogInventar(biblioteca);
                dialogCartiDisponibile.setVisible(true);
            }
        });
        meniuInventar.add(afisareCartiDisponibile);

        JMenu meniuAbonati = new JMenu("Abonati");
        JMenuItem afiseazaAbonati = new JMenuItem("Afiseaza Abonatii");
        afiseazaAbonati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAbonati dialogAbonati = new DialogAbonati(biblioteca);
                dialogAbonati.setVisible(true);
            }
        });
        meniuAbonati.add(afiseazaAbonati);

        JMenu meniuImprumuturi = new JMenu("Imprumuturi");
        JMenuItem afiseazaImprumuturile = new JMenuItem("Afiseaza Imprumuturile");
        afiseazaImprumuturile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAfisareImprumuturi dialogAfisareImprumuturi = new DialogAfisareImprumuturi(biblioteca);
                dialogAfisareImprumuturi.setVisible(true);
            }
        });
        meniuImprumuturi.add(afiseazaImprumuturile);

        menuBar.add(meniuInventar);
        menuBar.add(meniuAbonati);
        menuBar.add(meniuImprumuturi);

        return menuBar;
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        JButton adaugaArticolButton = new JButton("Adauga Articol");
        adaugaArticolButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deschideDialogAdaugaArticol();
                    }
                }
        );

        JButton adaugaAbonatButton = new JButton("Adauga Abonat");
        adaugaAbonatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAdaugaAbonat abonatDialog = new DialogAdaugaAbonat(biblioteca);
                abonatDialog.setVisible(true);
            }
        });

        JButton imprumutaArticolButton = new JButton("Imprumuta Articol");
        imprumutaArticolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogImprumuta borrowingDialog = new DialogImprumuta(biblioteca);
                borrowingDialog.setVisible(true);
            }
        });

        JButton returneazaArticolButton = new JButton("Returneaza Articol");
        returneazaArticolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogReturneaza returnDialog = new DialogReturneaza(biblioteca);
                returnDialog.setVisible(true);
            }
        });

        panel.add(adaugaArticolButton);
        panel.add(adaugaAbonatButton);
        panel.add(imprumutaArticolButton);
        panel.add(returneazaArticolButton);

        return panel;
    }

    private void deschideDialogAdaugaArticol() {
        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        DialogAdaugaArticol dialog = new DialogAdaugaArticol(parentFrame, biblioteca);
        dialog.setVisible(true);
    }

}