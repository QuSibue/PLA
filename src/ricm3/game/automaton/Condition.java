package ricm3.game.automaton;

import java.awt.Point;
import java.util.Iterator;

import ricm3.game.entity.Being;
import ricm3.game.entity.Character;
import ricm3.game.entity.Entity;
import ricm3.game.entity.Laser;
import ricm3.game.entity.Minion;
import ricm3.game.entity.Player;
import ricm3.game.entity.Portal;
import ricm3.game.entity.PowerUp;
import ricm3.game.mvc.Map;
import ricm3.game.other.Transversal;
import ricm3.game.other.TypeKey;

public class Condition {
	private TypeCondition m_type;
	private Direction m_direction;
	private TypeEntity m_entity;

	// Operator vaut soit ' ' soit '&' soit '|'
	private char m_operator;
	private Condition m_condition;

	public Condition(TypeCondition type, Direction direction, TypeEntity entity, char operator, Condition condition) {
		m_type = type;
		m_direction = direction;
		m_operator = operator;
		m_condition = condition;
		m_entity = entity;
	}

	public boolean eval(Being b, Map m) {
		boolean res;

		/**
		 * Si la conditon est de type KEYPRESSEDUP On va aller regarder sur la map a la
		 * case correspondant a la direction indiquée dans la condition, si la case est
		 * libre.
		 */
		if (m_type.equals(TypeCondition.TRUE)) {
			return true;
		} else if (b instanceof Player
				&& (m_type.equals(TypeCondition.KEYPRESSEDZ) || m_type.equals(TypeCondition.KEYPRESSEDO))) {
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
		else if (b instanceof Player
				&& (m_type.equals(TypeCondition.KEYPRESSEDS) || m_type.equals(TypeCondition.KEYPRESSEDL))) {
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
		else if (b instanceof Player
				&& (m_type.equals(TypeCondition.KEYPRESSEDQ) || m_type.equals(TypeCondition.KEYPRESSEDK))) {
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
		else if (b instanceof Player
				&& (m_type.equals(TypeCondition.KEYPRESSEDD) || m_type.equals(TypeCondition.KEYPRESSEDM))) {
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
		else if (b instanceof Player && (m_type.equals(TypeCondition.KEYPRESSEDNONE))) {
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
		else if (b instanceof Player
				&& (m_type.equals(TypeCondition.KEYPRESSEDCOMMA) || m_type.equals(TypeCondition.KEYPRESSEDC))) {
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

		else if (b instanceof Player
				&& (m_type.equals(TypeCondition.KEYPRESSEDI) || m_type.equals(TypeCondition.KEYPRESSEDA))) {
			res = ((Player) b).getKey() == TypeKey.WIZZ;
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
		else if (b instanceof Player
				&& (m_type.equals(TypeCondition.KEYPRESSEDE) || m_type.equals(TypeCondition.KEYPRESSEDP))) {
			res = ((Player) b).getKey() == TypeKey.POP;
			if (m_operator == ' ')
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		} else if (b instanceof Player
				&& (m_type.equals(TypeCondition.KEYPRESSEDJ) || m_type.equals(TypeCondition.KEYPRESSEDF))) {
			res = ((Player) b).getKey() == TypeKey.PICK;
			if (m_operator == ' ')
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		} else if (b instanceof Player
				&& (m_type.equals(TypeCondition.KEYPRESSEDN) || m_type.equals(TypeCondition.KEYPRESSEDV))) {
			res = ((Player) b).getKey() == TypeKey.GET;
			if (m_operator == ' ')
				return res;
			else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		} else if (m_type.equals(TypeCondition.MYDIR)) {
			switch (m_direction) {
			case NORTH:
				res = b.getOrientation().equals(Orientation.UP);
				break;
			case EAST:
				res = b.getOrientation().equals(Orientation.RIGHT);
				break;
			case WEST:
				res = b.getOrientation().equals(Orientation.LEFT);
				break;
			case SOUTH:
				res = b.getOrientation().equals(Orientation.DOWN);
				break;
			default:
				throw new RuntimeException("My dir expect an absolute direction");
			}
			if (m_operator == ' ')
				return res;
			else if (m_operator == '!') {
				return !res;
			} else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");

		} else if (m_type.equals(TypeCondition.CELL)) {
			Point p = new Point();
			Transversal.evalPosition(b.getX(), b.getY(), p, m_direction, b.getOrientation());
			Entity e = b.global_map.getEntity(p.x, p.y);
			if (e == null) {
				if (m_entity.equals(TypeEntity.VOID)) {
					res = true;
				} else {
					res = false;
				}

			} else {
				switch (m_entity) {
				case PROJECTILE:
				case DANGER:
					res = e instanceof Laser;
					break;
				case ENEMY:
					res = b instanceof Character && e instanceof Character
							&& ((Character) b).getEquipe() != ((Character) e).getEquipe();
					break;
				case GATE:
					res = e instanceof Portal;
					break;
				case PICKABLE:
					res = e.getPickable();
					break;
				case TEAM:
					res = b instanceof Character && e instanceof Character
							&& ((Character) b).getEquipe() == ((Character) e).getEquipe();
					break;
				default:
					throw new RuntimeException("Unknown Type Entity");
				}
			}
			if (m_operator == ' ')
				return res;
			else if (m_operator == '!') {
				return !res;
			} else if (m_operator == '&')
				return res && m_condition.eval(b, m);
			else if (m_operator == '|')
				return res || m_condition.eval(b, m);
			else
				throw new RuntimeException("Operateur condition invalide");
		}

		else if (m_type.equals(TypeCondition.CLOSEST)) {

			if (m_entity == TypeEntity.ENEMY) {
				Character c;
				Character closest = null;
				// on cherche le caractère/sbires le plus proche
				Iterator<Minion> iter = b.m_model.m_minions.iterator();

				// on teste les deux joueurs
				// faut regarder les equipes, si on est virus on test pas virus ect
				// closest = (Character) b.closestEntity(b.m_model.antivirus, b.m_model.virus);

				if (b.m_model.virus.getEquipe() == ((Character) b).getEquipe()) {
					closest = b.m_model.antivirus;
				} else {
					closest = b.m_model.virus;
				}

				while (iter.hasNext()) {
					c = iter.next();
					// si c'est un ennemi alors il n'est pas dans mon equipe
					if (c.getEquipe() != ((Character) b).getEquipe()) {
						// on cherche le plus proche entre closest et c avec notre entity appelante
						closest = (Character) b.closestEntity(c, closest);
					}
				}

				// si il n'est pas dans la bonne direction on renvoit faux
				return this.isClosest(b, closest);

			}

			else if (m_entity == TypeEntity.TEAM) {
				Character c;
				Character closest = null;
				// on cherche le caractère/sbires le plus proche
				Iterator<Minion> iter = b.m_model.m_minions.iterator();

				// on teste les deux joueurs
				// faut regarder les equipes, si on est virus on test pas virus ect
				// closest = (Character) b.closestEntity(b.m_model.antivirus, b.m_model.virus);

				if (b.m_model.virus.getEquipe() == ((Character) b).getEquipe()) {
					closest = b.m_model.virus;
				} else {
					closest = b.m_model.antivirus;
				}

				while (iter.hasNext()) {
					c = iter.next();
					// si c'est un ennemi alors il n'est pas dans mon equipe
					if (c.getEquipe() == ((Character) b).getEquipe()) {
						// on cherche le plus proche entre closest et c avec notre entity appelante
						closest = (Character) b.closestEntity(c, closest);
					}
				}

				// direction
				return this.isClosest(b, closest);
			}
			// un danger est un laser (pour l'instant)
			else if (m_entity == TypeEntity.DANGER || m_entity == TypeEntity.PROJECTILE) {
				Laser l;
				// on cherche le laser le plus proche
				Laser closest = null;
				// on cherche le caractère/sbires le plus proche
				Iterator<Laser> iter = b.m_model.m_laser.iterator();

				// on teste les deux joueurs
				// faut regarder les equipes, si on est virus on test pas virus ect
				// closest = (Character) b.closestEntity(b.m_model.antivirus, b.m_model.virus);

				while (iter.hasNext()) {
					l = iter.next();
					closest = (Laser) b.closestEntity(l, closest);
				}

				return this.isClosest(b, closest);
			}
			// si il n'est pas dans la bonne direction on renvoit faux

			else if (m_entity == TypeEntity.VOID) {
				return true;
				// regarde sur la map les cases autours de nous
				// renvoit faux si aucune case libre directe dans la direction donnée
				// vrai si la cas est libre dans la direction

			} else if (m_entity == TypeEntity.PICKABLE) {
				Laser l = null;
				Character c = null;
				PowerUp p = null;
				Entity closest = null;
				// on cherche le caractère/sbires le plus proche
				Iterator<Laser> iterL = b.m_model.m_laser.iterator();
				Iterator<Minion> iterM = b.m_model.m_minions.iterator();
				Iterator<PowerUp> iterP = b.m_model.m_powerup.iterator();

				while (iterL.hasNext()) {
					l = iterL.next();
					closest = (Character) b.closestEntity(l, closest);
				}

				while (iterM.hasNext()) {
					c = iterM.next();
					closest = (Character) b.closestEntity(c, closest);
				}

				while (iterP.hasNext()) {
					p = iterP.next();
					closest = (Character) b.closestEntity(p, closest);
				}

				// renvoit faux si il existe un pickable plus proche dans une autre direction
				return this.isClosest(b, closest);
			}
			/*
			 * else if (m_entity == TypeEntity.GATE) { Portal p; Portal closest; // on
			 * cherche le caractère/sbires le plus proche Iterator<Portal> iterP =
			 * b.m_model.m_gate.iterator();
			 * 
			 * while (iterP.hasNext()) { p = iterP.next(); closest = (Character)
			 * b.closestEntity(p, closest); } // renvoit faux si il existe un portail plus
			 * proche dans une autre direction return this.isClosest(b, closest);
			 * 
			 * }
			 */
		}

		else {
			return true;
		}
		return false;
	}

	public boolean isClosest(Being b, Entity closest) {
		if( closest == null) {
			return false;
		}
		else if (b.getY() > closest.getY()
				&& Transversal.abs(b.getY() - closest.getY()) >= Transversal.abs(b.getX() - closest.getX())) {
			// direction absolue
			if (m_direction == Direction.NORTH) {
				return true;
			}
			// direction relative
			else if ((b.getOrientation() == Orientation.UP && m_direction == Direction.FRONT)
					|| (b.getOrientation() == Orientation.DOWN && m_direction == Direction.BACK)
					|| (b.getOrientation() == Orientation.LEFT && m_direction == Direction.RIGHT)
					|| (b.getOrientation() == Orientation.RIGHT && m_direction == Direction.LEFT)) {
				return true;
			}
		}
		// EST
		else if (b.getX() < closest.getX()
				&& Transversal.abs(b.getX() - closest.getX()) >= Transversal.abs(b.getY() - closest.getY())) {
			// direction absolue
			if (m_direction == Direction.EAST) {
				return true;
			}
			// direction relative
			else if ((b.getOrientation() == Orientation.UP && m_direction == Direction.RIGHT)
					|| (b.getOrientation() == Orientation.DOWN && m_direction == Direction.LEFT)
					|| (b.getOrientation() == Orientation.LEFT && m_direction == Direction.FRONT)
					|| (b.getOrientation() == Orientation.RIGHT && m_direction == Direction.BACK)) {
				return true;
			}
		}

		// SOUTH
		else if (b.getY() < closest.getY()
				&& Transversal.abs(b.getY() - closest.getY()) >= Transversal.abs(b.getX() - closest.getX())) {
			// direction absolue
			if (m_direction == Direction.SOUTH) {
				return true;
			}
			// direction relative
			else if ((b.getOrientation() == Orientation.UP && m_direction == Direction.BACK)
					|| (b.getOrientation() == Orientation.DOWN && m_direction == Direction.FRONT)
					|| (b.getOrientation() == Orientation.LEFT && m_direction == Direction.RIGHT)
					|| (b.getOrientation() == Orientation.RIGHT && m_direction == Direction.LEFT)) {
				return true;
			}
		}
		// WEST
		else if (b.getX() > closest.getX()
				&& Transversal.abs(b.getX() - closest.getX()) >= Transversal.abs(b.getY() - closest.getY())) {
			// direction absolue
			if (m_direction == Direction.WEST) {
				return true;
			}
			// direction relative
			else if ((b.getOrientation() == Orientation.UP && m_direction == Direction.LEFT)
					|| (b.getOrientation() == Orientation.DOWN && m_direction == Direction.RIGHT)
					|| (b.getOrientation() == Orientation.LEFT && m_direction == Direction.FRONT)
					|| (b.getOrientation() == Orientation.RIGHT && m_direction == Direction.BACK)) {
				return true;
			}
		}
		return false;
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
