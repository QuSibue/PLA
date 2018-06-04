package ricm3.game.entity;

import java.awt.image.BufferedImage;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Orientation;
import ricm3.game.mvc.Map;

public abstract class Character extends Being {
	private int m_equipe;

	public Character(BufferedImage[] sprites, int x, int y, boolean moveable, boolean pickable, boolean killable,
			boolean lethal, int moveSpeed, Automaton automate, Orientation orientation, int equipe, Map map) {
		super(x, y, moveable, pickable, killable, lethal, moveSpeed, sprites, automate, orientation,map);
		m_equipe = equipe;
	}

	int getEquipe() {
		return m_equipe;
	}

	void setEquipe(int equipe) {
		m_equipe = equipe;
	}

}
