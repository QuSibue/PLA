package ricm3.game.mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import ricm3.game.ath.ATH;
import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Orientation;
import ricm3.game.entity.Drapeau;
import ricm3.game.entity.ImageDataBase;
import ricm3.game.entity.Laser;
import ricm3.game.entity.Minion;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Player;
import ricm3.game.entity.Portal;
import ricm3.game.entity.PowerUp;
import ricm3.game.framework.GameModel;
import ricm3.game.mains.GameMain;
import ricm3.game.other.Options;
import ricm3.game.other.TypeKey;
import ricm3.game.parser.Ast;

public class Model extends GameModel {

	public LinkedList<Minion> m_minions;
	public LinkedList<Obstacle> m_obstacles;
	public LinkedList<Laser> m_laser;
	public LinkedList<PowerUp> m_powerup;
	public LinkedList<Portal> m_portal;
	public Player virus;
	public Player antivirus;
	public ArrayList<Automaton> m_automates;
	public Map map;
	public ATH m_ath;
	public boolean finPartie;
	public boolean afficherFin;
	public Ast m_tree;
	public Drapeau m_drapeau;
	public boolean flagCaptured = false;
	public ImageDataBase m_idb;
	public IconDataBase m_icb;
	public long m_last;

	public Model(ArrayList<Integer> indices) {
		m_automates = new ArrayList<Automaton>();

		// initTree();
		m_tree = GameMain.m_tree;
		m_last = 0;

		for (int i = 0; i < indices.size(); i++) {
			Automaton temp = ((ArrayList<Automaton>) m_tree.make()).get(indices.get(i));
			m_automates.add(temp);

		}
		init();

	}

	/*
	 * public void initTree() { try { m_tree =
	 * AutomataParser.parserAutomates(GameMain.pathPlayer); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * @SuppressWarnings("unchecked") Automaton aut2 = ((ArrayList<Automaton>)
	 * m_tree.make()).get(0); }
	 */

	public void init() {

		m_icb = new IconDataBase();
		m_minions = new LinkedList<Minion>();
		m_obstacles = new LinkedList<Obstacle>();
		m_laser = new LinkedList<Laser>();
		m_powerup = new LinkedList<PowerUp>();
		m_portal = new LinkedList<Portal>();
		// m_automates = new ArrayList<Automaton>();
		// loadAutomaton();

		m_idb = new ImageDataBase();

		// sprites vont etres donné a l'instantiation normalement, a voir
		// ON FAIT LA MAP
		map = new Map("map/1/map1.map", this);
		finPartie = false;
		afficherFin = false;

		// ON FAIT UN AUTOMATE

		// Automaton aut = Transversal.virusAutomaton();
		// FIN DE L'AUTOMATE

		// ONFAIT LE JOUEUR

		virus = new Player(4, 7, m_idb.virusIdle, m_idb.nbFrameVirus, m_automates.get(0), Orientation.RIGHT, 1, map,
				this, 3, 0, TypeKey.NONE);

		map.setEntity(virus);
		// ajout d'un obstacle
		// antivirus

		// aut = Transversal.antivirusAutomaton();
		antivirus = new Player(22, 8, m_idb.antivirusIdle, m_idb.nbFrameAntivirus, m_automates.get(1), Orientation.LEFT,
				2, map, this, 3, 0, TypeKey.NONE);

		map.setEntity(antivirus);


		Portal p = new Portal(12, 10, 12, 1, m_idb.portail, m_idb.nbFramePortail, null, map, this);
		m_portal.add(p);
		map.setEntity(p);
		
		Obstacle obs1 = new Obstacle(19, 8, true, true, false, false, this.m_idb.obstacle, this.m_idb.nbFrameObstacle, this.m_icb.m_obstacleSac,map, this);
		this.m_obstacles.add(obs1);
		map.setEntity(obs1);
		
		Obstacle obs2 = new Obstacle(5, 12, true, true, false, false, this.m_idb.obstacle, this.m_idb.nbFrameObstacle, this.m_icb.m_obstacleSac,map, this);
		this.m_obstacles.add(obs2);
		map.setEntity(obs2);
		
		Obstacle obs3 = new Obstacle(24, 3, true, true, false, false, this.m_idb.obstacle, this.m_idb.nbFrameObstacle, this.m_icb.m_obstacleSac,map, this);
		this.m_obstacles.add(obs3);
		map.setEntity(obs3);
		
		m_drapeau = new Drapeau(26, 8, m_idb.drapeau, m_idb.nbFrameDrapeau, null, map, this);
		map.setEntity(m_drapeau);

		m_ath = new ATH(this);

	}

