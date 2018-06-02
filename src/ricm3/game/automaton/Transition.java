package ricm3.game.automaton;

public class Transition {

	// Attributs de transition
	private Etat m_etatInit;
	private Condition m_condition;
	private Action m_action;
	private Etat m_etatSorti;

	// Constructeurs (normale et par recopie)
	public Transition(Etat init, Condition condi, Action action, Etat sortit) {
		m_etatInit = init;
		m_condition = condi;
		m_action = action;
		m_etatSorti = sortit;
	}

	public Transition(Transition t) {
		this(t.getInit(), t.getCondition(), t.getAction(), t.getSortit());
	}

	// getter et setter
	Etat getInit() {
		return m_etatInit;
	}

	Condition getCondition() {
		return m_condition;
	}

	Action getAction() {
		return m_action;
	}

	Etat getSortit() {
		return m_etatInit;
	}

	void setInit(Etat etat) {
		m_etatInit = etat;
	}

	void setCondition(Condition condi) {
		m_condition = condi;
	}

	void setAction(Action action) {
		m_action = action;
	}

	void setSortit(Etat sortit) {
		m_etatSorti = sortit;
	}
	
	
	//fonctions et méthodes
}

