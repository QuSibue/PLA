package ricm3.game.entity;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Etat;
import ricm3.game.automaton.Orientation;
import ricm3.game.automaton.Transition;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;

public abstract class Being extends Entity {
	
	// attributs
	private int m_moveSpeed;
	private Automaton m_automaton;
	private Etat m_etatCourant;
	private Orientation m_orientation;
	private int m_life;
	private long m_lastMove;
	


	// Constructor
	public Being(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, int ms, BufferedImage[] sprites,
			Automaton aut, Orientation orientation, Map map,Model model, int life, long lastMove) {

		// appel au constructeur de entity
		super(x, y, moveable, pickable, killable, lethal, sprites,map,model);

		m_moveSpeed = ms;
		m_automaton = aut; // alias
		//TODO rajouter l'orientation
		m_orientation = orientation;
		// ALiasing possible puisque on ne vas jamais modifier les objets
		m_etatCourant = m_automaton.getEtatInitial(); 
		m_life = life;
		m_lastMove = lastMove;
	}

	//getters
	public int getLife() {
		return m_life;
	}
	
	public int getMoveSpeed() {
		return m_moveSpeed;
	}
	
	public Automaton getAutomaton() {
		return m_automaton;
	}
	
	public Etat getEtatCourant() {
		return m_etatCourant;
	}
	
	public Orientation getOrientation() {
		return m_orientation;
	}
	
	//setters
	public boolean setLife(int life){
		m_life = life;
		return true;
	}
	
	public boolean setMoveSpeed(int movespeed) {
		m_moveSpeed = movespeed;
		return true;
	}
	
	public boolean setAutomaton(Automaton automaton) {
		m_automaton = automaton;
		return true;
	}
	
	public boolean setEtatCourant(Etat etat) {
		m_etatCourant = etat;
		return true;
	}
	
	public void reinitialisation() {
		m_etatCourant = m_automaton.getEtatInitial();
	}
	
	public long getLastMove() {
		return this.m_lastMove;
	}
	
	public void setLastMove(long lastMove) {
		this.m_lastMove = lastMove;
	}
	
	// methodes abstraites

	public void step(long now) {
		long elapsed = now - m_lastMove;
		if (elapsed > this.m_moveSpeed*1L) {
			m_lastMove = now;
			Iterator<Transition> iter = this.getAutomaton().getTransitions().iterator();
			Transition transi;
			boolean condition = false;

			// On va chercher la première transition utilisable
			// puis on met a jour l'etat courant
			// et on effectue l'action associée a la transition

			// ce code va surement être deplacé dans being, superclass de player, minion et
			// laser
			while (!condition && iter.hasNext()) {
				transi = iter.next();
				// les etats sont par aliasing on peut donc utiliser le double égale
				condition = this.getEtatCourant() == transi.getInitial()
						&& transi.getCondition().eval((Being) this, global_map);
				if (condition) {
					this.setEtatCourant(transi.getSortie());
					transi.getAction().executeAction(this);
				}

			}
			if (!condition)
				throw new RuntimeException("AUcune transition trouvée pour l'automate dans player");
		}
	}

	public abstract void move(Direction d);

	public abstract void pop();

	public abstract void wizz();

	public abstract void hit();
	 
	public void getDamage() {
		this.m_life --;
		if(this.m_life <= 0) {
			this.global_map.deleteEntity(this);
			if(this instanceof Minion) {
				m_model.m_minions.remove(this);
			}
			else if(this instanceof Laser) {
				m_model.m_laser.remove(this);
			}	
		}
	}

	public abstract void power();

	public abstract void protect();

	public abstract void jump();

	public abstract void pick();

	public abstract void get();

	public abstract void store();

	public abstract void _throw();
	
	public abstract void turn();
	
	public abstract void kamikaze();

}
