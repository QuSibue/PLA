package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Wall extends Environment {

	public Wall(int x, int y, boolean move, boolean pick, boolean kill, boolean leth, BufferedImage[] sprites) {
		super(x, y, move, pick, kill, leth, sprites);
	}

	public void paint(Graphics g) {
		return;
	}
}
