package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import controller.CellListener;
import model.*;
import util.*;
import controller.KeyboardListener;

/**
 *  Class representing GamePanel. Used for graphical interface.
 */
public class GamePanel extends JPanel implements ModelListener {


	ModelGrill grille;

	/*Stocking cells for memory management*/
	private Cell[][] cells;
	private int lines, colonnes;

	public GamePanel(ModelGrill grille){

		this.setBackground(Cell.BUTTON_COLOR.darker().darker().darker());
		this.setBorder(null);

		this.grille = grille;
		grille.addListener(this);

		this.lines = grille.getNbLines();
		this.colonnes = grille.getNbColumns();
		this.cells = new Cell[lines][colonnes];


		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		//Creates buttons for each position of grid
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < colonnes; j++) {
				gbc.gridx = j; gbc.gridy = i;
				cells[i][j] = new Cell("");
				cells[i][j].setActionCommand(String.valueOf(i*colonnes+j));

				CellListener listener = new CellListener(grille);
				cells[i][j].addActionListener(listener);
				cells[i][j].addMouseListener(listener);
				this.add(cells[i][j], gbc);
			}
		}
		KeyboardListener keyboardListener = new KeyboardListener(grille);
		this.addKeyListener(keyboardListener);
		this.setFocusable(true);

		this.updateModel();
		this.setMaximumSize(this.getSize());
	}

	/**
	 * Update the panel according to the current state of {@link ModelGrill}
	 */
	@Override
	public void updateModel() {
		ArrayList<Integer> playable = grille.getPlayable();
		for (int i = 0; i<lines; i++){
			for (int j=0; j<colonnes; j++){
				int pos = i*colonnes + j;
				int value = grille.getCase(pos);

				cells[i][j].setText(String.valueOf(value));
				cells[i][j].resetColor();
				//If this an empty case then don't show it
				if(value==-1){
					cells[i][j].setVisible(false);
				}
				//If this is a case with number then show it
				else {
					cells[i][j].setVisible(true);
					//If this is in a playable position case AND game is not finished -> set case playable
					if (playable.contains(pos) && !grille.isWin()) cells[i][j].setPlayable(true);
					//Else this case is not playable
					else cells[i][j].setPlayable(false);
				}
			}
		}
		this.requestFocusInWindow();
		this.repaint();
	}








}
