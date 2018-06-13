package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import ricm3.game.mvc.Model;
import ricm3.game.other.Options;

public class PowerUp extends Entity {

	public PowerUp(int x, int y, Model model, ImageIcon icon) {
		super(x, y, false, true, true, false, null, icon, model.map, model);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		int m_x = this.getX() * Options.TAILLE_CASE;
		int m_y = this.getY() * Options.TAILLE_CASE;
		g.setColor(Color.PINK);
		g.fillRect(m_x, m_y, Options.TAILLE_CASE, Options.TAILLE_CASE);

	}

}
