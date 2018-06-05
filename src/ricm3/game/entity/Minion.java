package ricm3.game.entity;

import java.awt.image.BufferedImage;
import java.util.Random;

import ricm3.game.automaton.Direction;
import ricm3.game.other.Options;
import ricm3.game.other.Transversal;

public class Minion extends Character {

	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Transversal.positionRelative(this.getX(), this.getY(), x_res, y_res, d, this.getOrientation());
	}

	public void pop() {
		int x = this.getX(); // X courant du minion
		int y = this.getY(); // Y courant du minion
		Entity e= global_map.getEntity(x, y+1);
		// test si on ne sort pas de la MAP
		if (e!=null) {
			if(e instanceof Wall) {				
				
			}
			else if (e instanceof Minion) {
				Laser.lifespan--;
				if (laser.lifespan==0) {
					// Supprimer le minion
				}
			}
			
			
		}
		e = global_map.getEntity(x+1, y+1);
		if (e!=null) {
			if(e instanceof Wall) {				
				
			}
			else if (e instanceof Minion) {
				Laser.lifespan--;
				if (laser.lifespan==0) {
					// Supprimer le minion
				}
			}
			
		
		e = global_map.getEntity(x+1, y);
		if (e!=null) {
			if(e instanceof Wall) {				
				
			}
			else if (e instanceof Minion) {
				Laser.lifespan--;
				if (laser.lifespan==0) {
					// Supprimer le minion
				}
			}
			
		
		e = global_map.getEntity(x+1, y-1);
		if (e!=null) {
			if(e instanceof Wall) {				
				
			}
			else if (e instanceof Minion) {
				Laser.lifespan--;
				if (laser.lifespan==0) {
					// Supprimer le minion
				}
			}
			
		
		e = global_map.getEntity(x, y-1);
		if (e!=null) {
			if(e instanceof Wall) {				
				
			}
			else if (e instanceof Minion) {
				Laser.lifespan--;
				if (laser.lifespan==0) {
					// Supprimer le minion
				}
			}
			
		
		e = global_map.getEntity(x-1, y-1);
		if (e!=null) {
			if(e instanceof Wall) {				
				
			}
			else if (e instanceof Minion) {
				Laser.lifespan--;
				if (laser.lifespan==0) {
					// Supprimer le minion
				}
			}
			
		
		e = global_map.getEntity(x-1, y);	if (e!=null) {
			if(e instanceof Wall) {				
				
			}
			else if (e instanceof Minion) {
				Laser.lifespan--;
				if (laser.lifespan==0) {
					// Supprimer le minion
				}
			}
			
		
		
		e = global_map.getEntity(x-1, y+1);
		if (e!=null) {
			if(e instanceof Wall) {				
				
			}
			else if (e instanceof Minion) {
				Laser.lifespan--;
				if (laser.lifespan==0) {
					// Supprimer le minion
				}
			}
			
		
	}

	public void wizz() {
		BufferedImage[] buff = null;
		int valeurMin = 2;
		int valeurMax = 5;
		boolean valide = false;
		int x = this.getX(); // X courant
		int y = this.getY(); // Y courant
		Random r = new Random(); // Valeur aléatoire avec borne max et min
		int xRand = valeurMin + r.nextInt(valeurMax + 1 - valeurMin);
		int yRand = valeurMin + r.nextInt(valeurMax + 1 - valeurMin);
		int xMove = x + xRand;
		int yMove = y + yRand;
		while (!valide) { // tant que valide est faux on teste la position des valeurs random
			// test si on ne sort pas de la MAP
			if (xMove >= 0 && yMove >= 0 && yMove < Options.NB_TILE_HEIGHT_MAP && xMove < Options.NB_TILE_WIDTH_MAP) {

				if (global_map.getEntity(xMove, yMove) == null) { // Si la case est libre
					valide = true;
					// placer un portail à la position actuelle du mignon
					// placer la sortie du portail à xmove,ymove
					Portal p = new Portal(xMove, yMove, xMove, yMove, buff);
					global_map.setEntity(p);
					global_map.setEntity(x, y, p);
					// supprimer le sbire

					// to do

					return;
				}
			}
			// Si valide est toujours à false alors on recalcul les valeurs
			r = new Random();
			xRand = valeurMin + r.nextInt(valeurMax + 1 - valeurMin);
			yRand = valeurMin + r.nextInt(valeurMax + 1 - valeurMin);
			xMove = x + xRand;
			yMove = y + yRand;

		}
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

	public void jump() { // Pas implémenté pour les minions
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
