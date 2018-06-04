package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Transition;
import ricm3.game.other.Options;

public class Player extends Being {

	public Player(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, int ms,
			BufferedImage[] sprites, Automaton aut) {
		super(x, y, moveable, pickable, killable, lethal, ms, sprites, aut);
	}

	// @override
	public void step() {

		Iterator<Transition> iter = this.getAutomaton().getTransitions().iterator();
		Transition transi;
		boolean condition = false;

		// On va chercher la première transition utilisable
		// puis on met a jour l'etat courant
		// et on effectue l'action associée a la transition
		
		// ce code va surement être deplacé dans being, superclass de player, minion et laser
		while (!condition && iter.hasNext()) {
			transi = iter.next();
			//les etats sont par aliasing on peut donc utiliser le double égale
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

	// action

	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Options.evalPosition(this.getX(), this.getY(), x_res, y_res, d, this.getOrientation());

	}

	public void attack() {
		return;
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
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint() {
		// TODO Auto-generated method stub
		
	}
}
