package ricm3.game.mvc;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class IconDataBase {
	public ImageIcon[] m_iconSbiresAntivirus;
	public ImageIcon[] m_iconSbiresVirus;
	public ImageIcon[] m_iconSbiresAntivirusSac;
	public ImageIcon[] m_iconSbiresVirusSac;
	public ImageIcon m_energieSac;
	public ImageIcon m_laserSac;
	public ImageIcon m_obstacleSac;
	public ImageIcon m_vieSac;

	public IconDataBase() {
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

		m_iconSbiresAntivirusSac = new ImageIcon[6];
		imageFile = new File("images/sac/ABloqueurSac.png");
		try {
			m_iconSbiresAntivirusSac[0] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/AFarmerSac.png");
		try {
			m_iconSbiresAntivirusSac[1] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/AKamikazeSac.png");
		try {
			m_iconSbiresAntivirusSac[2] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/APortailSac.png");
		try {
			m_iconSbiresAntivirusSac[3] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/ATankSac.png");
		try {
			m_iconSbiresAntivirusSac[4] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		m_iconSbiresVirusSac = new ImageIcon[6];
		imageFile = new File("images/sac/VBloqueurSac.png");
		try {
			m_iconSbiresVirusSac[0] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/VFarmerSac.png");
		try {
			m_iconSbiresVirusSac[1] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/VKamikazeSac.png");
		try {
			m_iconSbiresVirusSac[2] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/VPortailSac.png");
		try {
			m_iconSbiresVirusSac[3] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/VTankSac.png");
		try {
			m_iconSbiresVirusSac[4] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/tourelleSac.png");
		try {
			m_iconSbiresAntivirusSac[5] = new ImageIcon(ImageIO.read(imageFile));
			m_iconSbiresVirusSac[5] = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/energieSac.png");
		try {
			m_energieSac = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/laserSac.png");
		try {
			m_laserSac = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/obstacleSac.png");
		try {
			m_obstacleSac = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFile = new File("images/sac/vieSac.png");
		try {
			m_vieSac = new ImageIcon(ImageIO.read(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ImageIcon[] getIconSbiresAntivirus() {
		return m_iconSbiresAntivirus;
	}

	public ImageIcon[] getIconSbiresVirus() {
		return m_iconSbiresVirus;
	}
}
