package ricm3.game.entity;

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

public class Minion extends Character {
	public long m_lastMove;
	public int xOrigin;
	public int yOrigin;
	public long lifespan;
	public long m_last;
	private long m_lastShot = -Options.laserCD;

	public Minion(BufferedImage[][] sprites, int nbImage, ImageIcon icon, int x, int y, boolean moveable,
			boolean pickable, boolean killable, boolean lethal, int moveSpeed, Automaton automate,
			Orientation orientation, int equipe, Map map, Model model, int life, long lastMove) {
		super(sprites, nbImage, icon, x, y, moveable, pickable, killable, lethal, moveSpeed, automate, orientation,
				equipe, map, model, life, lastMove);

		xOrigin = this.getX();
		yOrigin = this.getY();
		lifespan = 20000;
		m_last = 0;
	}

	@Override
	public void step(long now) {
		if (!isexploding) {
			super.step(now);
			long elapsed = 0;
			if (m_last != 0) {
				elapsed = now - m_last;
			}
			m_last = now;
			lifespan = lifespan - elapsed;
			if (lifespan <= 0) {
				// kaboum
				global_map.deleteEntity(this);
				m_model.m_minions.remove(this);
				// enlever de la liste des trucs à step
			}
		}

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

				Laser laser = new Laser(p.x, p.y, m_model.m_idb.laserIdle, m_model.m_idb.nbFrameLaser,
						m_model.m_icb.m_laserSac, m_model.m_automates.get(2), this.getOrientation(), global_map,
						m_model, 1, 0, 1000);

				this.m_model.m_laser.add(laser);
				global_map.setEntity(laser);

			} else if (e instanceof PowerUp) {

				Laser laser = new Laser(p.x, p.y, m_model.m_idb.laserIdle, m_model.m_idb.nbFrameLaser,
						m_model.m_icb.m_laserSac, m_model.m_automates.get(2), this.getOrientation(), global_map,
						m_model, 1, 0, 1000);

				laser.erased_powerup = (PowerUp) e;
				this.m_model.m_laser.add(laser);
				global_map.setEntity(laser);
			} else if (e.getKillable()) {
				((Being) e).getDamage();
			}
		}
		m_lastShot = now;
		this.setLastMove(now);
		Point p = new Point();
		Transversal.evalPosition(this.getX(), this.getY(), p, Direction.LEFT, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if (e == null) {

			Laser laser = new Laser(p.x, p.y, m_model.m_idb.laserIdle, m_model.m_idb.nbFrameLaser,
					m_model.m_icb.m_laserSac, m_model.m_automates.get(2), this.getOrientation(), global_map, m_model, 1,
					0, 1000);

			this.m_model.m_laser.add(laser);
			global_map.setEntity(laser);

		} else if (e instanceof PowerUp) {

			Laser laser = new Laser(p.x, p.y, m_model.m_idb.laserIdle, m_model.m_idb.nbFrameLaser,
					m_model.m_icb.m_laserSac, m_model.m_automates.get(2), this.getOrientation(), global_map, m_model, 1,
					0, 1000);

			laser.erased_powerup = (PowerUp) e;
			this.m_model.m_laser.add(laser);
			global_map.setEntity(laser);
		} else if (e.getKillable()) {
			((Being) e).getDamage();
		}
		m_lastShot = now;
		this.setLastMove(now);
		p = new Point();
		Transversal.evalPosition(this.getX(), this.getY(), p, Direction.RIGHT, this.getOrientation());
		e = global_map.getEntity(p.x, p.y);
		if (e == null) {

			Laser laser = new Laser(p.x, p.y, m_model.m_idb.laserIdle, m_model.m_idb.nbFrameLaser,
					m_model.m_icb.m_laserSac, m_model.m_automates.get(2), this.getOrientation(), global_map, m_model, 1,
					0, 1000);

			this.m_model.m_laser.add(laser);
			global_map.setEntity(laser);

		} else if (e instanceof PowerUp) {

			Laser laser = new Laser(p.x, p.y, m_model.m_idb.laserIdle, m_model.m_idb.nbFrameLaser,
					m_model.m_icb.m_laserSac, m_model.m_automates.get(2), this.getOrientation(), global_map, m_model, 1,
					0, 1000);

			laser.erased_powerup = (PowerUp) e;
			this.m_model.m_laser.add(laser);
			global_map.setEntity(laser);
		} else if (e.getKillable()) {
			((Being) e).getDamage();
		}
		m_lastShot = now;
		this.setLastMove(now);
		p = new Point();
		Transversal.evalPosition(this.getX(), this.getY(), p, Direction.BACK, this.getOrientation());
		e = global_map.getEntity(p.x, p.y);
		if (e == null) {

			Laser laser = new Laser(p.x, p.y, m_model.m_idb.laserIdle, m_model.m_idb.nbFrameLaser,
					m_model.m_icb.m_laserSac, m_model.m_automates.get(2), this.getOrientation(), global_map, m_model, 1,
					0, 1000);

			this.m_model.m_laser.add(laser);
			global_map.setEntity(laser);

		} else if (e instanceof PowerUp) {

			Laser laser = new Laser(p.x, p.y, m_model.m_idb.laserIdle, m_model.m_idb.nbFrameLaser,
					m_model.m_icb.m_laserSac, m_model.m_automates.get(2), this.getOrientation(), global_map, m_model, 1,
					0, 1000);

			laser.erased_powerup = (PowerUp) e;
			this.m_model.m_laser.add(laser);
			global_map.setEntity(laser);
		} else if (e.getKillable()) {
			((Being) e).getDamage();
		}
	}

	public void pop(long now) {
		int x = this.getX();
		int y = this.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Entity e = global_map.getEntity(i, j);
				if (e != null && e != this) {
					if (e instanceof Being) {
						((Being) e).getDamage();
					}
				}
				global_map.deleteEntity(this);
				isexploding = true;
				setIndexRefresh(0);
				setNumImage(0);
				setNbImageRefresh((int) ricm3.game.framework.Options.FPS / m_model.m_idb.nbFrameExplosion);
				setNbiMAGE(m_model.m_idb.nbFrameExplosion);

			}
		}
		// m_model.m_minions.remove(this);

	}

	public void wizz() {
		int xCourant = this.getX();
		int yCourant = this.getY();
		this.global_map.deleteEntity(this);
		m_model.m_minions.remove(this);
		Portal p = new Portal(xOrigin, yOrigin, xCourant, yCourant, m_model.m_idb.portail, m_model.m_idb.nbFramePortail,
				m_model.m_icb.m_energieSac, global_map, m_model);
		m_model.m_portal.add(p);
		global_map.setEntity(p); // enlever les commentaires quand la liste de portail sera dans model
		// m_model.m_portail.add(p);

	}

	/*
	 * public void hit(long now) {
	 * 
	 * Point p = new Point(); Transversal.evalPosition(this.getX(), this.getY(), p,
	 * Direction.FRONT, this.getOrientation()); Entity e = global_map.getEntity(p.x,
	 * p.y); if (e instanceof Being) { ((Being) e).getDamage(); } /*
	 * Iterator<Minion> iterM = m_model.m_minions.iterator(); Entity closest = null;
	 * if (this.getEquipe() == m_model.virus.getEquipe()) { closest =
	 * m_model.antivirus;< } else { closest = m_model.virus;
	 * 
	 * } while (iterM.hasNext()) { Minion m = iterM.next(); if (m.getEquipe() !=
	 * this.getEquipe()) { closest = this.closestEntity(closest, m);
	 * 
	 * } }
	 * 
	 * 
	 * }
	 */

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
	public void kamikaze() {

	}

}