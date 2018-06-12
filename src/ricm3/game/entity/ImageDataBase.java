package ricm3.game.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ricm3.game.automaton.Orientation;

public class ImageDataBase {

	BufferedImage[][] virusIdle;
	BufferedImage[][] antivirusIdle;
	BufferedImage[][] laserIdle;
	BufferedImage[][] m1Idle;
	BufferedImage[][] m2Idle;
	BufferedImage[][] m3Idle;
	BufferedImage[][] m4Idle;
	BufferedImage[][] m5Idle;
	BufferedImage[][] m6Idle;
	final int nbFrameVirus = 3;

	public ImageDataBase() {
		//On initialise Virus
		antivirusIdle = new BufferedImage[4][];
		
		// On charge Virus de dos
		// L'animation du virus est en 3 frames
		loadOneRowOfSprite(antivirusIdle, 0, nbFrameVirus, "images/antivirus/idleUp.png");

		// On charge Virus regardant a droite
		// L'animation du virus est en 3 frames
		loadOneRowOfSprite(antivirusIdle, 1, 3, "images/antivirus/idleRight.png");

		// On charge Virus de face
		// L'animation du virus est en 3 frames
		loadOneRowOfSprite(antivirusIdle, 2, 3, "images/antivirus/idleDown.png");

		// On charge Virus regardant a gauche
		// L'animation du virus est en 3 frames
		loadOneRowOfSprite(antivirusIdle, 3, 3, "images/antivirus/idleLeft.png");
	}

	private void loadOneRowOfSprite(BufferedImage[][] images, int index, int nbFrame, String path) {
		File imageFile = new File(path);
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		int width = sprite.getWidth(null);
		int height = sprite.getHeight(null);
		images[index] = new BufferedImage[nbFrame];
		int m_w = width / nbFrame;
		// Je considere que tous nos sprites vont etre decris sur une ligne et plsuieurs
		// colonnes
		// si ca change il faut juste diviser non plus par 1 mais par le nombre de ligne
		// de l'image
		// et rajouter une boucle iterant sur i un peu plus bas
		int m_h = height / 1;
		for (int j = 0; j < nbFrame; j++) {
			int x = j * m_w;
			int y = 0; // 1 * m_h;
			images[index][j] = sprite.getSubimage(x, y, m_w, m_h);
		}
	}

	public BufferedImage[] getAntivirusIdle(Orientation direction) {
		switch (direction) {
		case UP:
			return antivirusIdle[0];
		case RIGHT:
			return antivirusIdle[1];
		case DOWN:
			return antivirusIdle[2];
		case LEFT:
			return antivirusIdle[3];
		default:
			throw new RuntimeException("Orientation du sprite invalide.");
		}

	}
}
