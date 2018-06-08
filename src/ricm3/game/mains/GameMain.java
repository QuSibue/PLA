package ricm3.game.mains;

import java.awt.Dimension;
import java.io.IOException;

import ricm3.game.framework.GameUI;
import ricm3.game.mvc.Controller;
import ricm3.game.mvc.Model;
import ricm3.game.mvc.View;
import ricm3.game.menu.Menu;;

public class GameMain {
	
	public static boolean gameOn;
	
	public static void main(String[] args) throws IOException {

		// construct the game elements: model, controller, and view.
		gameOn = false;
		Menu menu = new Menu();
		menu.afficherMenu();
		
		if(gameOn) {
			Model model = new Model();
			View view = new View(model);
			Controller controller = new Controller(model);

			Dimension d = new Dimension(1024, 768);
			new GameUI(model, view,d,controller);
			
		}
		
		return;
	}
}
