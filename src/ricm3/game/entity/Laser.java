package ricm3.game.entity;

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
	private long m_lifespan;
	private long m_last;

	public Laser(int x, int y, BufferedImage[][] sprites, int nbImage, ImageIcon icon, Automaton aut,
			Orientation orientation, Map map, Model model, int life, long lastMove, long lifespan) {
		super(x, y, true, true, true, true, Options.LASER_MS, sprites, nbImage, icon, aut, orientation, map, model,
				life, lastMove);
		erased_powerup = null;
		m_lifespan = lifespan;
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
		} else if (e instanceof Portal) {
			((Portal) e).use_portal(this, p);
		}
	}

	@Override
	public void step(long now) {
		if (!isexploding) {
			if (!m_isFirstPaint) {
				super.step(now);

			}

			long elapsed = 0;
			if (m_last != 0) {
				elapsed = now - m_last;
			}
			m_last = now;
			m_lifespan = m_lifespan - elapsed;
			if (m_lifespan <= 0) {
				// kaboum

				global_map.deleteEntity(this);
				m_model.m_laser.remove(this);
				if (erased_powerup != null) {
					global_map.setEntity(erased_powerup);
					erased_powerup = null;
				}
			}

		}

	}

	@Override
	public void pop(long now) {
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

		if (erased_powerup != null) {
			global_map.setEntity(erased_powerup);
			erased_powerup = null;
		}

		isexploding = true;
		setIndexRefresh(0);
		setNumImage(0);
		setNbImageRefresh((int) ricm3.game.framework.Options.FPS / m_model.m_idb.nbFrameExplosion);
		setNbiMAGE(m_model.m_idb.nbFrameExplosion);
	}

	public void splitter() {

		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();

		Laser laser1, laser2;

		// we get coordinates of the tile in front of the laser

		Transversal.evalPosition(this.getX(), this.getY(), p1, Direction.FRONT, this.getOrientation());
		Transversal.evalPosition(p1.x, p1.y, p2, Direction.LEFT, this.getOrientation());
		Transversal.evalPosition(p1.x, p1.y, p3, Direction.RIGHT, this.getOrientation());

		if (global_map.getEntity(p2.x, p2.y) == null && global_map.getEntity(p3.x, p3.y) == null) { // tile in front is
																									// free

			// we get the coordinate of the tiles up left and up right relatively to the
			// laser's position

			laser1 = new Laser(p3.x, p3.y, this.getSprites(), m_model.m_idb.nbFrameLaser, this.getIcon(),
					this.getAutomaton(), this.getOrientation(), global_map, m_model, this.getLife(), this.getLastMove(),
					m_lifespan / 2);
			laser2 = new Laser(p2.x, p2.y, this.getSprites(), m_model.m_idb.nbFrameLaser, this.getIcon(),
					this.getAutomaton(), this.getOrientation(), global_map, m_model, this.getLife(), this.getLastMove(),
					m_lifespan / 2);
			global_map.deleteEntity(this);
			global_map.setEntity(laser1);
			global_map.setEntity(laser2);
			m_model.m_laser.add(laser1);
			m_model.m_laser.add(laser2);
			m_model.m_laser.remove(this);
		} else { // getEntity(dx,dy) != null
			Transversal.evalPosition(this.getX(), this.getY(), p2, Direction.LEFT, this.getOrientation());
			Transversal.evalPosition(this.getX(), this.getY(), p3, Direction.RIGHT, this.getOrientation());
			// we get the coordinates of the tiles left and right of the laser
			if (global_map.getEntity(p2.x, p2.y) == null && global_map.getEntity(p3.x, p3.y) == null) {
				laser1 = new Laser(p2.x, p2.y, this.getSprites(), m_model.m_idb.nbFrameLaser, this.getIcon(),
						this.getAutomaton(), this.getOrientation(), global_map, m_model, this.getLife(),
						this.getLastMove(), m_lifespan / 2);
				laser2 = new Laser(p3.x, p3.y, this.getSprites(), m_model.m_idb.nbFrameLaser, this.getIcon(),
						this.getAutomaton(), this.getOrientation(), global_map, m_model, this.getLife(),
						this.getLastMove(), m_lifespan / 2);

				global_map.deleteEntity(this);
				global_map.setEntity(laser1);
				global_map.setEntity(laser2);
				m_model.m_laser.add(laser1);
				m_model.m_laser.add(laser2);
				m_model.m_laser.remove(this);
			}

		}
	}

	@Override
	public void wizz() {

		long life = m_lifespan;
		if (life == 1) {
			return;
		} else {
			this.splitter();
		}

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

		if (isexploding) {
			int i = getIndexRefresh();
			setIndexRefresh(i + 1);
			if (i == getNbImageRefresh()) {
				setIndexRefresh(0);
				int j = getNumImage();
				setNumImage(j + 1);
			}

			g.drawImage(m_model.m_idb.explosion[0][getNumImage() % getNbImage()],
					(this.getX() - 1) * Options.TAILLE_CASE, (this.getY() - 1) * Options.TAILLE_CASE,
					Options.TAILLE_CASE * 3, Options.TAILLE_CASE * 3, null);
			if ((getNumImage() % getNbImage()) == 8) {
				m_model.m_laser.remove(this);
			}
		} else {
			int i = getIndexRefresh();
			setIndexRefresh(i + 1);
			if (i == getNbImageRefresh()) {
				setIndexRefresh(0);
				int j = getNumImage();
				setNumImage(j + 1);
			}
			g.drawImage(this.getSprites()[0][getNumImage() % getNbImage()], this.getX() * Options.TAILLE_CASE, this.getY() * Options.TAILLE_CASE,
					Options.TAILLE_CASE, Options.TAILLE_CASE, null);
			if (m_isFirstPaint)
				m_isFirstPaint = false;
		}
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