	@Override
	public void step(long now) {

		if (!finPartie) {

			LinkedList<Minion> minionsClone = (LinkedList<Minion>) m_minions.clone();
			Iterator<Minion> iterM = minionsClone.iterator();

			LinkedList<Obstacle> obstaclesClone = (LinkedList<Obstacle>) m_obstacles.clone();
			Iterator<Obstacle> iterO = obstaclesClone.iterator();

			LinkedList<Laser> laserClone = (LinkedList<Laser>) m_laser.clone();
			Iterator<Laser> iterL = laserClone.iterator();

			LinkedList<Portal> portalClone = (LinkedList<Portal>) m_portal.clone();
			Iterator<Portal> iterP = portalClone.iterator();

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

			Portal p;
			while (iterP.hasNext()) {
				p = iterP.next();
				p.step(now);
			}
			if (virus.getLife() > 0) {
				virus.step(now);
			}
			if (antivirus.getLife() > 0) {
				antivirus.step(now);
			}
			m_ath.step(now);
			// Affichage du modele

			long elapsed = 0;
			if (m_last != 0) {
				elapsed = now - m_last;
			}
			m_last = now;
			Options.TIMER_POWER_UP = Options.TIMER_POWER_UP - elapsed;
			if (Options.TIMER_POWER_UP <= 0) {
				// kaboum
				int xr;
				int yr;
				int xr2;
				int yr2;
				do {
					Random rand = new Random();
					Random rand2 = new Random();

					xr = rand.nextInt(29);
					yr = rand.nextInt(15);

					xr2 = rand.nextInt(29);
					yr2 = rand.nextInt(15);

				} while (map.getEntity(xr, yr) != null || map.getEntity(xr2, yr2) != null);
				PowerUp p1 = new PowerUp(xr, yr, this, this.m_idb.powerUpV, this.m_icb.m_energieSac,
						this.m_idb.nbFramePowerUp, 0);
				PowerUp p2 = new PowerUp(xr2, yr2, this, this.m_idb.powerUpE, this.m_icb.m_energieSac,
						this.m_idb.nbFramePowerUp, 1);
				map.setEntity(p1);
				map.setEntity(p2);
				this.m_powerup.add(p1);
				this.m_powerup.add(p2);

				Options.TIMER_POWER_UP = 10000;
				// enlever de la liste des trucs à step
			}

			finPartie = virus.getLife() <= 0 || antivirus.getLife() <= 0 || m_ath.getTimer() <= 0 || flagCaptured;
			afficherFin = finPartie;

		}

		else if (virus.getLife() <= 0 && afficherFin) {
			GameMain.afficherFinPartie(1, this);
			afficherFin = false;

		} else if (antivirus.getLife() <= 0 && afficherFin) {
			GameMain.afficherFinPartie(2, this);
			afficherFin = false;
		}

		else if (m_ath.getTimer() <= 0 && afficherFin) {
			GameMain.afficherFinPartie(3, this);
			afficherFin = false;
		}

		else if (afficherFin && flagCaptured) {
			GameMain.afficherFinPartie(4, this);
			afficherFin = false;
		}

	}

	@Override
	public void shutdown() {

	}

	/*
	 * public void loadAutomaton() {
	 * m_automates.add(Transversal.straightAutomaton());
	 * m_automates.add(Transversal.shootAutomaton());
	 * m_automates.add(Transversal.idleAutomaton());
	 * m_automates.add(Transversal.straightAutomaton());
	 * m_automates.add(Transversal.shootAutomaton());
	 * m_automates.add(Transversal.idleAutomaton());
	 * 
	 * }
	 */

	// private void loadSprites() {

	// }
}