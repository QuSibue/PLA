package ricm3.game.automaton;

import java.awt.Point;

import ricm3.game.entity.Being;
import ricm3.game.entity.Environment;
import ricm3.game.mvc.Map;
import ricm3.game.other.Transversal;
import ricm3.game.other.TypeKey;
import ricm3.game.entity.Player;

public class Condition {
	private TypeCondition m_type;
	private Direction m_direction;

	// Operator vaut soit ' ' soit '&' soit '|'
	private char m_operator;
	private Condition m_condition;

	public Condition(TypeCondition type, Direction direction, char operator, Condition condition) {
		m_type = type;
		m_direction = direction;
		m_operator = operator;
		m_condition = condition;
	}

	public boolean eval(Being b, Map m) {
		int x = 0, y = 0;
		boolean res;

		/**
		 * Si la conditon est de type FREE On va aller regarder sur la map a la case
		 * correspondant a la direction indiquée dans la condition, si la case est
		 * libre.
		 */
		if (m_type.equals(TypeCondition.FREE)) {
			Point p = new Point();
			Transversal.evalPosition(b.getX(), b.getY(), p, m_direction, b.getOrientation());
			res = m.getEntity(p.x, p.y) == null;
			if (m_operator == 0)
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		}
		/**
		 * Si la conditon est de type OBSTACLE On va aller regarder sur la map a la case
		 * correspondant a la direction indiquée dans la condition, si la case est est
		 * un WALL ou un OBSTACLE.
		 */
		else if (m_type.equals(TypeCondition.OBSTACLE)) {
			Point p = new Point();
			Transversal.evalPosition(b.getX(), b.getY(), p, m_direction, b.getOrientation());
			res = m.getEntity(p.x, p.y) instanceof Environment;
			if (m_operator == 0)
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		} else if (m_type.equals(TypeCondition.TRUE)) {
			return true;
		}
		/**
		 * Si la conditon est de type KEYPRESSEDUP On va aller regarder sur la map a la
		 * case correspondant a la direction indiquée dans la condition, si la case est
		 * libre.
		 */
		if (m_type.equals(TypeCondition.KEYPRESSEDUP) || m_type.equals(TypeCondition.KEYPRESSEDZ)) {
			res = ((Player) b).getKey() == TypeKey.UP;
			if (m_operator == ' ')
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		}
		/**
		 * Si la conditon est de type KEYPRESSEDDOWN On va aller regarder sur la map a
		 * la case correspondant a la direction indiquée dans la condition, si la case
		 * est libre.
		 */
		if (m_type.equals(TypeCondition.KEYPRESSEDDOWN) || m_type.equals(TypeCondition.KEYPRESSEDS)) {
			res = ((Player) b).getKey() == TypeKey.DOWN;
			if (m_operator == ' ')
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		}
		/**
		 * Si la conditon est de type KEYPRESSEDLEFT On va aller regarder sur la map a
		 * la case correspondant a la direction indiquée dans la condition, si la case
		 * est libre.
		 */
		if (m_type.equals(TypeCondition.KEYPRESSEDLEFT) || m_type.equals(TypeCondition.KEYPRESSEDQ)) {
			res = ((Player) b).getKey() == TypeKey.LEFT;
			if (m_operator == ' ')
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		}
		/**
		 * Si la conditon est de type KEYPRESSEDRIGHT On va aller regarder sur la map a
		 * la case correspondant a la direction indiquée dans la condition, si la case
		 * est libre.
		 */
		if (m_type.equals(TypeCondition.KEYPRESSEDRIGHT) || m_type.equals(TypeCondition.KEYPRESSEDD)) {
			res = ((Player) b).getKey() == TypeKey.RIGHT;
			if (m_operator == ' ')
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		}
		/**
		 * Si la conditon est de type KEYPRESSEDRIGHT On va aller regarder sur la map a
		 * la case correspondant a la direction indiquée dans la condition, si la case
		 * est libre.
		 */
		if (m_type.equals(TypeCondition.KEYPRESSEDNONE)) {
			res = ((Player) b).getKey() == TypeKey.NONE;
			if (m_operator == ' ')
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		}
		/**
		 * Si la conditon est de type KEYPRESSEDRIGHT On va aller regarder sur la map a
		 * la case correspondant a la direction indiquée dans la condition, si la case
		 * est libre.
		 */
		if (m_type.equals(TypeCondition.KEYPRESSEDHIT) || m_type.equals(TypeCondition.KEYPRESSEDF)) {
			res = ((Player) b).getKey() == TypeKey.HIT;
			if (m_operator == ' ')
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		}
		/**
		 * Si la conditon est de type KEYPRESSEDRIGHT On va aller regarder sur la map a
		 * la case correspondant a la direction indiquée dans la condition, si la case
		 * est libre.
		 */
		if (m_type.equals(TypeCondition.KEYPRESSEDPOP) || m_type.equals(TypeCondition.KEYPRESSEDG)) {
			res = ((Player) b).getKey() == TypeKey.POP;
			if (m_operator == ' ')
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		} else {
			throw new RuntimeException("Type condition invalide");
		}
	}

	// getters
	public TypeCondition getTypeCondition() {
		return m_type;
	}

	public Direction getDirection() {
		return m_direction;
	}

	public char getOperator() {
		return m_operator;
	}

	public Condition getCondition() {
		return m_condition;
	}

	// setters

	public boolean setTypeCondition(TypeCondition type) {
		m_type = type;
		return true;
	}

	public boolean setDirection(Direction direction) {
		m_direction = direction;
		return true;
	}

	public boolean setOperator(char operator) {
		m_operator = operator;
		return true;
	}

	public boolean setCondition(Condition condition) {
		m_condition = condition;
		return true;
	}
}
