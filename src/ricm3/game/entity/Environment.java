package ricm3.game.entity;

import java.awt.image.BufferedImage;

import ricm3.game.mvc.Map;

public abstract class Environment extends Entity {

	public Environment(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal,
			BufferedImage[] sprites, Map map) {
		super(x, y, moveable, pickable, killable, lethal, sprites,map);
	}

}
