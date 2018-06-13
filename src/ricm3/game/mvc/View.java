package ricm3.game.mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import ricm3.game.entity.Laser;
import ricm3.game.entity.Minion;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.PowerUp;
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
		
		if(m_model.virus.getLife() > 0) {
			m_model.virus.paint(g);
		}
		if(m_model.antivirus.getLife() > 0) {
			m_model.antivirus.paint(g);
		}
		
		if(!m_model.flagCaptured) {
			m_model.m_drapeau.paint(g);
		}
		
		
		Iterator<Minion> iterM = m_model.m_minions.iterator();
		Iterator<Obstacle> iterO = m_model.m_obstacles.iterator();
		Iterator<Laser> iterL = m_model.m_laser.iterator();
		Iterator<PowerUp> iterP = m_model.m_powerup.iterator();
		Minion m;
		while (iterM.hasNext()) {
			m = iterM.next();
			m.paint(g);
		}
		
		Obstacle o;
		while (iterO.hasNext()) {
			o = iterO.next();
			o.paint(g);
		}
		Laser l;
		while (iterL.hasNext()) {
			l = iterL.next();
			l.paint(g);
		}
		
		PowerUp p;
		while (iterP.hasNext()) {
			p = iterP.next();
			p.paint(g);
		}

		

	}
}
