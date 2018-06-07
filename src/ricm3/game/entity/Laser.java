package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.security.cert.PKIXRevocationChecker.Option;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Orientation;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Options;
import ricm3.game.other.Transversal;

public class Laser extends Being {

	private boolean m_isFirstPaint = true;

	public Laser(int x, int y, BufferedImage[] sprites, Automaton aut, Orientation orientation, Map map, Model model,
			int life, long lastMove) {
		super(x, y, true, true, true, true, Options.LASER_MS, sprites, aut, orientation, map, model, life, lastMove);
	}

	@Override
	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		Transversal.evalPosition(this.getX(), this.getY(), p, d, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if (e == null) {
			global_map.moveEntity(this, p.x, p.y);
		} else if (e instanceof Being) {
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
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz() {
		// TODO Auto-generated method stub

	}

	public void hit(Entity e) {
		((Being) e).getDamage();
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
	public void paint(Graphics g) {
		int m_x = this.getX() * Options.TAILLE_CASE;
		int m_y = this.getY() * Options.TAILLE_CASE;
		g.setColor(Color.GREEN);
		g.fillRect(m_x, m_y, Options.TAILLE_CASE, Options.TAILLE_CASE);

		if (m_isFirstPaint)
			m_isFirstPaint = false;
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
