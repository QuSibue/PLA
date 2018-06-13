package ricm3.game.entity;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.swing.ImageIcon;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Orientation;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;

public class Minion extends Character {
	public long m_lastMove;
	public int xOrigin;
	public int yOrigin;

	public Minion(BufferedImage[][] sprites, int nbImage, ImageIcon icon, int x, int y, boolean moveable,
			boolean pickable, boolean killable, boolean lethal, int moveSpeed, Automaton automate,
			Orientation orientation, int equipe, Map map, Model model, int life, long lastMove) {
		super(sprites, nbImage, icon, x, y, moveable, pickable, killable, lethal, moveSpeed, automate, orientation,
				equipe, map, model, life, lastMove);

		xOrigin = this.getX();
		yOrigin = this.getY();
	}

	public void pop(long now) {
		int x = this.getX();
		int y = this.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i != x && j != y) {
					Entity e = global_map.getEntity(i, j);
					if (e != null) {
						if (e instanceof Being) {
							((Being) e).getDamage();
						}
					}
				}
			}
		}
	}

	public void wizz() {
		int xCourant = this.getX();
		int yCourant = this.getY();
		Portal p = new Portal(xOrigin, yOrigin, xCourant, yCourant, m_model.m_idb.portail, m_model.m_idb.nbFramePortail, m_model.m_icb.m_energieSac, global_map, m_model);
		global_map.setEntity(p); // enlever les commentaires quand la liste de portail sera dans model
		// m_model.m_portail.add(p);
		this.global_map.deleteEntity(this);
		m_model.m_minions.remove(this);
	}

	public void hit(long now) {
		Iterator<Minion> iterM = m_model.m_minions.iterator();
		Entity closest = null;
		if (this.getEquipe() == m_model.virus.getEquipe()) {
			closest = m_model.antivirus;
		} else {
			closest = m_model.virus;

		}
		while (iterM.hasNext()) {
			Minion m = iterM.next();
			if (m.getEquipe() != this.getEquipe()) {
				closest = this.closestEntity(closest, m);

			}
		}

	}

	public void power(long now) {
		return;
	}

	public void protect() {
		return;
	}

	public void jump() { // Non implémenté
		return;
	}

	public void pick() {
		return;
	}

	public void get() {
		return;
	}

	public void store() {
		return;
	}

	public void _throw() {
		return;
	}

	@Override
	public void kamikaze() {

	}

}