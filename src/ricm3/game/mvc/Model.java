package ricm3.game.mvc;

import java.util.Iterator;
import java.util.LinkedList;

import ricm3.game.automaton.Action;
import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Condition;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Etat;
import ricm3.game.automaton.Orientation;
import ricm3.game.automaton.Transition;
import ricm3.game.automaton.TypeAction;
import ricm3.game.automaton.TypeCondition;
import ricm3.game.entity.Being;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Player;
import ricm3.game.framework.GameModel;

public class Model extends GameModel {

	LinkedList<Being> m_printables;
	LinkedList<Obstacle> m_obstacles;
	Player virus;
	public Map map;

	public Model(){
		m_printables = new LinkedList<Being>();
		m_obstacles = new LinkedList<Obstacle>();
		
		//sprites vont etres donné a l'instantiation normalement, a voir 
		//ON FAIT LA MAP
		map = new Map(5,6);
		
		//ON FAIT UN AUTOMATE
		Etat etatInitialAut = new Etat(0);
		Etat etatInitialTransition = etatInitialAut;
		Condition condi = new Condition(TypeCondition.TRUE, Direction.FRONT, ' ', null);
		Action action = new Action(TypeAction.MOVE, Direction.FRONT);
		Transition transition = new Transition(etatInitialTransition, condi, action, etatInitialTransition); 
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transition);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		//FIN DE L'AUTOMATE
		//ONFAIT LE JOUEUR
		Player  virus  = new Player(1, 1, true, false, true, false, 10, null, test,Orientation.RIGHT,map);
		map.setEntity(virus);
		//ajout du du player test
		m_printables.add(virus);
		//ajout d'un obstacle
		Obstacle obs = new Obstacle(4, 1, false, true, false, false, null);
		m_obstacles.add(obs);
		map.setEntity(obs);
		
	}

	@Override
	public void step(long now) {
		Iterator<Being> iter = m_printables.iterator();
		map.printMap();
		Being e;
		while (iter.hasNext()) {
			e = iter.next();
			e.step();
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