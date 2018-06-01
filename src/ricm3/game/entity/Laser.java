package ricm3.game.entity;

public class Laser extends Being {

	int lifespan;
	int damage;
	int type;

	Laser(int lifespan, int damage, int type) {
		this.lifespan = lifespan;
		this.damage = damage;
		this.type = type;
	}

	// the laser splits in two
	public void pop(Laser l, Map map) {

		if (l.lifespan == 1) {
			return;
		}

		else {
			l.lifespan = l.lifespan / 2;
			splitter(l,map);
			Laser l2 = new Laser(l.lifespan, l.damage, l.type);
			splitter(l2,map);
		}

	}

	// auxiliary of pop, searches for empty tiles around pop location, calls Wizz if none is available

	public void splitter(Laser l, Map map) {
		int cx = l.coordinateX;
		int cy = l.coordinateY;

		if ((map.getEntity(cx+1, cy)) == null) {
			l.coordinateX = cx + 1;
		} else if (map.getEntity(cx, cy+1) == null) {
			l.coordinateY = cy + 1;
		} else if (map.getEntity(cx+1, cy+1) == null) {
			l.coordinateY = cy + 1;
			l.coordinateY = cy + 1;
		} else if (map.getEntity(cx, cy-1) == null) {
			l.coordinateY = cy - 1;
		} else if (map.getEntity(cx-1, cy) == null) {
			l.coordinateX = cx - 1;
		} else if (map.getEntity(cx-1, cy-1) == null) {
			l.coordinateY = cy - 1;
			l.coordinateY = cy - 1;
		} else if (map.getEntity(cx+1, cy-1) == null) {
			l.coordinateY = cy - 1;
			l.coordinateY = cy + 1;
		} else if (map.getEntity(cx-1, cy+1) == null) {
			l.coordinateY = cy + 1;
			l.coordinateY = cy - 1;
		} else {
			Wizz(l);
		}

	}
	
	
	public void Wizz (Laser l, Map map) {
		int cx = l.coordinateX;
		int cy = l.coordinateY;
		
		// hit all entities on the 8 adjacent tiles
		hit.(map.getEntity(cx+1, cy));
		hit.(map.getEntity(cx, cy+1));
		hit.(map.getEntity(cx+1, cy+1));
		hit.(map.getEntity(cx, cy-1));
		hit.(map.getEntity(cx-1, cy-1));
		hit.(map.getEntity(cx+1, cy-1));
		hit.(map.getEntity(cx-1, cy+1));
		hit.(map.getEntity(cx-1, cy+1));
		
		Despawn(l);
	}

	
	// despawns laser once it's out of time
	public void Despawn(Laser l) {
		int cx = l.coordinateX;
		int cy = l.coordinateY;
		
		map[cx][cy]= 'n';
		l = null ;
			
	}
	
	// movement ( whether or not they're allowed checked by the automaton )
	
	public void Move_up (Laser l) {
		
		int cx = l.coordinateX;
		int cy = l.coordinateY;
		
		map[cx][cy]= 'n';   // we free the current tile
		map[cx][cy-1]= 'l'; // fill the new location
		l.lifespan--;       // decrease the time before the laser despawns
		if( l.lifespan ==0) {  // if out of time, laser dispawns
			Despawn(l);
		}
		
	}
	
	public void Move_down (Laser l) {
		
		int cx = l.coordinateX;
		int cy = l.coordinateY;
		
		map[cx][cy]= 'n';   // we free the current tile
		map[cx][cy+1]= 'l'; // fill the new location
		l.lifespan--;       // decrease the time before the laser despawns
		if( l.lifespan ==0) { // if out of time, laser dispawns
			Despawn(l);
		}
	}
	
	public void Move_right (Laser l) {
		
		int cx = l.coordinateX;
		int cy = l.coordinateY;
		
		map[cx][cy]= 'n';   // we free the current tile
		map[cx+1][cy]= 'l'; // fill the new location
		l.lifespan--;       // decrease the time before the laser despawns
		if( l.lifespan ==0) { // if out of time, laser dispawns
			Despawn(l);
		}
	}
	
	public void Move_left (Laser l) {
		
		int cx = l.coordinateX;
		int cy = l.coordinateY;
		
		map[cx][cy]= 'n';   // we free the current tile
		map[cx-1][cy]= 'l'; // fill the new location
		l.lifespan--;       // decrease the time before the laser despawns
		if( l.lifespan == 0) { // if out of time, laser dispawns
			Despawn(l);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
