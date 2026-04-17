package view;

import javax.swing.*;
import java.awt.*;
import model.*;

/**
 * Frame that holds the game panels
 */
public class TaquinFrame extends JFrame {

	/**
	 * Initialize the frame with MenuPanel
	 */
	public TaquinFrame(){
		super("15-Puzzle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);

		this.setContentPane(new MenuPanel(this));
		this.setLocationRelativeTo(null);
	}

	/**
	 * Generates {@link ModelGrill} then adds {@link GamePanel} and {@link InfoPanel} to the frame.
	 * @param lignes number of lines
	 * @param colonnes number of columns
	 */
	public void startGame(int lignes, int colonnes) {
		ModelGrill grille = new ModelGrill(lignes, colonnes);
		grille.shuffle();

		this.getContentPane().removeAll();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		mainPanel.setBackground(Cell.BUTTON_COLOR.darker().darker());

		JPanel gamePanel = new GamePanel(grille);
		mainPanel.add(gamePanel, BorderLayout.CENTER);

		JPanel infoPanel = new InfoPanel(grille);
		mainPanel.add(infoPanel, BorderLayout.EAST);

		this.setContentPane(mainPanel);
		this.revalidate();
		this.repaint();
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setLocationRelativeTo(null);

		gamePanel.requestFocusInWindow();
	}
}
