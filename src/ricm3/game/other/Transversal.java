package ricm3.game.other;

import java.awt.Point;
import java.util.LinkedList;

import ricm3.game.automaton.Action;
import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Condition;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Etat;
import ricm3.game.automaton.Orientation;
import ricm3.game.automaton.Transition;
import ricm3.game.automaton.TypeAction;
import ricm3.game.automaton.TypeCondition;

public class Transversal {

	public static void evalPosition(int x, int y, Point p, Direction d, Orientation o) {
		p.x = x;
		p.y = y;

		switch (d) {
		case NORTH:
			p.y--;
			break;

		case SOUTH:
			p.y++;
			break;

		case EAST:
			p.x--;
			break;

		case WEST:
			p.x++;
			break;
			
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
	
	
	public static Automaton straightAutomaton() {
		Etat etatInitialAut = new Etat(0);
		Etat etatInitialTransition = etatInitialAut;
		Condition condi = new Condition(TypeCondition.TRUE, null, ' ', null);
		Action action = new Action(TypeAction.MOVE, Direction.FRONT);
		Transition transition = new Transition(etatInitialTransition, condi, action, etatInitialTransition); 
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transition);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		
		return test;
	}
	
	public static Automaton idleAutomaton() {
		Etat etatInitialAut = new Etat(0);
		Etat etatInitialTransition = etatInitialAut;
		Condition condi = new Condition(TypeCondition.TRUE, null, ' ', null);
		Action action = new Action(TypeAction.IDLE, null);
		Transition transition = new Transition(etatInitialTransition, condi, action, etatInitialTransition); 
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transition);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		
		return test;
	}
	
	public static Automaton popAutomaton() {
		Etat etatInitialAut = new Etat(0);
		Etat etatInitialTransition = etatInitialAut;
		Condition condi = new Condition(TypeCondition.TRUE, null, ' ', null);
		Action action = new Action(TypeAction.POP, null);
		Transition transition = new Transition(etatInitialTransition, condi, action, etatInitialTransition); 
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transition);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		return test;
	}
	
	public static Automaton shootAutomaton() {
		Etat etatInitialAut = new Etat(0);
		Etat etatInitialTransition = etatInitialAut;
		Condition condi = new Condition(TypeCondition.TRUE, null, ' ', null);
		Action action = new Action(TypeAction.HIT, null);
		Transition transition = new Transition(etatInitialTransition, condi, action, etatInitialTransition); 
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transition);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		return test;
	}
	public static Automaton playerAutomaton() {
		Etat etatInitialAut = new Etat(0);
		Etat etatInitialTransition = etatInitialAut;
		Condition condiUp = new Condition(TypeCondition.KEYPRESSEDUP, null, ' ', null);
		Condition condiDown = new Condition(TypeCondition.KEYPRESSEDDOWN, null, ' ', null);
		Condition condiLeft = new Condition(TypeCondition.KEYPRESSEDLEFT, null, ' ', null);
		Condition condiRight = new Condition(TypeCondition.KEYPRESSEDRIGHT, null, ' ', null);
		Condition condiIdle = new Condition(TypeCondition.KEYPRESSEDNONE, null, ' ', null);
		
		Action up = new Action(TypeAction.MOVE, Direction.NORTH);
		Action down = new Action(TypeAction.MOVE, Direction.SOUTH);
		Action left = new Action(TypeAction.MOVE, Direction.EAST);
		Action right = new Action(TypeAction.MOVE, Direction.WEST);
		Action idle = new Action(TypeAction.IDLE, null);
		
		Transition transitionUp = new Transition(etatInitialTransition, condiUp, up, etatInitialTransition);
		Transition transitionDown = new Transition(etatInitialTransition, condiDown, down, etatInitialTransition);
		Transition transitionLeft = new Transition(etatInitialTransition, condiLeft, left, etatInitialTransition);
		Transition transitionRight = new Transition(etatInitialTransition, condiRight, right, etatInitialTransition);
		Transition transitionIdle = new Transition(etatInitialTransition, condiIdle, idle, etatInitialTransition);
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transitionUp);
		listTransitions.add(transitionDown);
		listTransitions.add(transitionLeft);
		listTransitions.add(transitionRight);
		listTransitions.add(transitionIdle);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		return test;
	}
}
