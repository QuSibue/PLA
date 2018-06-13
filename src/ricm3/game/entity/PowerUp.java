package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ricm3.game.mvc.Model;
import ricm3.game.other.Options;

public class PowerUp extends Entity {

	public PowerUp(int x, int y, Model model,BufferedImage[][] sprites, ImageDataBase idb) {
		super(x, y, false, true, true, false, sprites, model.map, model, idb);
	}

	@Override
	public void paint(Graphics g) {
		int IndexFrame = 0;
		g.drawImage(this.getSprites()[0][IndexFrame], this.getX() * Options.TAILLE_CASE, this.getY() * Options.TAILLE_CASE,
				Options.TAILLE_CASE, Options.TAILLE_CASE, null);

	}

}
