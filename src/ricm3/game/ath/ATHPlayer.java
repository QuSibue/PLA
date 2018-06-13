package ricm3.game.ath;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

import org.w3c.dom.ranges.RangeException;

import ricm3.game.entity.Player;
import ricm3.game.mvc.ImageBase;
import ricm3.game.other.Options;

/**
 * Classe qui construit l'ATH d'un player (virus ou antivirus).
 */
public class ATHPlayer {

	Player m_player; // virus ou antivirus
	JLabel m_heart;
	JLabel m_heart1;
	JLabel m_heart2; // il a 3 vies
	JLabel m_label1;
	JLabel m_label2;
	JLabel m_label3; // il peut avoir jusqu'a 3 choses dans son sac
	
	JLabel m_sbire;
	
	JProgressBar energie;
	ImageIcon[] m_iconSbire;
	ImageBase m_ib;

	public ATHPlayer(Player player, ImageBase ib) {
		m_player = player;
		m_ib = ib;

		ImageIcon heartIcon = new ImageIcon("images/heart.png");
		m_heart = new JLabel();
		m_heart.setIcon(heartIcon);
		m_heart1 = new JLabel();
		m_heart1.setIcon(heartIcon);
		m_heart2 = new JLabel();
		m_heart2.setIcon(heartIcon);
		
		energie = new JProgressBar(0, Options.initialEnergie);
		energie.setString(Integer.toString(Options.initialEnergie));
		energie.setStringPainted(true);
		energie.setValue(Options.initialEnergie);
		
		m_iconSbire = m_ib.getIconSbiresAntivirus();
		m_sbire = new JLabel();
		m_sbire.setIcon(m_iconSbire[0]);
	}

	public Container init() {
		Container vie = new Container();
		vie.setLayout(new FlowLayout());

		vie.add(m_heart);
		vie.add(m_heart1);
		vie.add(m_heart2);

//		JProgressBar energie = new JProgressBar(0, Options.initialEnergie);
//		energie.setString(Integer.toString(Options.initialEnergie));
//		energie.setStringPainted(true);
//		energie.setValue(Options.initialEnergie);

		Container VieEnergie = new Container();
		VieEnergie.setLayout(new BoxLayout(VieEnergie, BoxLayout.Y_AXIS));
		VieEnergie.add(vie);
		VieEnergie.add(Box.createRigidArea(new Dimension(1, 10)));
		VieEnergie.add(energie);

		Container cont = new Container();
		cont.setLayout(new FlowLayout());

		JPanel panelDuSbire = new JPanel();
		panelDuSbire.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		panelDuSbire.add(m_sbire);

		Container sacCont = new Container();
		sacCont.setLayout(new FlowLayout());

		JPanel panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		panel1.setPreferredSize(new Dimension(40, 40));

		m_label1 = new JLabel("X");
		panel1.add(m_label1);

		sacCont.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		panel2.setPreferredSize(new Dimension(40, 40));

		m_label2 = new JLabel("Z");
		panel2.add(m_label2);

		sacCont.add(panel2);

		JPanel panel3 = new JPanel();
		panel3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		panel3.setPreferredSize(new Dimension(40, 40));

		m_label3 = new JLabel("W");
		panel3.add(m_label3);

		sacCont.add(panel3);

		cont.add(VieEnergie);
		cont.add(Box.createRigidArea(new Dimension(20, 1)));
		cont.add(panelDuSbire);
		cont.add(Box.createRigidArea(new Dimension(20, 1)));
		cont.add(sacCont);

		return cont;
	}

	public void step(long now) {
		switch (m_player.getLife()) {
		case 3:
			break;
		case 2:
			m_heart2.setVisible(false);
			break;
		case 1:
			m_heart1.setVisible(false);
			break;
		case 0:
			m_heart.setVisible(false);
			break;
		default:
			throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Error vie");
		}

		if (m_player.getSac().getItem(0) == null)
			m_label1.setVisible(false);
		else
			m_label1.setVisible(true);
		if (m_player.getSac().getItem(1) == null)
			m_label2.setVisible(false);
		else
			m_label2.setVisible(true);
		if (m_player.getSac().getItem(2) == null)
			m_label3.setVisible(false);
		else
			m_label3.setVisible(true);
		
		energie.setValue(m_player.getEnergie());
		energie.setString(Integer.toString(m_player.getEnergie()));
		
		m_sbire.setIcon(m_iconSbire[m_player.getIndiceMinion()]);
	}
}
