package ricm3.game.entity;

import java.awt.Graphics;

import ricm3.game.mvc.Map;

public class Wall extends Environment {

	public Wall(int x, int y,Map map) {
		super(x, y, false, false, false, false, null,map);
	}

	public void pretty_print() {
		System.out.print("Wall");
	}

	public void paint(Graphics g) {
		return;
	}
}
