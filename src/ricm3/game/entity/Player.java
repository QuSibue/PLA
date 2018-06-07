package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Orientation;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Options;
import ricm3.game.other.Transversal;
import ricm3.game.other.TypeKey;

//TODO faire le bon heritage (character)
public class Player extends Character {

	private TypeKey m_key;
	private ArrayList<Automaton> m_autoMinions;
	private int m_indiceAutoMinions;
	
	public Player(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, int ms,
			BufferedImage[] sprites, Automaton aut, Orientation orientation, int equipe, Map map, Model model, int life,
			long lastMove,TypeKey key) {
		super(sprites, x, y, moveable, pickable, killable, lethal, ms, aut, orientation, equipe, map, model, life,
				lastMove);
		m_key = key;
		m_autoMinions = new ArrayList<Automaton>();
		m_indiceAutoMinions = 0;
		
	}
	// action

	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		Transversal.evalPosition(this.getX(), this.getY(), p, d, this.getOrientation());
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
			Minion minion = new Minion(null, p.x, p.y, true, false, true, true, 1, this.m_autoMinions.get(m_indiceAutoMinions),
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
		if(m_indiceAutoMinions == Options.NB_MINIONS_TYPE-1) {
			m_indiceAutoMinions = 0;
		}
		else {
			m_indiceAutoMinions ++;
		}

	}

	@Override
	public void hit() {
		Point p = new Point();
		Transversal.evalPosition(this.getX(), this.getY(), p, Direction.FRONT, this.getOrientation());
		if (global_map.getEntity(p.x, p.y) == null) {
			Laser laser = new Laser(p.x, p.y, true, true, false, true, 100, null, Transversal.straightAutomaton(),
					this.getOrientation(), global_map, m_model, 1, 0);
			this.m_model.m_laser.add(laser);
			global_map.setEntity(laser);
		}

	}
	
	public TypeKey getKey() {
		return m_key;
	}
	
	public void setKey(TypeKey key) {
		m_key = key;
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

	@Override
	public void turn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kamikaze() {
		// TODO Auto-generated method stub
		
	}

}
