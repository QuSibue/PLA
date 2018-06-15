package ricm3.game.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageDataBase {

	public BufferedImage[][] virusIdle;
	public BufferedImage[][] antivirusIdle;
	public BufferedImage[][] laserIdle;

	public BufferedImage[][] m1IdleA;
	public BufferedImage[][] m2IdleA;
	public BufferedImage[][] m3IdleA;
	public BufferedImage[][] m4IdleA;
	public BufferedImage[][] m5IdleA;
	public BufferedImage[][] m6IdleA;
	public BufferedImage[][] m1IdleV;
	public BufferedImage[][] m2IdleV;
	public BufferedImage[][] m3IdleV;
	public BufferedImage[][] m4IdleV;
	public BufferedImage[][] m5IdleV;
	public BufferedImage[][] m6IdleV;

	public BufferedImage[][] obstacle;
	public BufferedImage[][] powerUpE;
	public BufferedImage[][] powerUpV;
	public BufferedImage[][] portail;
	public BufferedImage[][] drapeau;

	public BufferedImage[][] explosion;

	public BufferedImage map;

	public final int nbFrameVirus = 3;
	public final int nbFrameAntivirus = 3;
	public final int nbFrameLaser = 6;

	public final int nbFrameM1 = 6;
	public final int nbFrameM2 = 6;
	public final int nbFrameM3 = 6;
	public final int nbFrameM4 = 6;
	public final int nbFrameM5 = 6;
	public final int nbFrameM6 = 6;

	public final int nbFrameObstacle = 4;
	public final int nbFramePowerUp = 8;
	public final int nbFramePortail = 5;
	public final int nbFrameDrapeau = 7;

	public final int nbFrameExplosion = 9;

	public ImageDataBase() {
		// On initialise les tableau
		virusIdle = new BufferedImage[4][];
		antivirusIdle = new BufferedImage[4][];
		laserIdle = new BufferedImage[4][];

		m1IdleA = new BufferedImage[4][];
		m2IdleA = new BufferedImage[4][];
		m3IdleA = new BufferedImage[4][];
		m4IdleA = new BufferedImage[4][];
		m5IdleA = new BufferedImage[4][];
		m6IdleA = new BufferedImage[4][];
		m1IdleV = new BufferedImage[4][];
		m2IdleV = new BufferedImage[4][];
		m3IdleV = new BufferedImage[4][];
		m4IdleV = new BufferedImage[4][];
		m5IdleV = new BufferedImage[4][];
		m6IdleV = new BufferedImage[4][];

		obstacle = new BufferedImage[1][];
		powerUpE = new BufferedImage[1][];
		powerUpV = new BufferedImage[1][];
		portail = new BufferedImage[1][];
		drapeau = new BufferedImage[1][];
		
		explosion = new BufferedImage[1][];

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
		loadPortailSprite();
		loadDrapeauSprite();
		loadExplosionSprite();

		loadMapSprite();

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
		loadOneRowOfSprite(m1IdleA, 0, nbFrameM1, "images/m1A/IdleUp.png");
		loadOneRowOfSprite(m1IdleA, 1, nbFrameM1, "images/m1A/IdleRight.png");
		loadOneRowOfSprite(m1IdleA, 2, nbFrameM1, "images/m1A/IdleDown.png");
		loadOneRowOfSprite(m1IdleA, 3, nbFrameM1, "images/m1A/IdleLeft.png");
		loadOneRowOfSprite(m1IdleV, 0, nbFrameM1, "images/m1V/IdleUp.png");
		loadOneRowOfSprite(m1IdleV, 1, nbFrameM1, "images/m1V/IdleRight.png");
		loadOneRowOfSprite(m1IdleV, 2, nbFrameM1, "images/m1V/IdleDown.png");
		loadOneRowOfSprite(m1IdleV, 3, nbFrameM1, "images/m1V/IdleLeft.png");
	}

	public void loadM2Sprite() {
		loadOneRowOfSprite(m2IdleA, 0, nbFrameM2, "images/m2A/IdleUp.png");
		loadOneRowOfSprite(m2IdleA, 1, nbFrameM2, "images/m2A/IdleRight.png");
		loadOneRowOfSprite(m2IdleA, 2, nbFrameM2, "images/m2A/IdleDown.png");
		loadOneRowOfSprite(m2IdleA, 3, nbFrameM2, "images/m2A/IdleLeft.png");
		loadOneRowOfSprite(m2IdleV, 0, nbFrameM2, "images/m2V/IdleUp.png");
		loadOneRowOfSprite(m2IdleV, 1, nbFrameM2, "images/m2V/IdleRight.png");
		loadOneRowOfSprite(m2IdleV, 2, nbFrameM2, "images/m2V/IdleDown.png");
		loadOneRowOfSprite(m2IdleV, 3, nbFrameM2, "images/m2V/IdleLeft.png");
	}

	public void loadM3Sprite() {
		loadOneRowOfSprite(m3IdleA, 0, nbFrameM3, "images/m3A/IdleUp.png");
		loadOneRowOfSprite(m3IdleA, 1, nbFrameM3, "images/m3A/IdleRight.png");
		loadOneRowOfSprite(m3IdleA, 2, nbFrameM3, "images/m3A/IdleDown.png");
		loadOneRowOfSprite(m3IdleA, 3, nbFrameM3, "images/m3A/IdleLeft.png");
		loadOneRowOfSprite(m3IdleV, 0, nbFrameM3, "images/m3V/IdleUp.png");
		loadOneRowOfSprite(m3IdleV, 1, nbFrameM3, "images/m3V/IdleRight.png");
		loadOneRowOfSprite(m3IdleV, 2, nbFrameM3, "images/m3V/IdleDown.png");
		loadOneRowOfSprite(m3IdleV, 3, nbFrameM3, "images/m3V/IdleLeft.png");
	}

	public void loadM4Sprite() {
		loadOneRowOfSprite(m4IdleA, 0, nbFrameM4, "images/m4A/IdleUp.png");
		loadOneRowOfSprite(m4IdleA, 1, nbFrameM4, "images/m4A/IdleRight.png");
		loadOneRowOfSprite(m4IdleA, 2, nbFrameM4, "images/m4A/IdleDown.png");
		loadOneRowOfSprite(m4IdleA, 3, nbFrameM4, "images/m4A/IdleLeft.png");
		loadOneRowOfSprite(m4IdleV, 0, nbFrameM4, "images/m4V/IdleUp.png");
		loadOneRowOfSprite(m4IdleV, 1, nbFrameM4, "images/m4V/IdleRight.png");
		loadOneRowOfSprite(m4IdleV, 2, nbFrameM4, "images/m4V/IdleDown.png");
		loadOneRowOfSprite(m4IdleV, 3, nbFrameM4, "images/m4V/IdleLeft.png");
	}

	public void loadM5Sprite() {
		loadOneRowOfSprite(m5IdleA, 0, nbFrameM5, "images/m5A/IdleUp.png");
		loadOneRowOfSprite(m5IdleA, 1, nbFrameM5, "images/m5A/IdleRight.png");
		loadOneRowOfSprite(m5IdleA, 2, nbFrameM5, "images/m5A/IdleDown.png");
		loadOneRowOfSprite(m5IdleA, 3, nbFrameM5, "images/m5A/IdleLeft.png");
		loadOneRowOfSprite(m5IdleV, 0, nbFrameM5, "images/m5V/IdleUp.png");
		loadOneRowOfSprite(m5IdleV, 1, nbFrameM5, "images/m5V/IdleRight.png");
		loadOneRowOfSprite(m5IdleV, 2, nbFrameM5, "images/m5V/IdleDown.png");
		loadOneRowOfSprite(m5IdleV, 3, nbFrameM5, "images/m5V/IdleLeft.png");
	}

	public void loadM6Sprite() {
		loadOneRowOfSprite(m6IdleA, 0, nbFrameM6, "images/m6A/IdleUp.png");
		loadOneRowOfSprite(m6IdleA, 1, nbFrameM6, "images/m6A/IdleRight.png");
		loadOneRowOfSprite(m6IdleA, 2, nbFrameM6, "images/m6A/IdleDown.png");
		loadOneRowOfSprite(m6IdleA, 3, nbFrameM6, "images/m6A/IdleLeft.png");
		loadOneRowOfSprite(m6IdleV, 0, nbFrameM6, "images/m6V/IdleUp.png");
		loadOneRowOfSprite(m6IdleV, 1, nbFrameM6, "images/m6V/IdleRight.png");
		loadOneRowOfSprite(m6IdleV, 2, nbFrameM6, "images/m6V/IdleDown.png");
		loadOneRowOfSprite(m6IdleV, 3, nbFrameM6, "images/m6V/IdleLeft.png");
	}

	public void loadObstacleSprite() {
		loadOneRowOfSprite(obstacle, 0, nbFrameObstacle, "images/obstacle/Obstacle.png");
	}

	public void loadPowerUpSprite() {
		loadOneRowOfSprite(powerUpE, 0, nbFramePowerUp, "images/energie/Energie.png");
		loadOneRowOfSprite(powerUpV, 0, nbFramePowerUp, "images/Vie/Vie.png");
	}

	public void loadPortailSprite() {
		loadOneRowOfSprite(portail, 0, nbFramePortail, "images/Portail/Portail.png");
	}

	public void loadDrapeauSprite() {
		loadOneRowOfSprite(drapeau, 0, nbFrameDrapeau, "images/Flag/Flag.png");
	}
	
	public void loadExplosionSprite() {
		loadOneRowOfSprite(explosion, 0, nbFrameExplosion, "images/explosion/Explosion.png");
	}

	public void loadMapSprite() {
		BufferedImage[][] tmp = new BufferedImage[1][];
		loadOneRowOfSprite(tmp, 0, 1, "images/map/1/map.png");
		map = tmp[0][0];
	}

	public BufferedImage getMapSprite() {
		return map;
	}

	public BufferedImage[][] getMinionSpritesA(int i) {
		switch (i) {
		case 0:
			return m1IdleA;
		case 1:
			return m2IdleA;
		case 2:
			return m3IdleA;
		case 3:
			return m4IdleA;
		case 4:
			return m5IdleA;
		case 5:
			return m6IdleA;
		default:
			throw new RuntimeException("Indice sprite des sbires invalide");
		}
	}

	public BufferedImage[][] getMinionSpritesV(int i) {
		switch (i) {
		case 0:
			return m1IdleV;
		case 1:
			return m2IdleV;
		case 2:
			return m3IdleV;
		case 3:
			return m4IdleV;
		case 4:
			return m5IdleV;
		case 5:
			return m6IdleV;
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
