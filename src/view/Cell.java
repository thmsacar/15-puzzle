package view;

import javax.swing.*;
import java.awt.*;

/**
 * Class representing a Cell of the Game
 */
public class Cell extends JButton{

	public static final Color TEXT_COLOR = new Color(40, 40, 40);
	public static final Color HOVER_COLOR = new Color(220, 120, 100);
	public static final Color CLICK_COLOR = HOVER_COLOR.darker();
	public static final Color BUTTON_COLOR = new Color(90, 170, 230);

	private boolean playable;


	public Cell(String name) {
		super(name);

		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setPreferredSize(new Dimension(120, 120));

		this.setBackground(Cell.BUTTON_COLOR);
		this.setForeground(Cell.TEXT_COLOR);
		this.setOpaque(true);
		this.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 35));

		setPreferredSize(new Dimension(120, 120));

		this.setEnabled(false);
		this.playable = false;

	}

	
	/**
	 * Passe l'attribut playable à true, 
	 * enable le bouton et ajoute un 
	 * MouseListener à l'aide de la méthode hoverable
	 * @param playable
	 * **/
	public void setPlayable(boolean playable){
		this.playable = playable;
		this.setEnabled(playable);
	}

	public boolean getPlayable(){
		return this.playable;
	}
	
	/**
	 * Définit la couleur de fond de la cellule à un bleu clair
	 */
	public void resetColor(){
		this.setBackground(Cell.BUTTON_COLOR);
	}

	/**
	 * Cette fonction override le design Swing par défaut des boutons, pour mettre un joli dégradé bleu. 
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int w = getWidth();
		int h = getHeight();

		Color baseColor = getBackground();
		Color lightColor = baseColor.brighter();
		Color darkColor = baseColor.darker();

		//Outer gradient
		GradientPaint outerGp = new GradientPaint(0, 0, lightColor, w * 0.8f, h, darkColor);
		g2.setPaint(outerGp);
		g2.fillRect(0, 0, w, h);

		int margin = w / 10;
		GradientPaint innerGp = new GradientPaint(0, margin, darkColor, w * 0.8f, h - margin, lightColor);
		g2.setPaint(innerGp);
		g2.fillOval(margin, margin, w - (2*margin), h - (2*margin));

		if (getText() != null) {
			g2.setFont(getFont());
			FontMetrics fm = g2.getFontMetrics();
			int x = (w - fm.stringWidth(getText())) / 2;
			int y = (h - fm.getHeight()) / 2 + fm.getAscent();

			g2.setColor(new Color(255, 255, 255, 40));
			g2.drawString(getText(), x, y + 1);

			g2.setColor(getForeground());
			g2.drawString(getText(), x, y);
		}
		g2.dispose();
	}







}
