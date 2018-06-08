package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Orientation;
import ricm3.game.automaton.Transition;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Options;
import ricm3.game.other.Transversal;

public class Minion extends Character {
	public long m_lastMove;
	public int xOrigin;
	public int yOrigin;

	public Minion(BufferedImage[] sprites, int x, int y, Automaton automate, Orientation orientation, int equipe,
			Map map, Model model, int life, long lastMove) {
		super(sprites, x, y, true, true, true, false, Options.MINION_MS, automate, orientation, equipe, map, model,
				life, lastMove);
		xOrigin = this.getX();
		yOrigin = this.getY();

	}

	public void pop() {
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
		Portal p = new Portal(xOrigin, yOrigin, xCourant, yCourant, null, global_map, m_model);
		global_map.setEntity(p); // enlever les commentaires quand la liste de portail sera dans model
		// m_model.m_portail.add(p);
		this.global_map.deleteEntity(this);
		m_model.m_minions.remove(this);
	}

	@Override
	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		Transversal.evalPosition(this.getX(), this.getY(), p, d, this.getOrientation());
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
	public void step(long now) {

		long elapsed = now - m_lastMove;
		if (elapsed > 300L) {
			m_lastMove = now;
			Iterator<Transition> iter = this.getAutomaton().getTransitions().iterator();
			Transition transi;
			boolean condition = false;

			// On va chercher la première transition utilisable
			// puis on met a jour l'etat courant
			// et on effectue l'action associée a la transition

			// ce code va surement être deplacé dans being, superclass de player, minion et
			// laser
			while (!condition && iter.hasNext()) {
				transi = iter.next();
				// les etats sont par aliasing on peut donc utiliser le double égale
				condition = this.getEtatCourant() == transi.getInitial()
						&& transi.getCondition().eval((Being) this, global_map);
				if (condition) {
					this.setEtatCourant(transi.getSortie());
					transi.getAction().executeAction(this, now);
				}

			}
			if (!condition)
				throw new RuntimeException("AUcune transition trouvée pour l'automate dans player");
		}

	}

	@Override
	public void paint(Graphics g) {
		// affiche un carré bleu pour le joueur
		int m_x = this.getX() * Options.TAILLE_CASE;
		int m_y = this.getY() * Options.TAILLE_CASE;
		g.setColor(Color.RED);
		g.fillRect(m_x, m_y, Options.TAILLE_CASE, Options.TAILLE_CASE);

	}

	@Override
	public void turn(Direction d) {
	}

	@Override
	public void kamikaze() {
		// TODO Auto-generated method stub

	}

}
