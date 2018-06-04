package ricm3.game.entity;

import java.awt.image.BufferedImage;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Etat;
import ricm3.game.automaton.Orientation;

public abstract class Being extends Entity {

	// attributs
	private int m_moveSpeed;
	private Automaton m_automaton;
	private Etat m_etatCourant;
	private Orientation m_orientation;

	// Constructor
	public Being(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, int ms,
			BufferedImage[] sprites, Automaton aut) {

		// appel au constructeur de entity
		super(x, y, moveable, pickable, killable, lethal, sprites);

		m_moveSpeed = ms;
		m_automaton = aut; // alias

		// ALiasing possible puisque on ne vas jamais modifier les objets
		m_etatCourant = m_automaton.getEtatInitial();
	}

	// getters

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

	// setters

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

	// methodes abstraites

	public abstract void step();

	public abstract void paint();

	public abstract void move(Direction d);

	public abstract void attack();

	public abstract void pop();

	public abstract void wizz();

	public abstract void hit();

	public abstract void power();

	public abstract void protect();

	public abstract void jump();

	public abstract void pick();

	public abstract void get();

	public abstract void store();

	public abstract void _throw();

}
