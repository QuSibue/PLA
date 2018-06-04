package game.main;

import java.awt.Dimension;

import ricm3.game.framework.GameUI;
import ricm3.game.mvc.Model;
import ricm3.game.mvc.View;


public class GameMain {
	public static void main(String[] args) {

		// construct the game elements: model, controller, and view.
		Model model = new Model();
		View view = new View(model);

		Dimension d = new Dimension(1024, 768);
		new GameUI(model, view, d);
		
		return;
	}
}
