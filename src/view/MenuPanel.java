package view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for retrieving line and column information to start the game
 */
public class MenuPanel extends JPanel {

    public MenuPanel(TaquinFrame frame) {
        this.setLayout(new GridBagLayout());
        this.setBackground(Cell.BUTTON_COLOR.darker().darker());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel bienvenue = new JLabel("Welcome to 15-Puzzle");
        bienvenue.setForeground(Color.WHITE);
        bienvenue.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        this.add(bienvenue, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblLignes = new JLabel("Nb rows:");
        lblLignes.setForeground(Color.WHITE);
        lblLignes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(lblLignes, gbc);

        gbc.gridx = 1;
        JTextField txtLignes = new JTextField(5);
        txtLignes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(txtLignes, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblColonnes = new JLabel("Nb columns:");
        lblColonnes.setForeground(Color.WHITE);
        lblColonnes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(lblColonnes, gbc);

        gbc.gridx = 1;
        JTextField txtColonnes = new JTextField(5);
        txtColonnes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(txtColonnes, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton btnCommencer = new JButton("START");
        btnCommencer.setPreferredSize(new Dimension(150, 50));
        btnCommencer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btnCommencer.setForeground(Cell.TEXT_COLOR);
        btnCommencer.setFocusPainted(false);
        this.add(btnCommencer, gbc);

        btnCommencer.addActionListener(e -> {
            try {
                int l = Integer.parseInt(txtLignes.getText());
                int c = Integer.parseInt(txtColonnes.getText());

                if(l > 0 && c > 0) {
                    frame.startGame(l, c);
                } else {
                    JOptionPane.showMessageDialog(this, "Choose a value greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Value should be an integer", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}