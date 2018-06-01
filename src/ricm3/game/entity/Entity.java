package ricm3.game.entity;

import java.awt.image.BufferedImage;

public abstract class Entity {

	int coordinateX;
	int coordinateY;
	boolean moveable;
	boolean pickable;
	boolean killable;
	boolean lethal;
	BufferedImage[] sprites;

	public Entity(int x, int y, boolean move, boolean pick, boolean kill, boolean leth, BufferedImage[] spri) {
		coordinateX = x;
		coordinateY = y;
		moveable = move;
		pickable = pick;
		killable = kill;
		lethal = leth;
		sprites = spri;

	}

}
