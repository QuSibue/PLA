package ricm3.game.mains;

import java.awt.Dimension;

import ricm3.game.ath.ATHP1;
import ricm3.game.framework.GameUI;
import ricm3.game.mvc.Controller;
import ricm3.game.mvc.Model;
import ricm3.game.mvc.View;

public class GameMain {
	public static void main(String[] args) {

		// construct the game elements: model, controller, and view.
		Model model = new Model();
		ATHP1 a = new ATHP1();
		View view = new View(model);
		Controller controller = new Controller(model);

		Dimension d = new Dimension(1024, 768);
		new GameUI(model, view,d,controller,a);
		
		return;
	}
}
