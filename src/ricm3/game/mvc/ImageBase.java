package ricm3.game.mvc;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	// BufferedImage[] iconSbire;

	public ImageBase() {

		int nb_cols = 2;
		int nb_lines = 2;

		m_virus = new BufferedImage[nb_cols * nb_lines * 4];
		lecture_fichier(nb_cols, nb_lines, "images/virus/virus droite.png", m_virus, 0);
		lecture_fichier(nb_cols, nb_lines, "images/virus/virus devant.png", m_virus, 2);
		lecture_fichier(nb_cols, nb_lines, "images/virus/virus gauche.png", m_virus, 5);
		lecture_fichier(nb_cols, nb_lines, "images/virus/virus derriere.png", m_virus, 8);

		m_antivirus = new BufferedImage[nb_cols * nb_lines * 4];
		lecture_fichier(nb_cols, nb_lines, "images/antivirus/antivirus droite.png", m_antivirus, 0);
		lecture_fichier(nb_cols, nb_lines, "images/antivirus/antivirus devant.png", m_antivirus, 2);
		lecture_fichier(nb_cols, nb_lines, "images/antivirus/antivirus gauche.png", m_antivirus, 5);
		lecture_fichier(nb_cols, nb_lines, "images/antivirus/antivirus derriere.png", m_antivirus, 8);

		nb_cols = 2;
		nb_lines = 3;
		m_kamikaze = new BufferedImage[nb_cols * nb_lines];
		lecture_fichier(2, 3, "images/kamikaze/Kamikaze idle.png", m_kamikaze, 0);
	}

	public static void lecture_fichier(int nb_cols, int nb_lines, String s, BufferedImage[] sprites, int debut) {
		File imageFile = new File(s);
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		int width = sprite.getWidth(null);
		int height = sprite.getHeight(null);
		int m_w = width / nb_cols;
		int m_h = height / nb_lines;
		for (int i = 0; i < nb_lines; i++) {
			for (int j = 0; j < nb_cols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				sprites[(i * 2) + j + debut] = sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	public BufferedImage[] getKamikaze() {
		return m_kamikaze;
	}

	public BufferedImage resize(BufferedImage inputImage, int scaledWidth, int scaledHeight) throws IOException {
		// reads input image
		// File inputFile = new File(inputImagePath);
		// BufferedImage inputImage = ImageIO.read(inputFile);

		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();

		return outputImage;

		// extracts extension of output file
		// String formatName =
		// outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

		// writes to output file
		// ImageIO.write(outputImage, formatName, new File(outputImagePath));
	}
}
