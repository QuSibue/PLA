package ricm3.game.entity;

import java.awt.image.BufferedImage;

public abstract class Environment extends Entity {

	public Environment(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal,
			BufferedImage[] sprites) {
		super(x, y, moveable, pickable, killable, lethal, sprites);
	}

}
