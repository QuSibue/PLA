package ricm3.game.framework;

import java.awt.Graphics;

import javax.swing.JComponent;

public abstract class GameATH extends JComponent{

	private static final long serialVersionUID = 1L;
	public GameUI m_game;
	
	public abstract void ATHVisible();
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
