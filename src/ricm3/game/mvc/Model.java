package ricm3.game.mvc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import ricm3.game.ath.ATH;
import ricm3.game.ath.TimerATH;
import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Orientation;
import ricm3.game.entity.Laser;
import ricm3.game.entity.Minion;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Player;
import ricm3.game.entity.PowerUp;
import ricm3.game.framework.GameModel;
import ricm3.game.mains.GameMain;
import ricm3.game.other.Transversal;
import ricm3.game.other.TypeKey;
import ricm3.game.parser.Ast;
import ricm3.game.parser.AutomataParser;

public class Model extends GameModel {
	public BufferedImage[] kamikaze_droite;
	public BufferedImage[] kamikaze_derriere;
	public BufferedImage[] kamikaze_idle;

	public LinkedList<Minion> m_minions;
	public LinkedList<Obstacle> m_obstacles;
	public LinkedList<Laser> m_laser;
	public LinkedList<PowerUp> m_powerup;
	public Player virus;
	public Player antivirus;
	public ArrayList<Automaton> m_automates;
	public Map map;
	public ATH m_ath;

	public Model() {
		loadSprites();
		m_minions = new LinkedList<Minion>();
		m_obstacles = new LinkedList<Obstacle>();
		m_laser = new LinkedList<Laser>();
		m_powerup = new LinkedList<PowerUp>();
		m_automates = new ArrayList<Automaton>();
		loadAutomaton();

		// sprites vont etres donné a l'instantiation normalement, a voir
		// ON FAIT LA MAP
		map = new Map(1100, 1200);

		// ON FAIT UN AUTOMATE

		Ast tree = null;
		try {
			tree = AutomataParser.parserAutomates(GameMain.pathPlayer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		Automaton aut2 = ((ArrayList<Automaton>) tree.make()).get(0);

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

		m_ath = new ATH(this);
	}

	@Override
	public void step(long now) {

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
	}

	@Override
	public void shutdown() {
	}

	public void loadAutomaton() {
		m_automates.add(Transversal.straightAutomaton());
		m_automates.add(Transversal.shootAutomaton());
		m_automates.add(Transversal.idleAutomaton());

	}

	private void loadSprites() {
		File imageFile = new File("src/ricm3/sprites/Kamikaze droite debut.png");
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		int width = sprite.getWidth(null);
		int height = sprite.getHeight(null);
		kamikaze_droite = new BufferedImage[9];
		int m_w = width / 2;
		int m_h = height / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				int x = j * m_w;
				int y = i * m_h;
				kamikaze_droite[(i * 2) + j] = sprite.getSubimage(x, y, m_w, m_h);
			}
		}
		imageFile = new File("src/ricm3/sprites/Kamikaze droite fin.png");
		try {
			sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		width = sprite.getWidth(null);
		height = sprite.getHeight(null);
		m_w = width / 2;
		m_h = height / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int x = j * m_w;
				int y = i * m_h;
				kamikaze_droite[5+(i * 2) + j] = sprite.getSubimage(x, y, m_w, m_h);
			}
		}
		/////////////////////////////////////////////////////////////////
		imageFile = new File("src/ricm3/sprites/Kamikaze idle.png");
		try {
			sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		width = sprite.getWidth(null);
		height = sprite.getHeight(null);
		kamikaze_idle = new BufferedImage[6];
		m_w = width / 2;
		m_h = height / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				int x = j * m_w;
				int y = i * m_h;
				kamikaze_idle[(i * 2) + j] = sprite.getSubimage(x, y, m_w, m_h);
			}
		}
		//////////////////////////////////////////////////////////////////
		imageFile = new File("src/ricm3/sprites/Kamikaze derriere debut.png");
		try {
			sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		width = sprite.getWidth(null);
		height = sprite.getHeight(null);
		kamikaze_derriere = new BufferedImage[8];
		m_w = width / 2;
		m_h = height / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int x = j * m_w;
				int y = i * m_h;
				kamikaze_derriere[(i * 2) + j] = sprite.getSubimage(x, y, m_w, m_h);
			}
		}
		imageFile = new File("src/ricm3/sprites/Kamikaze derriere fin.png");
		try {
			sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		width = sprite.getWidth(null);
		height = sprite.getHeight(null);
		m_w = width / 2;
		m_h = height / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int x = j * m_w;
				int y = i * m_h;
				kamikaze_derriere[4+(i * 2) + j] = sprite.getSubimage(x, y, m_w, m_h);
			}
		}
		/////////////////////////////////////////////////////////////////
	}
}