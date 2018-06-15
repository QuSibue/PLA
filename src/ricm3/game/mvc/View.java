package ricm3.game.mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import ricm3.game.entity.Laser;
import ricm3.game.entity.Minion;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Portal;
import ricm3.game.entity.PowerUp;
import ricm3.game.framework.GameView;
import ricm3.game.other.Options;;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = Color.gray;
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;
	// Controller m_ctr;

	public View(Model m) {
		m_model = m;
		// m_ctr = c;
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
		BufferedImage carte = m_model.m_idb.getMapSprite();
		g.drawImage(carte, 0, 0, Options.TAILLE_CASE * 29, Options.TAILLE_CASE * 15, null);

		// Paint our model, grabbing the elements,
		// in our case, the squares.

		/*
		 * Ici vous affichez vos entities Par exemple un mur
		 * 
		 * Wall w = m_model.m_laser; if (Options.SHOW_WALL){ w.paint(g) }
		 * 
		 * Attention Pas beaucoup de conditions ici
		 */

		if (m_model.virus.getLife() > 0) {
			m_model.virus.paint(g);
		}
		if (m_model.antivirus.getLife() > 0) {
			m_model.antivirus.paint(g);
		}

		if (!m_model.flagCaptured) {
			m_model.m_drapeau.paint(g);
		}

		LinkedList<Minion> minionsClone = (LinkedList<Minion>) m_model.m_minions.clone();
		Iterator<Minion> iterM = minionsClone.iterator();

		LinkedList<Obstacle> obstaclesClone = (LinkedList<Obstacle>) m_model.m_obstacles.clone();
		Iterator<Obstacle> iterO = obstaclesClone.iterator();

		LinkedList<Laser> laserClone = (LinkedList<Laser>) m_model.m_laser.clone();
		Iterator<Laser> iterL = laserClone.iterator();

		LinkedList<Portal> portalClone = (LinkedList<Portal>) m_model.m_portal.clone();
		Iterator<Portal> iterP2 = portalClone.iterator();
		
		LinkedList<PowerUp> powerClone = (LinkedList<PowerUp>) m_model.m_powerup.clone();
		Iterator<PowerUp> iterP = powerClone.iterator();
		
		
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

		Portal p2;
		while (iterP2.hasNext()) {
			p2 = iterP2.next();
			p2.paint(g);
		}
	}
}
