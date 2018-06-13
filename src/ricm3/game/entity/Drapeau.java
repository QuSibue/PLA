package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Options;

public class Drapeau extends Entity {
	private long m_lastMove;
	private int m_step;
	private int m_index = 0;

	public Drapeau(int x, int y, BufferedImage[][] sprites, int nbImage, ImageIcon image, Map map, Model model) {

		super(x, y, false, false, false, false, sprites, nbImage, image, map, model);

		m_lastMove = -100L;

		m_step = 7;
		/*
		 * int width = sprite.getWidth(null); int height = sprite.getHeight(null); int w
		 * = width / 3; int h = height / 3;
		 * 
		 * BufferedImage[] sprites = new BufferedImage[3 * 3];
		 * 
		 * for (int i = 0; i < 3; i++) { for (int j = 0; j < 3; j++) { int z = j * w;
		 * int t = i * h; sprites[(i * 3) + j] = sprite.getSubimage(z, t, w, h); } }
		 * 
		 * this.setSprites(sprites);
		 */

		// TODO Auto-generated constructor stub
	}

	public void step(long now) {
		long elapsed = now - m_lastMove;
		if (elapsed > 100L) {
			m_index++;
			m_index = m_index % m_step;
			m_lastMove = now;
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

		/*
		 * int m_x = this.getX() * Options.TAILLE_CASE; int m_y = this.getY() *
		 * Options.TAILLE_CASE; g.setColor(Color.BLACK); g.fillRect(m_x, m_y,
		 * Options.TAILLE_CASE, Options.TAILLE_CASE);
		 */

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
}