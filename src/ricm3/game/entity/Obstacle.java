package ricm3.game.entity;

import java.awt.image.BufferedImage;

public class Obstacle extends Environment{

	public Obstacle(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, BufferedImage[] sprites) {
		super(x, y, moveable, pickable, killable, lethal, sprites);
	
	}
}
