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

public abstract class Character extends Being {
	private int m_equipe;
	private Sac m_sac;
	private long m_lastShot = -Options.laserCD;


	public Character(BufferedImage[][] sprites, int nbImage, ImageIcon icon,int x, int y, boolean moveable, boolean pickable,
			boolean killable, boolean lethal, int moveSpeed, Automaton automate, Orientation orientation, int equipe,
			Map map, Model model, int life, long lastMove) {
		super(x, y, moveable, pickable, killable, lethal, moveSpeed, sprites, nbImage,icon,automate, orientation, map, model, life,lastMove);

		m_equipe = equipe;
		m_sac = new Sac(3);
	}

	public int getEquipe() {
		return m_equipe;
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
						m_model, 1, 0, Options.TIMER_LASER);

				this.m_model.m_laser.add(laser);
				global_map.setEntity(laser);

			} else if (e instanceof PowerUp) {

				Laser laser = new Laser(p.x, p.y, m_model.m_idb.laserIdle, m_model.m_idb.nbFrameLaser, m_model.m_icb.m_laserSac,
						m_model.m_automates.get(2), this.getOrientation(), global_map, m_model, 1, 0,
						Options.TIMER_LASER);

				laser.erased_powerup = (PowerUp) e;
				this.m_model.m_laser.add(laser);
				global_map.setEntity(laser);
			} else if (e.getKillable()) {
				((Being) e).getDamage();
			}
		}

	}

	public void setEquipe(int equipe) {
		m_equipe = equipe;
	}

	public Sac getSac() {
		return m_sac;
	}

	@Override
	public void pick() {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		Transversal.evalPosition(this.getX(), this.getY(), p, Direction.FRONT, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if (e != null && e.getPickable()) {
			if (m_sac.addItem(e)) {
				global_map.deleteEntity(e);
				this.m_model.m_powerup.remove(e);
				this.m_model.m_laser.remove(e);
				this.m_model.m_minions.remove(e);
			}
		}
	}

	@Override
	public void get() {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		Transversal.evalPosition(this.getX(), this.getY(), p, Direction.FRONT, this.getOrientation());
		if (global_map.getEntity(p.x, p.y) == null) {
			Entity e = m_sac.removeItem();
			if (e != null) {
				global_map.setEntity(p.x, p.y, e);
				if (e instanceof Laser) {
					this.m_model.m_laser.add((Laser) e);
				} else if (e instanceof Minion) {
					this.m_model.m_minions.add((Minion) e);
				} else if (e instanceof Obstacle) {
					this.m_model.m_obstacles.add((Obstacle) e);
				} else if (e instanceof PowerUp) {
					this.m_model.m_powerup.add((PowerUp) e);
				}
			}
		}
	}

}
