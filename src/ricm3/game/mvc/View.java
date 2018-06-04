package ricm3.game.mvc;

import java.awt.Color;
import java.awt.Graphics;

import ricm3.game.framework.GameView;;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = Color.gray;
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;
//	Controller m_ctr;

	public View(Model m) {
		m_model = m;
//		m_ctr = c;
	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, "npaints=" + m_npaints);
		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();

		// erase background
		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());

		// Paint our model, grabbing the elements,
		// in our case, the squares.

		/*
		 * Ici vous affichez vos entities Par exemple un mur
		 * 
		 * Wall w = m_model.m_laser; if (Options.SHOW_WALL){ w.paint(g) }
		 * 
		 * Attention Pas beaucoup de conditions ici
		 */
		
		m_model.virus.paint(g);

	}
}
