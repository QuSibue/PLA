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

	private final int nbFrameVirus = 3;
	private final int nbFrameAntivirus = 3;
	private final int nbFrameLaser = 1;
	
	private final int nbFrameM1 = 6;
	private final int nbFrameM2 = 6;
	private final int nbFrameM3 = 6;
	private final int nbFrameM4 = 6;
	private final int nbFrameM5 = 6;
	private final int nbFrameM6 = 6;

	private final int nbFrameObstacle = 4;
	private final int nbFramePowerUp = 1;

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

	public void loadObstacleSprite() {
		loadOneRowOfSprite(obstacle, 0, nbFrameObstacle, "images/obstacle/Obstacle.png");
	}
	
	public void loadPowerUpSprite() {
		loadOneRowOfSprite(powerUp, 0, nbFramePowerUp, "images/energie/Energie.png");
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
