package ricm3.game.entity;

import java.awt.image.BufferedImage;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Orientation;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;

public abstract class Character extends Being {
	private int m_equipe;
	

	public Character(BufferedImage[] sprites, int x, int y, boolean moveable, boolean pickable, boolean killable,
			boolean lethal, int moveSpeed, Automaton automate, Orientation orientation, int equipe, Map map,
			Model model, int life, long lastMove) {
		super(x, y, moveable, pickable, killable, lethal, moveSpeed, sprites, automate, orientation, map, model, life,
				lastMove);
		m_equipe = equipe;
	}

	int getEquipe() {
		return m_equipe;
	}

	void setEquipe(int equipe) {
		m_equipe = equipe;
	}
	
	public void protect() {
		setProtection(true);
	}
}
