package ricm3.game.automaton;

public class Transition {

	// Attributs de transition
	private Etat m_etatInitial;
	private Condition m_condition;
	private Action m_action;
	private Etat m_etatSortie;

	// Constructeurs (normale et par recopie)
	public Transition(Etat initial, Condition condi, Action action, Etat sortie) {
		m_etatInitial = initial;
		m_condition = condi;
		m_action = action;
		m_etatSortie = sortie;
	}
	/**
	 * 
	 * @warning attention aliasing
	 */
	
	public Transition(Transition t) {
		this(t.getInitial(), t.getCondition(), t.getAction(), t.getSortie());
	}

	// getter
	public Etat getInitial() {
		return m_etatInitial;
	}

	public Condition getCondition() {
		return m_condition;
	}

	public Action getAction() {
		return m_action;
	}

	public Etat getSortie() {
		return m_etatSortie;
	}

	
	//setter
	public boolean setInitial(Etat etat) {
		m_etatInitial = etat;
		return true;
	}

	public boolean setCondition(Condition condi) {
		m_condition = condi;
		return true;
	}

	public boolean setAction(Action action) {
		m_action = action;
		return true;
	}

	public boolean setSortie(Etat sortie) {
		m_etatSortie = sortie;
		return true;
	}
	
	
	//fonctions et méthodes
	
}

