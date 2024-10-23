import ui.PanelPrincipal;

import javax.swing.*;

public class MainBiblioteca {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                creazaUIBiblioteca();
            }
        });
    }

    public static void creazaUIBiblioteca() {
        JFrame frame = new JFrame("Biblioteca Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);

        PanelPrincipal mainPanel = new PanelPrincipal();
        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }
}
