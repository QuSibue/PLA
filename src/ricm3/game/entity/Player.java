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

public class Player extends Character {

	private TypeKey m_key;
	private long m_lastShot = -Options.laserCD;
	private int m_energie;
	private long m_lastPower = -Options.powerCD;
	private ArrayList<Automaton> m_autoMinions;
	private int m_indiceAutoMinions;

	public Player(int x, int y, BufferedImage[] sprites, Automaton aut, Orientation orientation, int equipe, Map map,
			Model model, int life, long lastMove, TypeKey key) {
		super(sprites, x, y, true, false, true, false, Options.PLAYER_MS, aut, orientation, equipe, map, model, life,
				lastMove);
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
				Minion minion = new Minion(m_model.kamikaze_droite, p.x, p.y,true,true,true,false, Options.MINION_MS, this.m_model.m_automates.get(m_indiceAutoMinions), Orientation.RIGHT, 1,
						global_map, this.m_model, 1, 0);
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
		// TODO Auto-generated method stub
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
				Laser laser = new Laser(p.x, p.y, null, Transversal.straightAutomaton(), this.getOrientation(),
						global_map, m_model, 1, 0);
				this.m_model.m_laser.add(laser);
				global_map.setEntity(laser);
			} else if (e instanceof PowerUp) {
				Laser laser = new Laser(p.x, p.y, null, Transversal.straightAutomaton(), this.getOrientation(),
						global_map, m_model, 1, 0);
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
	

	public void paint(Graphics g) {
		// affiche un carré bleu pour le joueur
		int m_x = this.getX() * Options.TAILLE_CASE;
		int m_y = this.getY() * Options.TAILLE_CASE;
		g.setColor(Color.blue);
		g.fillRect(m_x, m_y, Options.TAILLE_CASE, Options.TAILLE_CASE);

	}

	@Override
	public void kamikaze() {
		// TODO Auto-generated method stub

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
