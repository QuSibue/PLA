package ricm3.game.framework;

import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JComponent;

public abstract class GameATH extends JComponent{

	private static final long serialVersionUID = 1L;
	protected GameUI m_game;
	
	public abstract Container init();
	
	public abstract void ATHVisible();
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
