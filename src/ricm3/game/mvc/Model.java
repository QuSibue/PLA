package ricm3.game.mvc;

import java.util.Iterator;
import java.util.LinkedList;

import ricm3.game.automaton.Automaton;
import ricm3.game.entity.Being;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Player;
import ricm3.game.framework.GameModel;

public class Model extends GameModel {

	LinkedList<Being> m_printables;
	LinkedList<Obstacle> m_obstacles;
	Player joueur1;
	Player joueur2;

	public Model(){
		//sprites vont etres donné a l'instantiation normalement, a voir 
		//this.loadSprites();
		Automaton test =  new Automaton();
		//ajout du du player test
		m_printables.add(new Player(2, 2, true, false, true, false, 10, null,test));
		//ajout d'un obstacle
		m_obstacles.add(new Obstacle(2, 4, false, true, false, false, null));
		
	}
	@Override
	public void step(long now) {
		Iterator<Being> iter =  m_printables.iterator();
		Being e;
		while(iter.hasNext()) {
			e = iter.next();
			e.step();
		}
	}

	@Override
	public void shutdown() {

	}
	
	//private void loadSprites() {
		
	//}
}