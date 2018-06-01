package ricm3.game.entity;

import java.awt.image.BufferedImage;

import ricm3.game.automaton.Behaviour;
import ricm3.game.automaton.Etat;

public abstract class Character extends Being {
	private int m_equipe;

	public Character(BufferedImage[] sprites, int x, int y, boolean moveable, boolean pickable, boolean killable,
			boolean lethal, int moveSpeed, Behaviour automate, Etat state, int equipe) {
		super(x, y, moveable, pickable, killable, lethal, moveSpeed, automate, state);
		setEquipe(equipe);
	}

	int getEquipe() {
		return m_equipe;
	}

	void setEquipe(int equipe) {
		m_equipe = equipe;
	}

}
