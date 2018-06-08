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
import sun.font.TrueTypeFont;

public class Minion extends Character {
	public long m_lastMove;
	public int xOrigin;
	public int yOrigin;

	public Minion(BufferedImage[] sprites, int x, int y, boolean moveable, boolean pickable, boolean killable,
			boolean lethal, int moveSpeed, Automaton automate, Orientation orientation, int equipe, Map map,
			Model model, int life, long lastMove) {
		super(sprites, x, y, moveable, pickable, killable, lethal, moveSpeed, automate, orientation, equipe, map, model,
				life, lastMove);
		xOrigin = this.getX();
		yOrigin = this.getY();
		// TODO FACTORISER LES PARAMETRE CONSTANTS ex un minion est toujours moveable
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
		Portal p = new Portal(xOrigin, yOrigin, xCourant, yCourant, null);
		global_map.setEntity(p); // enlever les commentaires quand la liste de portail sera dans model
		// m_model.m_portail.add(p);
		this.global_map.deleteEntity(this);
		m_model.m_minions.remove(this);

	}

	public void hit() {
		Iterator<Minion> iterM = m_model.m_minions.iterator();
		int x = this.getX();
		int y = this.getY();
		Direction d;
		Entity closest = null;
		if (this.getEquipe() == m_model.virus.getEquipe()) {
			closest = m_model.antivirus;
		} else {
			closest = m_model.virus;

		}
		while (iterM.hasNext()) {
			Minion m = iterM.next();
			if (m.getEquipe() != this.getEquipe()) {
				closest = this.closest(closest, m);

			}
		}
		if (closest.getY() < y) {
			// envoyer un missile au dessus de moi
			d = Direction.NORTH;
		} else if (closest.getX() > x) {
			// envoyer un missile à droite
			d = Direction.EAST;
		} else if (closest.getX() > y) {
			// envoyer un missile en bas
			d = Direction.SOUTH;
		} else if (closest.getX() < x) {
			// envoyer un missile à gauche
			d = Direction.WEST;
		}
		Point p = new Point();
		Transversal.evalPosition(this.getX(), this.getY(), p, d, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if (e == null) {
			Laser laser = new Laser(p.x, p.y, true, true, false, true, 100, null, Transversal.straightAutomaton(),
					this.getOrientation(), global_map, m_model, 1, 0);
			this.m_model.m_laser.add(laser);
			global_map.setEntity(laser);
		} else if (e instanceof Minion || e instanceof Player) {
			((Being) e).getDamage();
		} else if (e instanceof Laser) {
			global_map.deleteEntity(e);
			m_model.m_laser.remove(e);
		}
		/*
		 * for (int i = x - 3; i <= x + 3; i++) { for (int j = y - 3; j <= y + 3; j++) {
		 * Entity e = global_map.getEntity(i, j); if (e != null) { if (e instanceof
		 * Being{ xZone = i; yZone = j; } } }
		 * 
		 * }
		 */

	}

	public void power() { // Non implémenté
		return;
	}

	public void protect() {
	
	}

	public void jump() { // Non implémenté
		return;
	}

	public void pick() { // Implémenté dans Character
		return;
	}

	public void get() {  //Implémenté dans Character
		return;
	}

	public void store() {	//Implémenté dans Character
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
					transi.getAction().executeAction(this);
				}

			}
			if (!condition)
				throw new RuntimeException("AUcune transition trouvée pour l'automate dans player");
		}

	}

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub

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
	public void turn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void kamikaze() {
		// TODO Auto-generated method stub

	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint() {
		// TODO Auto-generated method stub

	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

}
