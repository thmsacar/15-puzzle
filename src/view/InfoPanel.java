package view;

import model.ModelGrill;
import util.ModelListener;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for a shuffle button and number of moves
 */
public class InfoPanel extends JPanel implements ModelListener {

    ModelGrill grid;

    private JButton shakeButton;
    private JLabel moveCounter;
    private JLabel winStatus;

    private static final Color WIN_COLOR = new Color(50, 150, 0);

    public InfoPanel(ModelGrill grid) {

        this.grid = grid;
        grid.addListener(this);

        this.setLayout(new GridLayout(3, 1));
        this.setPreferredSize(new Dimension(200, 0));
        this.setOpaque(false);
        JPanel btnWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        btnWrapper.setOpaque(false);

        shakeButton = new JButton("SHUFFLE");
        shakeButton.setPreferredSize(new Dimension(120, 40));
        shakeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        shakeButton.setFocusPainted(false);
        shakeButton.addActionListener(e -> grid.shuffle());



        btnWrapper.add(shakeButton);
        this.add(btnWrapper);

        //moveCounter Label
        moveCounter = new JLabel(String.valueOf(grid.getNbMoves()), SwingConstants.CENTER);
        moveCounter.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        moveCounter.setForeground(Cell.TEXT_COLOR);
        this.add(moveCounter);

        //winStatus
        winStatus = new JLabel("", SwingConstants.CENTER);
        winStatus.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        winStatus.setBorder(BorderFactory.createEmptyBorder(20,0, 20, 0));
        winStatus.setForeground(WIN_COLOR);
        this.add(winStatus);

    }
    /**
     * This function updates the view, retrieving:
     * the counter using grid.getNbMoves() and updating the JLabel,
     * and changing the JLabel from "" to "WIN" in case of victory.
    */
    @Override
    public void updateModel() {
            int moves = grid.getNbMoves();
            moveCounter.setText(String.valueOf(moves));
            winStatus.setText(grid.isWin() ? "WIN" : "");
    }


}
