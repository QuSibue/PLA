package ricm3.game.entity;

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

public class Player extends Character {

	private TypeKey m_key;
	private long m_lastShot = -Options.laserCD;
	private int m_energie;
	private long m_lastPower = -Options.powerCD;
	private ArrayList<Automaton> m_autoMinions;
	private int m_indiceAutoMinions;

	public Player(int x, int y, BufferedImage[][] sprites, int nbImage, Automaton aut, Orientation orientation,
			int equipe, Map map, Model model, int life, long lastMove, TypeKey key, ImageDataBase idb) {
		super(sprites, nbImage, x, y, true, false, true, false, Options.PLAYER_MS, aut, orientation, equipe, map, model,
				life, lastMove, idb);
		m_key = key;
		m_energie = Options.initialEnergie;
		m_autoMinions = new ArrayList<Automaton>();
		m_indiceAutoMinions = 0;
		this.loadAutomaton();

	}

	@Override
	public void pop(long now) {
		if (m_energie >= 3) {
			Point p = new Point();
			if (global_map.caseLibre(this.getX(), this.getY(), p)) {
				BufferedImage[][] spriteMinion = m_model.m_idb.getMinionSprites(m_indiceAutoMinions);
				Minion minion = new Minion(spriteMinion, m_model.m_idb.nbFrameM1, p.x, p.y, true, true, true, false,
						Options.LASER_MS, this.m_model.m_automates.get(m_indiceAutoMinions), Orientation.RIGHT, 1,
						global_map, this.m_model, 1, 0, this.m_idb);
				m_model.m_minions.add(minion);
				global_map.setEntity(minion);
				m_energie -= 3;
			} else {
				System.out.print("Pas de place pour placer de nouveaux sbires");
			}

		} else {
			this.power(now);
		}

	}

	@Override
	public void wizz() {
		if (m_indiceAutoMinions == Options.NB_MINIONS_TYPE - 1) {
			m_indiceAutoMinions = 0;
		} else {
			m_indiceAutoMinions++;
			System.out.println(m_indiceAutoMinions);
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
						Transversal.straightAutomaton(), this.getOrientation(), global_map, m_model, 1, 0, this.m_idb);
				this.m_model.m_laser.add(laser);
				global_map.setEntity(laser);
			} else if (e instanceof PowerUp) {
				Laser laser = new Laser(p.x, p.y, null, m_model.m_idb.nbFrameLaser, Transversal.straightAutomaton(), this.getOrientation(),
						global_map, m_model, 1, 0, this.m_idb);
				laser.erased_powerup = (PowerUp) e;
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
		long elapsed = now - m_lastPower;
		if (elapsed > Options.powerCD) {
			if (m_energie < 10) {
				m_energie++;
			}
		}

	}

	@Override
	public void protect() {

	}

	@Override
	public void jump() {

	}

	@Override
	public void store() {

	}

	@Override
	public void _throw() {

	}

	@Override
	public void kamikaze() {

	}

	public void loadAutomaton() {
		m_autoMinions.add(m_model.m_automates.get(0));
		m_autoMinions.add(m_model.m_automates.get(1));
		m_autoMinions.add(m_model.m_automates.get(2));
		m_autoMinions.add(m_model.m_automates.get(0));
		m_autoMinions.add(m_model.m_automates.get(1));
		m_autoMinions.add(m_model.m_automates.get(2));
	}

}
