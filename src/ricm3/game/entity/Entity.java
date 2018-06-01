package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {

	int coordinateX;
	int coordinateY;
	boolean moveable;
	boolean pickable;
	boolean killable;
	boolean lethal;
	BufferedImage[] sprites;

	public Entity(int x, int y, boolean move, boolean pick, boolean kill, boolean leth, BufferedImage[] sprites) {
		this.coordinateX = x;
		this.coordinateY = y;
		this.moveable = move;
		this.pickable = pick;
		this.killable = kill;
		this.lethal = leth;
		this.sprites = sprites;

	}
	
	public abstract void paint(Graphics g);

}
