package ricm3.game.entity;

public class Laser extends Being {

	int lifespan;
	int damage;

	// constructor
	Laser(int lifespan, int damage) {
		this.lifespan = lifespan;
		this.damage = damage;

	}

	// getters , setters
	int getLife(Laser l) {
		return l.lifespan;
	}

	boolean setLife(Laser l, int lifespan) {
		l.lifespan = lifespan;
		return true;
	}

	int getDam(Laser l) {
		return l.damage;
	}

	boolean setDam(Laser l, int damage) {
		l.damage = damage;
		return true;
	}

	// POP : the laser splits in two
	public void pop(Laser l) {

		int life = getLife(l);
		int dam = getDam(l);

		if (life == 1) {
			return;
		}

		else {

			life = life / 2;

			setLife(l, life);
			setDam(l, dam);
			splitter(l);
			/*
			 * Laser l2 = new Laser(life, dam); splitter(l2);
			 */
		}

	}

	// auxiliary of pop, searches for empty tiles around pop location, calls Wizz if
	// none is available
	// CHECK NAMES OF EXTERNAL METHODS : GETORIENTATION,

	public void splitter(Laser l) {

		int cx = GetX(l);
		int cy = GetY(l);
		Orientation o = getOrientation(l);
		int life = getLife(l);
		int dam = getDam(l);
		int dx1, dy1, dx2, dy2;
		Laser l_copie = l; // ??? Dunno if it's still l or a copy, bug expected

		// we get coordinates of the tile in front of the laser
		int dx, dy;
		positionRelative(cx, cy, dx, dy, up, o);

		if (getEntity(dx, dy) == null) { // tile in front is free

			// we get the coordinate of the tiles up left and up right relatively to the
			// laser's position
			positionRelative(dx, dy, dx1, dy1, right, o);
			positionRelative(dx, dy, dx2, dy2, left, o);

			if (getEntity(dx1, dy1) == null) { // one half "moved" diagonally
				setEntity(dx1, dy1, l);
				setOrientation(l, right);
			} else { // laser causes explosion in front of itself
				setX(dx, dy, l);
				wizz(l);
			}
			if (getEntity(dx2, dy2) == null) { // one half "moved" diagonally
				setEntity(dx2, dy2, l_copie);
				setOrientation(l_copie, left);
			} else { // laser causes explosion in front of itself
				setX(dx, dy, l_copie);
				wizz(l_copie);
			}
		}

		else { // getEntity(dx,dy) != null

			// we get the coordinates of the tiles left and right of the laser
			positionRelative(cx, cy, dx1, dy1, right, o);
			positionRelative(cx, cy, dx2, dy2, left, o);

			if (getEntity(dx1, dy1) == null) { // one half "moved" on the right
				setEntity(dx1, dy1, l);
				setOrientation(l, right);
			} else { // laser causes explosion
				setX(dx, dy, l);
				wizz(l);
			}
			if (getEntity(dx2, dy2) == null) { // one half "moved" on the left
				setEntity(dx2, dy2, l_copie);
				setOrientation(l_copie, left);
			} else { // laser causes explosion
				setX(dx, dy, l_copie);
				wizz(l_copie);
			}

		}

	}

	public void hit (Entity E) {
		
		if (E == null) {  // just in case getEntity gives it null
			return;
		}
		if (getKillable(E) == true ) {  
			E.vie = E.vie - 1;
		}
	}

	public void Wizz (Laser l, Map map) {
		int cx = GetX(l);
		int cy = GetY(l);
		
		// hit all entities on the 8 adjacent tiles
		hit(getEntity(cx+1, cy)));
		hit(getEntity(cx, cy+1)));
		hit(getEntity(cx+1, cy+1)));
		hit(getEntity(cx, cy-1)));
		hit(getEntity(cx-1, cy-1)));
		hit(getEntity(cx+1, cy-1)));
		hit(getEntity(cx-1, cy)));
		hit(getEntity(cx-1, cy+1)));
		
		deleteEntity(l);
	}

	

	// absolute movement ( whether or not they're allowed checked by the automaton )

	public void move_up(Laser l) {

		int cx = GetX(l);
		int cy = GetY(l);
		int dx,dy;
		int life = getLife(l);

		dx = cx;
		dy = dy - 1;
		if (getEntity(dx, dy) == null) {
			setEntity(dx,dy,l)  // moves laser to the new location
			life --;
			if (life == 0) { // if out of time, laser despawns
				deleteEntity(l);
			}
			setLife(l, life) // decrease the time before the laser despawns
		}
		else {
			hit(getEntity(dx,dy));
			deleteEntity(l);
		}
		

	}

	public void move_down(Laser l) {
		
		int cx = GetX(l);
		int cy = GetY(l);
		int dx,dy;
		int life = getLife(l);

		dx = cx;
		dy = dy +1;
		if (getEntity(dx, dy) == null) {
			setEntity(dx,dy,l)  // moves laser to the new location
			life --;
			if (life == 0) { // if out of time, laser despawns
				deleteEntity(l);
			}
			setLife(l, life) // decrease the time before the laser despawns
		}
		else {
			hit(getEntity(dx,dy));
			deleteEntity(l);
		}
	}

	public void move_right(Laser l) {

		int cx = GetX(l);
		int cy = GetY(l);
		int dx,dy;
		int life = getLife(l);

		dx = cx+1;
		dy = cy;
		if (getEntity(dx, dy) == null) {
			setEntity(dx,dy,l)  // moves laser to the new location
			life --;
			if (life == 0) { // if out of time, laser despawns
				deleteEntity(l);
			}
			setLife(l, life) // decrease the time before the laser despawns
		}
		else {
			hit(getEntity(dx,dy));
			deleteEntity(l);
		}
	}

	public void move_left(Laser l) {

		int cx = GetX(l);
		int cy = GetY(l);
		int dx,dy;
		int life = getLife(l);

		dx = cx;
		dy = dy - 1;
		if (getEntity(dx, dy) == null) {
			setEntity(dx,dy,l)  // moves laser to the new location
			life --;
			if (life == 0) { // if out of time, laser despawns
				deleteEntity(l);
			}
			setLife(l, life) // decrease the time before the laser despawns
		}
		else {
			hit(getEntity(dx,dy));
			deleteEntity(l);
		}
	}

	
	
	// relative movement (uses the laser's orientation)
	
	public void move_up_relative(Laser l) {
		
		Orientation o = getOrientation(l);
		
		switch (o) {
			case up : move_up(l);
			case down : move_down(l);
			case left : move_left(l);
			case right : move_right(l);	
		}
		
	}
	
	public void move_down_relative(Laser l) {
		
		Orientation o = getOrientation(l);
		
		switch (o) {
			case up : move_down(l);
			case down : move_up(l);
			case left : move_right(l);
			case right : move_left(l);	
		}
		
	}
	
	public void move_right_relative(Laser l) {
		
		Orientation o = getOrientation(l);
		
		switch (o) {
			case up : move_right(l);
			case down : move_left(l);
			case left : move_up(l);
			case right : move_down(l);	
		}
		
	}
	
	public void move_left_relative(Laser l) {
		
		Orientation o = getOrientation(l);
		
		switch (o) {
			case up : move_left(l);
			case down : move_right(l);
			case left : move_down(l);
			case right : move_up(l);	
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	// TO DO
	/*
	 * 
	 * 
	 * 
	 */

}
