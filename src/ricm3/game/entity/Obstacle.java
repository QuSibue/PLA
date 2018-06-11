package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Options;

public class Obstacle extends Environment {

	public Obstacle(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal,
			BufferedImage[] sprites, Map map, Model modele) {
		super(x, y, moveable, pickable, killable, lethal, sprites, map, modele);

	}

	// @override
	public void step(long now) {

	}

	@Override
	public void paint(Graphics g) {
		// affiche un carré bleu pour le joueur
		int m_x = this.getX() * Options.TAILLE_CASE;
		int m_y = this.getY() * Options.TAILLE_CASE;
		g.setColor(Color.DARK_GRAY);
		g.fillRect(m_x, m_y, Options.TAILLE_CASE, Options.TAILLE_CASE);

	}
}
