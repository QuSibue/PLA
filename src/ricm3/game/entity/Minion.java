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
		//Portal p = new Portal(xOrigin, yOrigin, xCourant, yCourant, null);
		//global_map.setEntity(p); //enlever les commentaires quand la liste de portail sera dans model
		//m_model.m_portail.add(p); 
		this.global_map.deleteEntity(this);
		m_model.m_minions.remove(this);
		
		
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
	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		this.turn(d);
		Transversal.evalPosition(this.getX(), this.getY(), p, d, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if (e == null || e instanceof Laser || e instanceof PowerUp) {
			if (e instanceof Laser) {
				this.getDamage();
				global_map.deleteEntity(e);
				m_model.m_laser.remove(e);
			} else if (e instanceof PowerUp) {
				this.pick();
				global_map.deleteEntity(e);
				m_model.m_powerup.remove(e);
			}
			global_map.moveEntity(this, p.x, p.y);
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
	public void hit(long now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn(Direction d) {
		// TODO Auto-generated method stub
		
	}

}