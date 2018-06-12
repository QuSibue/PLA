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
	}

	public BufferedImage[] getKamikaze() {
		return m_kamikaze;
	}

	public BufferedImage resize(BufferedImage inputImage, int scaledWidth, int scaledHeight)
			throws IOException {
		// reads input image
//		File inputFile = new File(inputImagePath);
//		BufferedImage inputImage = ImageIO.read(inputFile);

		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();
		
		return outputImage;

		// extracts extension of output file
//		String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

		// writes to output file
//		ImageIO.write(outputImage, formatName, new File(outputImagePath));
	}
}
