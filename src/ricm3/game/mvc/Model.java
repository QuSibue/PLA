package ricm3.game.mvc;

import java.util.Iterator;
import java.util.LinkedList;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Orientation;
import ricm3.game.entity.Laser;
import ricm3.game.entity.Minion;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Player;
import ricm3.game.entity.PowerUp;
import ricm3.game.framework.GameModel;
import ricm3.game.other.Transversal;
import ricm3.game.other.TypeKey;

public class Model extends GameModel {

	public LinkedList<Minion> m_minions;
	public LinkedList<Obstacle> m_obstacles;
	public LinkedList<Laser> m_laser;
	public LinkedList<PowerUp> m_powerup;
	public Player virus;
	public Player antivirus;
	public Map map;

	public Model() {
		m_minions = new LinkedList<Minion>();
		m_obstacles = new LinkedList<Obstacle>();
		m_laser = new LinkedList<Laser>();
		m_powerup = new LinkedList<PowerUp>();
		// sprites vont etres donné a l'instantiation normalement, a voir
		// ON FAIT LA MAP
		map = new Map(1100, 1200);

		// ON FAIT UN AUTOMATE
		Automaton aut = Transversal.virusAutomaton();
		// FIN DE L'AUTOMATE

		// ONFAIT LE JOUEUR
		virus = new Player(1, 1, null, aut, Orientation.RIGHT, 1, map, this, 3, 0, TypeKey.NONE);
		map.setEntity(virus);
		// ajout d'un obstacle
		Obstacle obs = new Obstacle(0, 0, false, true, false, false, null, map, this);
		m_obstacles.add(obs);
		map.setEntity(obs);

		obs = new Obstacle(1, 0, false, true, false, false, null, map, this);
		m_obstacles.add(obs);
		map.setEntity(obs);
		for (int i = 1; i < 100; i++) {
			obs = new Obstacle(0, i, false, true, false, false, null, map, this);
			m_obstacles.add(obs);
			map.setEntity(obs);
			obs = new Obstacle(i, 0, false, true, false, false, null, map, this);
			m_obstacles.add(obs);
			map.setEntity(obs);
		}
		// antivirus
		aut = Transversal.antivirusAutomaton();
		antivirus = new Player(8, 1, null, aut, Orientation.LEFT, 2, map, this, 3, 0, TypeKey.NONE);
		map.setEntity(antivirus);

		PowerUp PU = new PowerUp(4, 3, this);
		m_powerup.add(PU);
		map.setEntity(PU);

	}

	@Override
	public void step(long now) {
		Iterator<Minion> iterM = m_minions.iterator();
		Iterator<Obstacle> iterO = m_obstacles.iterator();
		Iterator<Laser> iterL = m_laser.iterator();

		// map.printMap();
		Minion m;
		while (iterM.hasNext()) {
			m = iterM.next();
			m.step(now);
		}

		Obstacle o;
		while (iterO.hasNext()) {
			o = iterO.next();
			o.step(now);
		}

		Laser l;
		while (iterL.hasNext()) {
			l = iterL.next();
			l.step(now);
		}
		if (virus.getLife() > 0) {
			virus.step(now);
		}
		if (antivirus.getLife() > 0) {
			antivirus.step(now);
		}
		System.out.println("\n");
		// Affichage du modele
	}

	@Override
	public void shutdown() {
	}

	// private void loadSprites() {

	// }
}