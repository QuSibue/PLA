package ricm3.game.other;

import javafx.geometry.Orientation;
import ricm3.game.automaton.Direction;

public class Transversal {

	public static void positionDeplacement(int x, int y, int x2, int y2, Direction d, Orientation o) {
		x2 = x;
		y2 = y;

		switch (d) {
		case UP:
			y2++;
			break;
			
		case DOWN:
			y2--;
			break;
			
		case LEFT:
			x2--;
			break;
			
		case RIGHT:
			x2++;
			break;
			
		default:
			throw new RuntimeException("Direction invalid");
		}
	}
}
