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
import ricm3.game.other.Transversal;

//TODO faire le bon heritage (character)
public class Player extends Being {

	public Player(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, int ms,
			BufferedImage[] sprites, Automaton aut, Orientation orientation, Map map) {
		super(x, y, moveable, pickable, killable, lethal, ms, sprites, aut, orientation, map);
	}

	// @override
	public void step() {

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

	// action

	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		Transversal.positionRelative(this.getX(), this.getY(), p, d, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if( e == null || e instanceof Laser) {
			global_map.moveEntity(this, p.x, p.y);
		}

	}



	@Override
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(200, 200, 50, 50);

	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void power() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void protect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void get() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void store() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void _throw() {
		// TODO Auto-generated method stub
		
	}

}
