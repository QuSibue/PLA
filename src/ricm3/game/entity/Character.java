package ricm3.game.entity;

import java.awt.image.BufferedImage;

import ricm3.game.automaton.Automaton;

public abstract class Character extends Being {
	private int m_equipe;
	private int m_vie;

	public Character(int x, int y, boolean moveable, boolean pickable, boolean killable,
			boolean lethal, int moveSpeed, BufferedImage[] sprites,Automaton automate, int equipe, int vie) {
		super(x, y, moveable, pickable, killable, lethal, moveSpeed, sprites, automate);
		m_equipe = equipe;
		m_vie = vie;
	}

	int getEquipe() {
		return m_equipe;
	}

	void setEquipe(int equipe) {
		m_equipe = equipe;
	}

}
