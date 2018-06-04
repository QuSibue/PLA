package ricm3.game.other;

import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Orientation;

public class Transversal {

	public static void positionAbsolue(int x, int y, int x2, int y2, Direction d) {
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

	public static void positionRelative(int x, int y, int x2, int y2, Direction d, Orientation o) {
		x2 = x;
		y2 = y;

		switch (d) {
		case FRONT:
			switch (o) {
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
				throw new RuntimeException("Orientation invalid");
			}
			break;

		case BACK:
			switch (o) {
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
				throw new RuntimeException("Orientation invalid");
			}
			break;

		case LEFT:
			switch (o) {
			case UP:
				x2--;
				break;
			case DOWN:
				x2++;
				break;
			case LEFT:
				y2++;
				break;
			case RIGHT:
				y2--;
				break;
			default:
				throw new RuntimeException("Orientation invalid");
			}
			break;

		case RIGHT:
			switch (o) {
			case UP:
				x2++;
				break;
			case DOWN:
				x2--;
				break;
			case LEFT:
				y2--;
				break;
			case RIGHT:
				y2++;
				break;
			default:
				throw new RuntimeException("Orientation invalid");
			}
			break;

		default:
			throw new RuntimeException("Direction invalid");
		}
	}
}
