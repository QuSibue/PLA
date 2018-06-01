package ricm3.game.entity;

import java.awt.image.BufferedImage;

import ricm3.game.automaton.Behaviour;
import ricm3.game.automaton.Etat;

public abstract class Being extends Entity {

	// attributs
	int moveSpeed;
	Behaviour automate;
	Etat etat;

	// Constructor
	public Being(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, int ms,
			BufferedImage[] sprites, Behaviour aut, Etat etat) {

		// appel au constructeur de entity
		super(x, y, moveable, pickable, killable, lethal, sprites);

		this.moveSpeed = ms;
		this.automate = aut; // alias
		this.etat = new Etat(etat); // copie
	}

	// methodes abstraites

	public abstract void step();

	public abstract void move();

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
