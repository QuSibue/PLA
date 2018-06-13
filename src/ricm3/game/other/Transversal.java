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
			p.x++;
			break;

		case WEST:
			p.x--;
			break;
			
		case FRONT:
			switch (o) {
			case UP:
				p.y--;
				break;
			case DOWN:
				p.y++;
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
		Etat etatInitialAut = new Etat("0");
		Etat etatInitialTransition = etatInitialAut;
		Condition condi = new Condition(TypeCondition.TRUE, null, null,' ', null);
		Action action = new Action(TypeAction.MOVE, Direction.FRONT);
		Transition transition = new Transition(etatInitialTransition, condi, action, etatInitialTransition); 
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transition);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		
		return test;
	}
	
	public static Automaton idleAutomaton() {
		Etat etatInitialAut = new Etat("0");
		Etat etatInitialTransition = etatInitialAut;
		Condition condi = new Condition(TypeCondition.TRUE, null,null, ' ', null);
		Action action = new Action(TypeAction.IDLE, null);
		Transition transition = new Transition(etatInitialTransition, condi, action, etatInitialTransition); 
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transition);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		
		return test;
	}
	
	public static Automaton popAutomaton() {
		Etat etatInitialAut = new Etat("0");
		Etat etatInitialTransition = etatInitialAut;
		Condition condi = new Condition(TypeCondition.TRUE, null,null, ' ', null);
		Action action = new Action(TypeAction.POP, null);
		Transition transition = new Transition(etatInitialTransition, condi, action, etatInitialTransition); 
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transition);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		return test;
	}
	
	public static Automaton shootAutomaton() {
		Etat etatInitialAut = new Etat("0");
		Etat etatInitialTransition = etatInitialAut;
		Condition condi = new Condition(TypeCondition.TRUE, null,null, ' ', null);
		Action action = new Action(TypeAction.HIT, null);
		Transition transition = new Transition(etatInitialTransition, condi, action, etatInitialTransition); 
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transition);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		return test;
	}/*
	public static Automaton virusAutomaton() {
		Etat etatInitialAut = new Etat("0");
		Etat etatInitialTransition = etatInitialAut;
		Condition condiUp = new Condition(TypeCondition.KEYPRESSEDO, null, null,' ', null);
		Condition condiDown = new Condition(TypeCondition.KEYPRESSEDDOWN, null, null,' ', null);
		Condition condiLeft = new Condition(TypeCondition.KEYPRESSEDLEFT, null, null,' ', null);
		Condition condiRight = new Condition(TypeCondition.KEYPRESSEDRIGHT, null, null,' ', null);
		Condition condiIdle = new Condition(TypeCondition.KEYPRESSEDNONE, null, null,' ', null);
		Condition condiHit = new Condition(TypeCondition.KEYPRESSEDHIT, null, null,' ', null);
		Condition condiPop = new Condition(TypeCondition.KEYPRESSEDPOP, null, null,' ', null);
		Condition condiPick = new Condition(TypeCondition.KEYPRESSEDO,null,null,' ',null);
		Condition condiGet = new Condition(TypeCondition.KEYPRESSEDI,null,null,' ',null);
		Condition condiWizz = new Condition(TypeCondition.KEYPRESSEDW, null, null,' ', null);
		
		
		Action up = new Action(TypeAction.MOVE, Direction.NORTH);
		Action down = new Action(TypeAction.MOVE, Direction.SOUTH);
		Action left = new Action(TypeAction.MOVE, Direction.WEST);
		Action right = new Action(TypeAction.MOVE, Direction.EAST);
		Action hit = new Action(TypeAction.HIT, null);
		Action pop = new Action(TypeAction.POP, null);		
		Action idle = new Action(TypeAction.IDLE, null);
		Action pick = new Action(TypeAction.PICK,null);
		Action get = new Action(TypeAction.GET,null);
		Action wizz = new Action(TypeAction.WIZZ, null);
		
		Transition transitionUp = new Transition(etatInitialTransition, condiUp, up, etatInitialTransition);
		Transition transitionDown = new Transition(etatInitialTransition, condiDown, down, etatInitialTransition);
		Transition transitionLeft = new Transition(etatInitialTransition, condiLeft, left, etatInitialTransition);
		Transition transitionRight = new Transition(etatInitialTransition, condiRight, right, etatInitialTransition);
		Transition transitionHit = new Transition(etatInitialTransition, condiHit, hit, etatInitialTransition);
		Transition transitionPop = new Transition(etatInitialTransition, condiPop, pop, etatInitialTransition);
		Transition transitionIdle = new Transition(etatInitialTransition, condiIdle, idle, etatInitialTransition);
		Transition transitionPick = new Transition(etatInitialTransition,condiPick,pick,etatInitialTransition);
		Transition transitionGet = new Transition(etatInitialTransition,condiGet,get,etatInitialTransition);
		Transition transitionWizz = new Transition(etatInitialTransition, condiWizz, wizz, etatInitialTransition);
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transitionUp);
		listTransitions.add(transitionDown);
		listTransitions.add(transitionLeft);
		listTransitions.add(transitionRight);
		listTransitions.add(transitionHit);
		listTransitions.add(transitionPop);
		listTransitions.add(transitionPick);
		listTransitions.add(transitionGet);
		listTransitions.add(transitionIdle);
		listTransitions.add(transitionWizz);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		return test;
	}
	
	public static Automaton antivirusAutomaton() {
		Etat etatInitialAut = new Etat("0");
		Etat etatInitialTransition = etatInitialAut;
		Condition condiUp = new Condition(TypeCondition.KEYPRESSEDZ, null, null,' ', null);
		Condition condiDown = new Condition(TypeCondition.KEYPRESSEDS, null, null,' ', null);
		Condition condiLeft = new Condition(TypeCondition.KEYPRESSEDQ, null, null,' ', null);
		Condition condiRight = new Condition(TypeCondition.KEYPRESSEDD, null, null,' ', null);
		Condition condiIdle = new Condition(TypeCondition.KEYPRESSEDNONE, null, null,' ', null);
		Condition condiHit = new Condition(TypeCondition.KEYPRESSEDF, null, null,' ', null);
		Condition condiPop = new Condition(TypeCondition.KEYPRESSEDG, null, null,' ', null);
		
		
		Action up = new Action(TypeAction.MOVE, Direction.NORTH);
		Action down = new Action(TypeAction.MOVE, Direction.SOUTH);
		Action left = new Action(TypeAction.MOVE, Direction.WEST);
		Action right = new Action(TypeAction.MOVE, Direction.EAST);
		Action hit = new Action(TypeAction.HIT, null);
		Action pop = new Action(TypeAction.POP, null);
		Action idle = new Action(TypeAction.IDLE, null);
		
		Transition transitionUp = new Transition(etatInitialTransition, condiUp, up, etatInitialTransition);
		Transition transitionDown = new Transition(etatInitialTransition, condiDown, down, etatInitialTransition);
		Transition transitionLeft = new Transition(etatInitialTransition, condiLeft, left, etatInitialTransition);
		Transition transitionRight = new Transition(etatInitialTransition, condiRight, right, etatInitialTransition);
		Transition transitionHit = new Transition(etatInitialTransition, condiHit, hit, etatInitialTransition);
		Transition transitionPop = new Transition(etatInitialTransition, condiPop, pop, etatInitialTransition);
		Transition transitionIdle = new Transition(etatInitialTransition, condiIdle, idle, etatInitialTransition);
				
		LinkedList<Transition> listTransitions = new LinkedList<Transition>();
		listTransitions.add(transitionUp);
		listTransitions.add(transitionDown);
		listTransitions.add(transitionLeft);
		listTransitions.add(transitionRight);
		listTransitions.add(transitionHit);
		listTransitions.add(transitionPop);
		listTransitions.add(transitionIdle);
		Automaton test =  new Automaton(etatInitialAut, listTransitions);
		return test;
	}
	*/
	public static int abs(int x) {
		if(x>=0) {
			return x;
		}
		else return -x;
	}
}
