package ricm3.game.automaton;

import ricm3.game.entity.Being;
import ricm3.game.entity.Environment;
import ricm3.game.mvc.Map;
import ricm3.game.other.Options;
import ricm3.game.other.Transversal;

public class Condition {
	private TypeCondition m_type;
	private Direction m_direction;

	// Operator vaut soit ' ' soit '&' soit '|'
	private char m_operator;
	private Condition m_condition;

	public boolean eval(Being b, Map m) {
		int x=0, y=0;
		boolean res;

		/**
		 * Si la conditon est de type FREE On va aller regarder sur la map a la case
		 * correspondant a la direction indiquée dans la condition, si la case est
		 * libre.
		 */
		if (m_type.equals(TypeCondition.FREE)) {
			Transversal.positionRelative(b.getX(), b.getY(), x, y, m_direction, b.getOrientation());
			res = m.getEntity(x, y) == null;
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
			Transversal.positionRelative(b.getX(), b.getY(), x, y, m_direction, b.getOrientation());
			res = m.getEntity(x, y) instanceof Environment;
			if (m_operator == 0)
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
