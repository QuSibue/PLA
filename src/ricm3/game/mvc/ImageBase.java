package ricm3.game.mvc;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageBase {
	ImageIcon[] m_iconSbiresAntivirus;
	ImageIcon[] m_iconSbiresVirus;

	public ImageBase() {
		m_iconSbiresAntivirus = new ImageIcon[6];
		File imageFile = new File("images/icones/ABloqueurIcon.png");
		try {
			m_iconSbiresAntivirus[0] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/icones/AFarmerIcon.png");
		try {
			m_iconSbiresAntivirus[1] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/icones/AKamikazeIcon.png");
		try {
			m_iconSbiresAntivirus[2] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/icones/APortailIcon.png");
		try {
			m_iconSbiresAntivirus[3] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/icones/ATankIcon.png");
		try {
			m_iconSbiresAntivirus[4] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/icones/tourelle.png");
		try {
			m_iconSbiresAntivirus[5] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		m_iconSbiresVirus = new ImageIcon[6];
		imageFile = new File("images/icones/VBloqueurIcon.png");
		try {
			m_iconSbiresVirus[0] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/icones/VFarmerIcon.png");
		try {
			m_iconSbiresVirus[1] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/icones/VKamikazeIcon.png");
		try {
			m_iconSbiresVirus[2] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/icones/VPortailIcon.png");
		try {
			m_iconSbiresVirus[3] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/icones/VTankIcon.png");
		try {
			m_iconSbiresVirus[4] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		imageFile = new File("images/icones/tourelle.png");
		try {
			m_iconSbiresVirus[5] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public ImageIcon[] getIconSbiresAntivirus() {
		return m_iconSbiresAntivirus;
	}
	
	public ImageIcon[] getIconSbiresVirus() {
		return m_iconSbiresAntivirus;
	}
}
