package ricm3.game.automaton;

public class Action {
	TypeAction m_action;
	Direction m_direction;

	//Constructeur apr defaut
	public Action(TypeAction action, Direction d){
		m_action = action;
		m_direction = d;
	}
	
	//Contructeur par Copie
	public Action( Action a){
		this(a.getAction(), a.getDirection());
	}
	
	public TypeAction getAction() {
		return m_action;
	}
	public Direction getDirection() {
		return m_direction;
	}
}
