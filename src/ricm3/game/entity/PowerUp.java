package ricm3.game.entity;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;


import ricm3.game.mvc.Model;
import ricm3.game.other.Options;

public class PowerUp extends Entity {

	public PowerUp(int x, int y, Model model,BufferedImage[][] sprites, ImageIcon icon,int nbImage) {
		super(x, y, false, true, true, false, sprites, nbImage,icon,model.map, model);

	}

	@Override
	public void paint(Graphics g) {
		int IndexFrame = 0;
		g.drawImage(this.getSprites()[0][IndexFrame], this.getX() * Options.TAILLE_CASE, this.getY() * Options.TAILLE_CASE,
				Options.TAILLE_CASE, Options.TAILLE_CASE, null);

	}

}
