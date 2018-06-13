package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Orientation;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Options;
import ricm3.game.other.Transversal;

public class Laser extends Being {

	private boolean m_isFirstPaint = true;
	public PowerUp erased_powerup;
	private long lifespan;
	private long m_last;

	public Laser(int x, int y, BufferedImage[][] sprites, int nbImage, ImageIcon icon, Automaton aut,
			Orientation orientation, Map map, Model model, int life, long lastMove) {
		super(x, y, true, true, true, true, Options.LASER_MS, sprites, nbImage, icon, aut, orientation, map, model,
				life, lastMove);
		erased_powerup = null;
		lifespan = 3000;
		m_last = 0;
	}


	@Override
	public void move(Direction d) {

		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		Transversal.evalPosition(this.getX(), this.getY(), p, d, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if (e == null) {
			global_map.moveEntity(this, p.x, p.y);
			if (erased_powerup != null) {
				global_map.setEntity(erased_powerup);
				erased_powerup = null;
			}
		} else if (e instanceof PowerUp) {
			if (erased_powerup != null) {
				global_map.setEntity(erased_powerup);
				erased_powerup = null;
			}
			global_map.moveEntity(this, p.x, p.y);
			erased_powerup = (PowerUp) e;
		} else if (e.getKillable()) {
			if (erased_powerup != null) {
				global_map.setEntity(erased_powerup);
				erased_powerup = null;
			}
			this.hit(e);
			global_map.deleteEntity(this);
			this.m_model.m_laser.remove(this);
		}
	}

	@Override
	public void step(long now) {
		if (!m_isFirstPaint) {
			super.step(now);
			
		}
		
		long elapsed = 0;
		if (m_last != 0) {
			elapsed = now - m_last;
		}		
		m_last = now;
		lifespan = lifespan - elapsed;
		if (lifespan <= 0) {
			// kaboum
			global_map.deleteEntity(this);
			m_model.m_laser.remove(this);
			// enlever de la liste des trucs à step
		}
	}

	@Override
	public void pop(long now) {
		int life = this.getLife();
		if (life == 1) {
			return;
		} else {
			life = life / 2;
			this.setLife(life);
			this.splitter();
		}
	}

	public void splitter() {

		int dx = 0, dy = 0;
		Point p = new Point(dx, dy);
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
		Point p1 = new Point(dx1, dy1);
		Point p2 = new Point(dx2, dy2);

		Laser laser1, laser2;

		// we get coordinates of the tile in front of the laser

		Transversal.evalPosition(this.getX(), this.getY(), p, Direction.FRONT, this.getOrientation());

		if (global_map.getEntity(p.x, p.y) == null) { // tile in front is free

			// we get the coordinate of the tiles up left and up right relatively to the
			// laser's position
			Transversal.evalPosition(p.x, p.y, p1, Direction.RIGHT, this.getOrientation());
			Transversal.evalPosition(p.x, p.y, p2, Direction.LEFT, this.getOrientation());

			laser1 = new Laser(p1.x, p1.y, this.getSprites(), m_model.m_idb.nbFrameLaser, this.getIcon(),
					this.getAutomaton(), this.getOrientation(), global_map, m_model, this.getLife(),
					this.getLastMove());
			laser2 = new Laser(p2.x, p2.y, this.getSprites(), m_model.m_idb.nbFrameLaser, this.getIcon(),
					this.getAutomaton(), this.getOrientation(), global_map, m_model, this.getLife(),
					this.getLastMove());

		}

		else { // getEntity(dx,dy) != null

			// we get the coordinates of the tiles left and right of the laser
			Transversal.evalPosition(this.getX(), this.getY(), p1, Direction.RIGHT, this.getOrientation());
			Transversal.evalPosition(this.getX(), this.getY(), p2, Direction.LEFT, this.getOrientation());

			laser1 = new Laser(p1.x, p1.y, this.getSprites(), m_model.m_idb.nbFrameLaser, this.getIcon(),
					this.getAutomaton(), this.getOrientation(), global_map, m_model, this.getLife(),
					this.getLastMove());
			laser2 = new Laser(p2.x, p2.y, this.getSprites(), m_model.m_idb.nbFrameLaser, this.getIcon(),
					this.getAutomaton(), this.getOrientation(), global_map, m_model, this.getLife(),
					this.getLastMove());

		}

		if (global_map.getEntity(p1.x, p1.y) == null) { // one half "moved" diagonally

			global_map.setEntity(laser1);
			m_model.m_laser.add(laser1);
		} else { // laser causes explosion in front of itself
			laser1.wizz();
		}
		if (global_map.getEntity(p2.x, p2.y) == null) { // one half "moved" diagonally
			global_map.setEntity(laser2);
			m_model.m_laser.add(laser2);
		} else { // laser causes explosion in front of itself
			laser2.wizz();
		}

		global_map.deleteEntity(this);
		m_model.m_laser.remove(this);

	}

	@Override
	public void wizz() {
		int cx = this.getX();
		int cy = this.getY();

		// hit all entities on the 8 adjacent tiles
		hit(global_map.getEntity(cx + 1, cy));
		hit(global_map.getEntity(cx, cy + 1));
		hit(global_map.getEntity(cx + 1, cy + 1));
		hit(global_map.getEntity(cx, cy - 1));
		hit(global_map.getEntity(cx - 1, cy - 1));
		hit(global_map.getEntity(cx + 1, cy - 1));
		hit(global_map.getEntity(cx - 1, cy));
		hit(global_map.getEntity(cx - 1, cy + 1));

		global_map.deleteEntity(this);
		m_model.m_laser.remove(this);
	}

	public void hit(Entity e) {
		if (e instanceof Being) {
			((Being) e).getDamage();
		}
	}

	@Override
	public void power(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void protect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void jump() {
		return;

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
	public void paint(Graphics g) {
		super.paint(g);
		if (m_isFirstPaint)
			m_isFirstPaint = false;
	}

	@Override
	public void hit(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void kamikaze() {

	}

	@Override
	public void turn(Direction d) {
		// TODO Auto-generated method stub

	}
}
