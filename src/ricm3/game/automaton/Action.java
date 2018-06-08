package ricm3.game.automaton;

import ricm3.game.entity.Being;

public class Action {
	private TypeAction m_action;
	private Direction m_direction;

	//Constructeur apr defaut
	public Action(TypeAction action, Direction d){
		m_action = action;
		m_direction = d;
	}
	
	//Contructeur par Copie
	/**
	 * @warning faire attention a l'aliasing pour la suite
	 */
	public Action( Action a){
		this(a.getTypeAction(), a.getDirection());
	}
	
	
	/**
	 * 
	 * Va faire appel a la methode  d'une entité correspondant a l'action voulue
	 */
	public void executeAction(Being b,long now) {
		switch (m_action){
		case MOVE:
			b.move(m_direction);
			break;
		case POP:
			b.pop(now);
			break;
		case IDLE:
			break;
		case HIT:
			b.hit(now);
			break;
		case JUMP:
			b.jump();
			break;
		case WIZZ:
			b.wizz();
			break;
		case TURN:
			b.turn(m_direction);
			break;
		case PROTECT:
			b.protect();
			break;
		case PICK:
			b.pick();
			break;
		case THROW:
			b._throw();
			break;
		case STORE:
			b.store();
			break;
		case GET:
			b.get();
			break;
		case POWER:
			b.power(now);
			break;
		case KAMIKAZE:
			b.kamikaze();
		default:
			throw new RuntimeException("Action NYI");
		}
		
	}
	
	//getters
	public TypeAction getTypeAction() {
		return m_action;
	}
	public Direction getDirection() {
		return m_direction;
	}
	
	
	//setters
	public boolean setTypeAction(TypeAction action) {
		m_action = action;
		return true;
	}
	
	public boolean setDirection(Direction direction) {
		m_direction = direction;
		return true;
	}
}
