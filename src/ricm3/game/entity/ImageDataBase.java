package ricm3.game.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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


	public ImageDataBase() {
		//On charge Virus de dos
		// L'animation du virus est en 3 frames
		loadOneRowOfSprite(virusIdle,0,3,"img/virus/idleUp.png");

		//On charge Virus regardant a droite
		// L'animation du virus est en 3 frames
		loadOneRowOfSprite(virusIdle,1,3,"img/virus/idleRight.png");

		//On charge Virus de face
		// L'animation du virus est en 3 frames
		loadOneRowOfSprite(virusIdle,2,3,"img/virus/idleDown.png");

		//On charge Virus regardant a gauche
		// L'animation du virus est en 3 frames
		loadOneRowOfSprite(virusIdle,3,3,"img/virus/idleLeft.png");
}

	public BufferedImage[] getVirusIdle(char direction){
		switch(direction) {
		case 'n':
			return virusIdle[0];
		case 'e':
			return virusIdle[1];
		case 's':
			return virusIdle[2];
		case 'o':
			return virusIdle[3];
		default:
			throw new RuntimeException("Orientation du sprite invalide.");
		}
		
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
		// Je considere que tous nos sprites vont etre decris sur une ligne et plsuieurs colonnes
		// si ca change il faut juste diviser non plus par 1 mais par le nombre de ligne de l'image
		// et rajouter une boucle iterant sur i un peu plus bas
		int m_h = height / 1;
		for (int j = 0; j < nbFrame; j++) {
			int x = j * m_w;
			int y = 0; //1 * m_h;
			images[index][j] = sprite.getSubimage(x, y, m_w, m_h);
		}
	}
}
