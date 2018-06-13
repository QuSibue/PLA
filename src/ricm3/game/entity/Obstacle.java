package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Options;

public class Obstacle extends Environment {

	public Obstacle(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal,
			BufferedImage[][] sprites,int nbImage, Map map, Model modele, ImageDataBase idb) {
		super(x, y, moveable, pickable, killable, lethal, sprites, nbImage, map, modele, idb);

	}

	// @override
	public void step(long now) {

	}

	@Override
	public void paint(Graphics g) {
		// cet indice va devoir bouger pour animer l'obstacle
		int IndexFrame = 0;
		g.drawImage(this.getSprites()[0][IndexFrame], this.getX() * Options.TAILLE_CASE, this.getY() * Options.TAILLE_CASE,
				Options.TAILLE_CASE, Options.TAILLE_CASE, null);

	}
}
