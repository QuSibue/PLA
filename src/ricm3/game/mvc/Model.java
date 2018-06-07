package ricm3.game.mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import ricm3.game.automaton.Action;
import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Condition;
import ricm3.game.automaton.Etat;
import ricm3.game.automaton.Orientation;
import ricm3.game.automaton.Transition;
import ricm3.game.automaton.TypeAction;
import ricm3.game.automaton.TypeCondition;
import ricm3.game.entity.Laser;
import ricm3.game.entity.Minion;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Player;
import ricm3.game.framework.GameModel;
import ricm3.game.other.Transversal;
import ricm3.game.other.TypeKey;

public class Model extends GameModel {

	public LinkedList<Minion> m_minions;
	public LinkedList<Obstacle> m_obstacles;
	public LinkedList<Laser> m_laser;
	public Player virus;
	public Player antivirus;
	public Map map;
	public ArrayList<Automaton>m_automates;

	public Model(){
		m_minions = new LinkedList<Minion>();
		m_obstacles = new LinkedList<Obstacle>();
		m_laser = new LinkedList<Laser>();
		m_automates = new ArrayList<Automaton>();
		//sprites vont etres donné a l'instantiation normalement, a voir 
		//ON FAIT LA MAP
		map = new Map(1100,1200);
		
		//ON FAIT UN AUTOMATE
		Automaton aut = Transversal.playerAutomaton();
		//FIN DE L'AUTOMATE
		
		//ONFAIT LE JOUEUR
		virus  = new Player(1, 1, true, false, true, false, 100, null, aut,Orientation.RIGHT,1,map,this,3,0,TypeKey.NONE);
		map.setEntity(virus);
		//ajout d'un obstacle
		/*Obstacle obs = new Obstacle(4, 1, false, true, false, false, null,map,this);
		m_obstacles.add(obs);
		map.setEntity(obs);*/
		
		//antivirus
		antivirus  = new Player(8, 1, true, false, true, false, 100, null, aut,Orientation.LEFT,2,map,this,3,0,TypeKey.NONE);
		map.setEntity(antivirus);
		
		
	}

	@Override
	public void step(long now) {
		Iterator<Minion> iterM = m_minions.iterator();
		Iterator<Obstacle> iterO = m_obstacles.iterator();
		Iterator<Laser> iterL = m_laser.iterator();
		//map.printMap();
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
		virus.step(now);
		System.out.println("\n");
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

	// private void loadSprites() {

	// }
}