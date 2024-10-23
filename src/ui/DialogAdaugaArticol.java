package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

import modele.Carte;
import servicii.api.GoogleBooksApiClient;
import servicii.api.Item;
import servicii.api.VolumeInfo;
import servicii.Biblioteca;

public class DialogAdaugaArticol extends JDialog {
    private JTextField txtTitlu;
    private JTextField txtAutor;
    private JButton btnSearch;
    private GoogleBooksApiClient googleBooksApiClient;
    private Biblioteca biblioteca;
    private boolean isSucceeded = false;

    public DialogAdaugaArticol(Frame parent, Biblioteca biblioteca) {
        super(parent, "Adauga Articol Nou", true);
        this.biblioteca = biblioteca;
        googleBooksApiClient = new GoogleBooksApiClient();
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel labelTitlu = new JLabel("Titlu:");
        txtTitlu = new JTextField();
        JLabel labelAutor = new JLabel("Autor:");
        txtAutor = new JTextField();
        btnSearch = new JButton("Cauta");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBooks();
            }
        });
        panel.add(labelTitlu);
        panel.add(txtTitlu);
        panel.add(labelAutor);
        panel.add(txtAutor);
        panel.add(new JLabel());
        panel.add(btnSearch);
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    }

    public boolean isSucceeded() {
        return isSucceeded;
    }

    private void searchBooks() {
        String title = txtTitlu.getText().trim();
        String author = txtAutor.getText().trim();
        if (title.isEmpty() && author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduceti titlul sau autorul.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }
        btnSearch.setEnabled(false);
        new Thread(() -> {
            try {
                List<Item> items = googleBooksApiClient.searchBooks(title, author, 10);
                if (items != null && !items.isEmpty()) {
                    SwingUtilities.invokeLater(() -> {
                        displayResults(items);
                        btnSearch.setEnabled(true);
                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, "Nu au fost gasite rezultate.", "Info", JOptionPane.INFORMATION_MESSAGE);
                        btnSearch.setEnabled(true);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Eroare de retea.", "Eroare", JOptionPane.ERROR_MESSAGE);
                    btnSearch.setEnabled(true);
                });
            } catch (IllegalArgumentException e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
                    btnSearch.setEnabled(true);
                });
            }
        }).start();
    }

    private void displayResults(List<Item> items) {
        DefaultListModel<Item> listModel = new DefaultListModel<>();
        for (Item item : items) {
            listModel.addElement(item);
        }
        JList<Item> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setCellRenderer(new BookCellRenderer());
        JScrollPane scrollPane = new JScrollPane(list);
        int result = JOptionPane.showConfirmDialog(this, scrollPane, "Selectati carti", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            List<Item> selectedItems = list.getSelectedValuesList();
            if (selectedItems != null && !selectedItems.isEmpty()) {
                addBooksToDatabase(selectedItems);
            } else {
                JOptionPane.showMessageDialog(this, "Nu ati selectat nici o carte.", "Info", JOptionPane.INFORMATION_MESSAGE);
                btnSearch.setEnabled(true);
            }
        } else {
            btnSearch.setEnabled(true);
        }
    }

    private void addBooksToDatabase(List<Item> selectedItems) {
        StringBuilder message = new StringBuilder("Au fost adaugate urmatoarele carti:\n");
        for (Item selectedItem : selectedItems) {
            VolumeInfo info = selectedItem.volumeInfo;
            Carte carte = new Carte(info.titlu, info.getIsbn13(), info.dataPublicarii, info.limba, info.descriere, info.getNumeAutori(), info.editura);
            biblioteca.adaugaArticol(carte);
            message.append("Titlu: ").append(info.titlu).append("\n");
            message.append("Autori: ").append(info.autori != null ? String.join(", ", info.autori) : "Fara autori").append("\n\n");
        }
        JOptionPane.showMessageDialog(this, message.toString(), "Carți adaugate", JOptionPane.INFORMATION_MESSAGE);
        isSucceeded = true;
        dispose();
    }

    private static class BookCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Item) {
                Item item = (Item) value;
                setText(item.toString());
            }
            return this;
        }
    }
}
