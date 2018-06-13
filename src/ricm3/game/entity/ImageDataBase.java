package ricm3.game.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageDataBase {

	public BufferedImage[][] virusIdle;
	public BufferedImage[][] antivirusIdle;
	public BufferedImage[][] laserIdle;

	public BufferedImage[][] m1Idle;
	public BufferedImage[][] m2Idle;
	public BufferedImage[][] m3Idle;
	public BufferedImage[][] m4Idle;
	public BufferedImage[][] m5Idle;
	public BufferedImage[][] m6Idle;

	public BufferedImage[][] obstacle;
	public BufferedImage[][] powerUp;

	public final int nbFrameVirus = 3;
	public final int nbFrameAntivirus = 3;
	public final int nbFrameLaser = 1;

	public final int nbFrameM1 = 6;
	public final int nbFrameM2 = 6;
	public final int nbFrameM3 = 6;
	public final int nbFrameM4 = 6;
	public final int nbFrameM5 = 6;
	public final int nbFrameM6 = 6;

	public final int nbFrameObstacle = 4;
	public final int nbFramePowerUp = 8;

	public ImageDataBase() {
		// On initialise les tableau
		virusIdle = new BufferedImage[4][];
		antivirusIdle = new BufferedImage[4][];
		laserIdle = new BufferedImage[4][];

		m1Idle = new BufferedImage[4][];
		m2Idle = new BufferedImage[4][];
		m3Idle = new BufferedImage[4][];
		m4Idle = new BufferedImage[4][];
		m5Idle = new BufferedImage[4][];
		m6Idle = new BufferedImage[4][];

		obstacle = new BufferedImage[1][];
		powerUp = new BufferedImage[1][];

		loadVirusSprite();
		loadAntivirusSprite();
		loadLaserSprite();
		
		loadM1Sprite();
		loadM2Sprite();
		loadM3Sprite();
		loadM4Sprite();
		loadM5Sprite();
		loadM6Sprite();
		

		loadObstacleSprite();
		loadPowerUpSprite();

	}

	public void loadAntivirusSprite() {
		// On charge antiVirus de dos
		// L'animation du antivirus est en 3 frames
		loadOneRowOfSprite(antivirusIdle, 0, nbFrameAntivirus, "images/antivirus/IdleUp.png");

		// On charge antiVirus regardant a droite
		// L'animation du antivirus est en 3 frames
		loadOneRowOfSprite(antivirusIdle, 1, nbFrameAntivirus, "images/antivirus/IdleRight.png");

		// On charge antiVirus de face
		// L'animation du antivirus est en 3 frames
		loadOneRowOfSprite(antivirusIdle, 2, nbFrameAntivirus, "images/antivirus/IdleDown.png");

		// On charge antiVirus regardant a gauche
		// L'animation du antivirus est en 3 frames
		loadOneRowOfSprite(antivirusIdle, 3, nbFrameAntivirus, "images/antivirus/IdleLeft.png");
	}

	public void loadVirusSprite() {
		loadOneRowOfSprite(virusIdle, 0, nbFrameVirus, "images/virus/IdleUp.png");
		loadOneRowOfSprite(virusIdle, 1, nbFrameVirus, "images/virus/IdleRight.png");
		loadOneRowOfSprite(virusIdle, 2, nbFrameVirus, "images/virus/IdleDown.png");
		loadOneRowOfSprite(virusIdle, 3, nbFrameVirus, "images/virus/IdleLeft.png");
	}

	public void loadLaserSprite() {
		loadOneRowOfSprite(laserIdle, 0, nbFrameLaser, "images/laser/Laser.png");
		loadOneRowOfSprite(laserIdle, 1, nbFrameLaser, "images/laser/Laser.png");
		loadOneRowOfSprite(laserIdle, 2, nbFrameLaser, "images/laser/Laser.png");
		loadOneRowOfSprite(laserIdle, 3, nbFrameLaser, "images/laser/Laser.png");
	}

	public void loadM1Sprite() {
		loadOneRowOfSprite(m1Idle, 0, nbFrameM1, "images/m1/IdleUp.png");
		loadOneRowOfSprite(m1Idle, 1, nbFrameM1, "images/m1/IdleRight.png");
		loadOneRowOfSprite(m1Idle, 2, nbFrameM1, "images/m1/IdleDown.png");
		loadOneRowOfSprite(m1Idle, 3, nbFrameM1, "images/m1/IdleLeft.png");
	}
	
	public void loadM2Sprite() {
		loadOneRowOfSprite(m2Idle, 0, nbFrameM2, "images/m2/IdleUp.png");
		loadOneRowOfSprite(m2Idle, 1, nbFrameM2, "images/m2/IdleRight.png");
		loadOneRowOfSprite(m2Idle, 2, nbFrameM2, "images/m2/IdleDown.png");
		loadOneRowOfSprite(m2Idle, 3, nbFrameM2, "images/m2/IdleLeft.png");
	}

	public void loadM3Sprite() {
		loadOneRowOfSprite(m3Idle, 0, nbFrameM3, "images/m3/IdleUp.png");
		loadOneRowOfSprite(m3Idle, 1, nbFrameM3, "images/m3/IdleRight.png");
		loadOneRowOfSprite(m3Idle, 2, nbFrameM3, "images/m3/IdleDown.png");
		loadOneRowOfSprite(m3Idle, 3, nbFrameM3, "images/m3/IdleLeft.png");
	}

	public void loadM4Sprite() {
		loadOneRowOfSprite(m4Idle, 0, nbFrameM4, "images/m4/IdleUp.png");
		loadOneRowOfSprite(m4Idle, 1, nbFrameM4, "images/m4/IdleRight.png");
		loadOneRowOfSprite(m4Idle, 2, nbFrameM4, "images/m4/IdleDown.png");
		loadOneRowOfSprite(m4Idle, 3, nbFrameM4, "images/m4/IdleLeft.png");
	}

	public void loadM5Sprite() {
		loadOneRowOfSprite(m5Idle, 0, nbFrameM5, "images/m5/IdleUp.png");
		loadOneRowOfSprite(m5Idle, 1, nbFrameM5, "images/m5/IdleRight.png");
		loadOneRowOfSprite(m5Idle, 2, nbFrameM5, "images/m5/IdleDown.png");
		loadOneRowOfSprite(m5Idle, 3, nbFrameM5, "images/m5/IdleLeft.png");
	}

	public void loadM6Sprite() {
		loadOneRowOfSprite(m6Idle, 0, nbFrameM6, "images/m6/IdleUp.png");
		loadOneRowOfSprite(m6Idle, 1, nbFrameM6, "images/m6/IdleRight.png");
		loadOneRowOfSprite(m6Idle, 2, nbFrameM6, "images/m6/IdleDown.png");
		loadOneRowOfSprite(m6Idle, 3, nbFrameM6, "images/m6/IdleLeft.png");
	}
	

	public void loadObstacleSprite() {
		loadOneRowOfSprite(obstacle, 0, nbFrameObstacle, "images/obstacle/Obstacle.png");
	}

	public void loadPowerUpSprite() {
		loadOneRowOfSprite(powerUp, 0, nbFramePowerUp, "images/energie/Energie.png");
	}
	
	public BufferedImage[][] getMinionSprites(int i){
		switch(i) {
		case 0:
			return m1Idle;
		case 1:
			return m2Idle;
		case 2:
			return m3Idle;
		case 3:
			return m4Idle;
		case 4:
			return m5Idle;
		case 5:
			return m6Idle;
		default:
			throw new RuntimeException("Indice sprite des sbires invalide");
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

}
