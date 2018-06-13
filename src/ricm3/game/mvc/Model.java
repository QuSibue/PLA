package ricm3.game.mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JComboBox;

import ricm3.game.ath.ATH;
import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Orientation;
import ricm3.game.entity.Drapeau;
import ricm3.game.entity.ImageDataBase;
import ricm3.game.entity.Laser;
import ricm3.game.entity.Minion;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Player;
import ricm3.game.entity.PowerUp;
import ricm3.game.framework.GameModel;
import ricm3.game.mains.GameMain;
import ricm3.game.other.Options;
import ricm3.game.other.Transversal;
import ricm3.game.other.TypeKey;
import ricm3.game.parser.Ast;
import ricm3.game.parser.AutomataParser;

public class Model extends GameModel {

	public LinkedList<Minion> m_minions;
	public LinkedList<Obstacle> m_obstacles;
	public LinkedList<Laser> m_laser;
	public LinkedList<PowerUp> m_powerup;
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

	public Model(ArrayList<Integer> indices) {
		m_automates = new ArrayList<Automaton>();

		// initTree();
		m_tree = GameMain.m_tree;

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
		m_minions = new LinkedList<Minion>();
		m_obstacles = new LinkedList<Obstacle>();
		m_laser = new LinkedList<Laser>();
		m_powerup = new LinkedList<PowerUp>();
		// m_automates = new ArrayList<Automaton>();
		// loadAutomaton();

		m_idb = new ImageDataBase();

		// sprites vont etres donné a l'instantiation normalement, a voir
		// ON FAIT LA MAP
		map = new Map(15, 29);
		finPartie = false;
		afficherFin = false;

		// ON FAIT UN AUTOMATE

		Automaton aut = Transversal.virusAutomaton();
		// FIN DE L'AUTOMATE

		// ONFAIT LE JOUEUR

		virus = new Player(1, 1, m_idb.virusIdle, m_idb.nbFrameVirus, m_automates.get(0), Orientation.RIGHT, 1, map,
				this, 3, 0, TypeKey.NONE);

		map.setEntity(virus);
		// ajout d'un obstacle
		Obstacle obs = new Obstacle(0, 0, false, true, false, false, m_idb.obstacle, m_idb.nbFrameObstacle, map, this);
		m_obstacles.add(obs);
		map.setEntity(obs);

		obs = new Obstacle(1, 0, false, true, false, false, m_idb.obstacle, m_idb.nbFrameObstacle, map, this);
		m_obstacles.add(obs);
		map.setEntity(obs);
		for (int i = 1; i < 14; i++) {

			obs = new Obstacle(0, i, false, true, false, false, m_idb.obstacle, m_idb.nbFrameObstacle, map, this);

			m_obstacles.add(obs);
			map.setEntity(obs);
			obs = new Obstacle(i, 0, false, true, false, false, m_idb.obstacle, m_idb.nbFrameObstacle, map, this);
			m_obstacles.add(obs);
			map.setEntity(obs);
		}
		// antivirus
		// aut = Transversal.antivirusAutomaton();
		antivirus = new Player(8, 1, m_idb.antivirusIdle, m_idb.nbFrameAntivirus, m_automates.get(1), Orientation.LEFT,
				2, map, this, 3, 0, TypeKey.NONE);

		map.setEntity(antivirus);

		PowerUp PU = new PowerUp(4, 3, this, m_idb.powerUp, m_idb.nbFramePowerUp, m_idb);
		m_powerup.add(PU);
		map.setEntity(PU);

		m_drapeau = new Drapeau(4, 1, null, map, this);
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
			m_ath.step(now);
			// Affichage du modele

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

		else if (afficherFin) {
			GameMain.afficherFinPartie(4, this);
			afficherFin = false;
		}

	}

	@Override
	public void shutdown() {
	}

	public void loadAutomaton() {
		m_automates.add(Transversal.straightAutomaton());
		m_automates.add(Transversal.shootAutomaton());
		m_automates.add(Transversal.idleAutomaton());
		m_automates.add(Transversal.straightAutomaton());
		m_automates.add(Transversal.shootAutomaton());
		m_automates.add(Transversal.idleAutomaton());

	}

	// private void loadSprites() {

	// }
}