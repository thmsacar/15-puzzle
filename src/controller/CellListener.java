package controller;

import model.ModelGrill;
import view.Cell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class of a listener for {@link Cell}
 */
public class CellListener implements ActionListener, MouseListener {

    private ModelGrill grille;

    public CellListener(ModelGrill grille) {
        this.grille = grille;
    }

    /**
     * Swap the clicked {@link Cell} with empty cell if clicked cell is playable.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        int pos = Integer.parseInt(e.getActionCommand());

        //If this button is playable then play it
        if(grille.getPlayable().contains(pos)){
            grille.swap(pos, grille.getEmptyCell());
        }else {
            System.err.println("Impossible move!");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    /**
     * Change the button to {@link Cell#HOVER_COLOR} if the button is playable when hovered over
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e){
        Cell c = (Cell) e.getSource();
        if(c.getPlayable()){
            c.setBackground(Cell.HOVER_COLOR);
        }
    }

    /**
     * Change the button to {@link Cell#BUTTON_COLOR} when no longer hovered over
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e){
        Cell c = (Cell) e.getSource();
        c.setBackground(Cell.BUTTON_COLOR);
    }

    /**
     * Change the button to {@link Cell#CLICK_COLOR} if button is playable when pressed
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e){
        Cell c = (Cell) e.getSource();
        if(c.getPlayable()){
            c.setBackground(Cell.CLICK_COLOR);
        }
    }

    /**
     * Change the button to {@link Cell#BUTTON_COLOR} when no longer pressed
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e){
        Cell c = (Cell) e.getSource();
        c.setBackground(Cell.BUTTON_COLOR);
    }
}
