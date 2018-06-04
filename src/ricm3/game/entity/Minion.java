package ricm3.game.entity;

public class Minion extends Character {

	public void move() {
		int x = this.getX(); // X courant du minion
		int y = this.getY(); // Y courant du minion
		double xRand = Math.random(); // Valeur random entre 0 et 1
		double yRand = Math.random();
		int xMove = (int) (x + xRand);
		int yMove = (int) (y + yRand);
		if (global_map.getEntity(xMove, yMove) == null) { // Si la case est libre
			this.setX(xMove); //MAJ des coordonnées
			this.setY(yMove);
		}
	}

	public void pop() {
		return;
	}

	public void wizz() {
		return;
	}

	public void hit() {
		return;
	}

	public void power() {
		return;
	}

	public void protect() {
		return;
	}

	public void jump() {
		return;
	}

	public void pick() {
		return;
	}

	public void get() {
		return;
	}

	public void store() {
		return;
	}

	public void _throw() {
		return;
	}

}
