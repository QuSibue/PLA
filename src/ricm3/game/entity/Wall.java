package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Wall extends Environment {

	public Wall(int x, int y) {
		super(x, y, false, false, false, false, null);
	}

	public void pretty_print() {
		System.out.print("Wall");
	}

	public void paint(Graphics g) {
		return;
	}
}
