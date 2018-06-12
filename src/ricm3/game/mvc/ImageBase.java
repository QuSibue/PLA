package ricm3.game.mvc;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageBase {
	BufferedImage[] m_virus;
	BufferedImage[] m_antivirus;
	BufferedImage[] m_tank;
	BufferedImage[] m_bloqueur;
	BufferedImage[] m_kamikaze;
	// BufferedImage[] m_tourelle;
	// BufferedImage[] m_farmer;
	// BufferedImage[] m_minion_portail;
	// BufferedImage[] m_drapeau;
	// BufferedImage[] m_obstacle;
	// BufferedImage[] m_laser;
	// BufferedImage[] m_explosion;
	// BufferedImage[] m_portail;
	ImageIcon[] m_iconSbires;

	public ImageBase() {
		File imageFile = new File("images/Kamikaze idle.png");
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		int width = sprite.getWidth(null);
		int height = sprite.getHeight(null);
		m_kamikaze = new BufferedImage[6];
		int m_w = width / 2;
		int m_h = height / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_kamikaze[(i * 2) + j] = sprite.getSubimage(x, y, m_w, m_h);
			}
		}
		
		
		m_iconSbires = new ImageIcon[2];
		imageFile = new File("images/sbire.png");
		try {
			m_iconSbires[0] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/evil_minion.png");
		try {
			m_iconSbires[1] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public BufferedImage[] getKamikaze() {
		return m_kamikaze;
	}
	
	public ImageIcon[] getIconSbires() {
		return m_iconSbires;
	}
}
