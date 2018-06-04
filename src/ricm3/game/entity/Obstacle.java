package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ricm3.game.mvc.Map;

public class Obstacle extends Environment{

	public Obstacle(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, BufferedImage[] sprites, Map map) {
		super(x, y, moveable, pickable, killable, lethal, sprites, map);
	
	}
	
	public void paint(Graphics g) {
		return;
	}
}
