package ricm3.game.automaton;

import java.util.LinkedList;

public class Automaton {
	private Etat m_etatInitial;
	private LinkedList<Transition> m_transitions;
	
	
	public Automaton() {
		//empty, just for test
	}
	public Automaton(Etat etatInitial, LinkedList<Transition> transitions){
		m_etatInitial = etatInitial;
		m_transitions = transitions; 
	}
	
	//setter
	public boolean addTransition(Transition t){
		return m_transitions.add(t);
	}
	
	public boolean setListTransitions(LinkedList<Transition> t) {
		m_transitions = t;
		return true;
	}
	public boolean setEtatInitial(Etat e){
		m_etatInitial = e;
		return true;
	}
	
	public boolean setListAutomaton(LinkedList<Transition> t) {
		m_transitions = t;
		return true;
	}
	
	//Getters
	public Etat getEtatInitial() {
		return m_etatInitial;
	}
	
	public LinkedList<Transition> getTransitions(){
		return m_transitions;
	}
}
