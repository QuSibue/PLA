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

//TODO faire le bon heritage (character)
public class Player extends Character {

	public Player(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, int ms,
			BufferedImage[] sprites, Automaton aut, Orientation orientation, int equipe, Map map, Model model, int life,
			long lastMove) {
		super(sprites, x, y, moveable, pickable, killable, lethal, ms, aut, orientation, equipe, map, model, life,
				lastMove);
	}
	// action

	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		Transversal.positionRelative(this.getX(), this.getY(), p, d, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if (e == null || e instanceof Laser) {
			global_map.moveEntity(this, p.x, p.y);
		}

	}

	@Override
	public void paint(Graphics g) {
		// affiche un carré bleu pour le joueur
		int m_x = this.getX() * Options.TAILLE_CASE;
		int m_y = this.getY() * Options.TAILLE_CASE;
		g.setColor(Color.blue);
		g.fillRect(m_x, m_y, Options.TAILLE_CASE, Options.TAILLE_CASE);

	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub
		Point p = new Point();
		if (global_map.caseLibre(this.getX(), this.getY(), p)) {
			Minion minion = new Minion(null, p.x, p.y, true, false, true, true, 1, Transversal.idleAutomaton(),
					Orientation.RIGHT, 1, global_map, this.m_model, 1, 0);
			m_model.m_minions.add(minion);
			global_map.setEntity(minion);
		} else {
			System.out.print("Pas de place pour placer de nouveaux sbires");
		}
	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hit() {
		Point p = new Point();
		Transversal.positionRelative(this.getX(), this.getY(), p, Direction.FRONT, this.getOrientation());
		if (global_map.getEntity(p.x, p.y) == null) {
			Laser laser = new Laser(p.x, p.y, true, true, false, true, 100, null, Transversal.straightAutomaton(),
					this.getOrientation(), global_map, m_model, 1, 0);
			this.m_model.m_laser.add(laser);
			global_map.setEntity(laser);
		}

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
