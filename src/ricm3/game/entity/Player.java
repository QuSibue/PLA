package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Orientation;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Options;
import ricm3.game.other.Transversal;
import ricm3.game.other.TypeKey;

public class Player extends Character {

	private TypeKey m_key;
	private long m_lastShot = -Options.laserCD;
	private int m_energie;
	private long m_lastPower = -Options.powerCD;

	public Player(int x, int y, BufferedImage[] sprites, Automaton aut, Orientation orientation, int equipe, Map map,
			Model model, int life, long lastMove, TypeKey key) {
		super(sprites, x, y, true, false, true, false, Options.PLAYER_MS, aut, orientation, equipe, map, model, life,
				lastMove);
		m_key = key;
		m_energie = 10;

	}

	// action
	@Override
	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		this.turn(d);
		Transversal.evalPosition(this.getX(), this.getY(), p, d, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if (e == null || e instanceof Laser || e instanceof PowerUp) {
			if (e != null) {
				if (e.getLethal()) {
					this.getDamage();
					global_map.deleteEntity(e);
					m_model.m_laser.remove(e);
				} else if (e instanceof PowerUp) {
					this.applyPowerUp((PowerUp) e);
					;
					global_map.deleteEntity(e);
					m_model.m_powerup.remove(e);
				}
			}
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
		if (m_energie >= 3) {
			Point p = new Point();
			if (global_map.caseLibre(this.getX(), this.getY(), p)) {
				Minion minion = new Minion(null, p.x, p.y, Transversal.idleAutomaton(), Orientation.RIGHT, 1,
						global_map, this.m_model, 1, 0);
				m_model.m_minions.add(minion);
				global_map.setEntity(minion);
				m_energie -= 3;
			} else {
				System.out.print("Pas de place pour placer de nouveaux sbires");
			}

		}

	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hit(long now) {
		long elapsed = now - m_lastShot;
		if (elapsed > Options.laserCD) {
			m_lastShot = now;
			this.setLastMove(now);
			Point p = new Point();
			Transversal.evalPosition(this.getX(), this.getY(), p, Direction.FRONT, this.getOrientation());
			Entity e = global_map.getEntity(p.x, p.y);
			if (e == null) {
				Laser laser = new Laser(p.x, p.y, null, Transversal.straightAutomaton(), this.getOrientation(),
						global_map, m_model, 1, 0);
				this.m_model.m_laser.add(laser);
				global_map.setEntity(laser);
			} else if (e.getKillable()) {
				((Being) e).getDamage();
			}
		}

	}

	public TypeKey getKey() {
		return m_key;
	}

	public void setKey(TypeKey key) {
		m_key = key;
	}

	@Override
	public void power(long now) {
		long elapsed = now - m_lastShot;
		if (elapsed > Options.laserCD) {
			if (m_energie < 10) {
				m_energie++;
			}
		}

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
	public void turn(Direction d) {
		// TODO Auto-generated method stub
		switch (d) {
		case NORTH:
			this.setOrientation(Orientation.UP);
			break;

		case SOUTH:
			this.setOrientation(Orientation.DOWN);
			break;

		case EAST:
			this.setOrientation(Orientation.RIGHT);
			break;

		case WEST:
			this.setOrientation(Orientation.LEFT);
			break;

		default:
			throw new RuntimeException("Direction invalid");
		}

	}

}
