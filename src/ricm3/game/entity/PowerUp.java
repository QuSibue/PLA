package ricm3.game.entity;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import ricm3.game.mvc.Model;
import ricm3.game.other.Options;

public class PowerUp extends Entity {
	private int m_type;

	public PowerUp(int x, int y, Model model, BufferedImage[][] sprites, ImageIcon icon, int nbImage, int type) {
		super(x, y, false, true, true, false, sprites, nbImage, icon, model.map, model);
		m_type = type;
	}

	@Override
	public void paint(Graphics g) {
		int i = getIndexRefresh();
		setIndexRefresh(i + 1);
		if (i == getNbImageRefresh()) {
			setIndexRefresh(0);
			int j = getNumImage();
			setNumImage(j + 1);
		}

		g.drawImage(this.getSprites()[0][getNumImage() % getNbImage()], this.getX() * Options.TAILLE_CASE,
				this.getY() * Options.TAILLE_CASE, Options.TAILLE_CASE, Options.TAILLE_CASE, null);

	}

	public void applyPowerUp(Being b) {
		if (this.m_type == 0 || b instanceof Minion) {
			b.setLife(b.getLife() + 1);
		} else if (this.m_type == 1 && !(b instanceof Minion)) {
			((Player) b).power(0);
		}
	}
}
