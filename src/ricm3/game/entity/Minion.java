package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Orientation;
import ricm3.game.automaton.Transition;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Options;

public class Minion extends Character {
	public long m_lastMove;
	public Minion(BufferedImage[] sprites, int x, int y, boolean moveable, boolean pickable, boolean killable,
			boolean lethal, int moveSpeed, Automaton automate, Orientation orientation, int equipe, Map map,
			Model model,int life, long lastMove) {
		super(sprites, x, y, moveable, pickable, killable, lethal, moveSpeed, automate, orientation, equipe, map,
				model,life,lastMove);
		// TODO FACTORISER LES PARAMETRE CONSTANTS ex un minion est toujours moveable
	}


	public void pop() {
		return;
	}

	public void wizz() {
		return;
	}

	public void hit() {
		return;
	}

	public void power() {
		return;
	}

	public void protect() {
		return;
	}

	public void jump() {
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

}
