package ricm3.game.entity;

import java.awt.image.BufferedImage;

import ricm3.game.automaton.Automaton;
import ricm3.game.other.Options;

public class Minion extends Character {
	
	Automaton[] Type_Minion;
	
	public Minion(int x, int y, BufferedImage[] sprites, Automaton aut) {
		
		super(x, y, true, true, true, false, Options.MS_MINION, sprites, aut);
	}
	
}
