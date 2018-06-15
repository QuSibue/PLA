package ricm3.game.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

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
	
	private int m_energie;
	private long m_lastPower = -Options.powerCD;
	private ArrayList<Automaton> m_autoMinions;
	private int m_indiceAutoMinions;
	private long m_lastShot = -Options.laserCD;

	public Player(int x, int y, BufferedImage[][] sprites, int nbImage, Automaton aut, Orientation orientation,
			int equipe, Map map, Model model, int life, long lastMove, TypeKey key) {
		super(sprites, nbImage, null, x, y, true, false, true, false, Options.PLAYER_MS, aut, orientation, equipe, map,
				model, life, lastMove);
		m_key = key;
		m_energie = Options.initialEnergie;
		m_autoMinions = new ArrayList<Automaton>();
		m_indiceAutoMinions = 0;
		this.loadAutomaton();

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
	
	
	
	@Override
	public void pop(long now) {
		if (m_energie >= 3) {
			Point p = new Point();
			if (global_map.caseLibre(this.getX(), this.getY(), p)) {

				BufferedImage[][] spriteMinion;

				ImageIcon ic = null;
				if (getEquipe() == 1) {
					spriteMinion = m_model.m_idb.getMinionSpritesV(m_indiceAutoMinions);
					ic = m_model.m_icb.m_iconSbiresVirusSac[m_indiceAutoMinions];
				} else {
					spriteMinion = m_model.m_idb.getMinionSpritesA(m_indiceAutoMinions);
					ic = m_model.m_icb.m_iconSbiresAntivirusSac[m_indiceAutoMinions];
				}
				Minion minion = new Minion(spriteMinion, m_model.m_idb.nbFrameM1, ic, p.x, p.y, true, true, true, false,
						Options.MINION_MS, this.m_autoMinions.get(m_indiceAutoMinions), this.getOrientation(), this.getEquipe(),
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
		m_indiceAutoMinions++;
		m_indiceAutoMinions = m_indiceAutoMinions % Options.NB_MINIONS_TYPE;
		System.out.println(m_indiceAutoMinions);

	}



	public TypeKey getKey() {
		return m_key;
	}

	public void setKey(TypeKey key) {
		m_key = key;
	}

	public int getEnergie() {
		return m_energie;
	}

	public int getIndiceMinion() {
		return m_indiceAutoMinions;
	}

	@Override
	public void power(long now) {
		System.out.println(m_energie);

		if (m_energie + 5 <= 10) {
			m_energie += 5;
			System.out.println(m_energie);
		} else {
			m_energie += 10 - m_energie;
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
		m_autoMinions.add(m_model.m_automates.get(3));
		m_autoMinions.add(m_model.m_automates.get(4));
		m_autoMinions.add(m_model.m_automates.get(5));
		m_autoMinions.add(m_model.m_automates.get(6));
		m_autoMinions.add(m_model.m_automates.get(7));
		m_autoMinions.add(m_model.m_automates.get(8));
	}

}
