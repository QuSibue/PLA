package ricm3.game.other;

import java.awt.Point;

import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Orientation;

public class Transversal {

	public static void positionAbsolue(int x, int y, Point p, Direction d) {
		p.x = x;
		p.y = y;

		switch (d) {
		case UP:
			p.y++;
			break;

		case DOWN:
			p.y--;
			break;

		case LEFT:
			p.x--;
			break;

		case RIGHT:
			p.x++;
			break;

		default:
			throw new RuntimeException("Direction invalid");
		}
	}

	public static void positionRelative(int x, int y, Point p, Direction d, Orientation o) {
		p.x = x;
		p.y = y;

		switch (d) {
		case FRONT:
			switch (o) {
			case UP:
				p.y++;
				break;
			case DOWN:
				p.y--;
				break;
			case LEFT:
				p.x--;
				break;
			case RIGHT:
				p.x++;
				break;
			default:
				throw new RuntimeException("Orientation invalid");
			}
			break;

		case BACK:
			switch (o) {
			case UP:
				p.y++;
				break;
			case DOWN:
				p.y--;
				break;
			case LEFT:
				p.x--;
				break;
			case RIGHT:
				p.x++;
				break;
			default:
				throw new RuntimeException("Orientation invalid");
			}
			break;

		case LEFT:
			switch (o) {
			case UP:
				p.x--;
				break;
			case DOWN:
				p.x++;
				break;
			case LEFT:
				p.y++;
				break;
			case RIGHT:
				p.y--;
				break;
			default:
				throw new RuntimeException("Orientation invalid");
			}
			break;

		case RIGHT:
			switch (o) {
			case UP:
				p.x++;
				break;
			case DOWN:
				p.x--;
				break;
			case LEFT:
				p.y--;
				break;
			case RIGHT:
				p.y++;
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
