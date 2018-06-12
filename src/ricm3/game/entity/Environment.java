package ricm3.game.entity;

import java.awt.image.BufferedImage;

import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;

public abstract class Environment extends Entity {

	public Environment(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal,
			BufferedImage[] sprites, Map map, Model model, ImageDataBase idb) {
		super(x, y, moveable, pickable, killable, lethal, sprites, map, model, idb);
	}

}
