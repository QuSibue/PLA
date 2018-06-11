package ricm3.game.ath;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

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

public class ATHPlayer {

	Player m_virus;
	JLabel m_heart;
	JLabel m_heart1;
	JLabel m_heart2;
	JLabel m_labelx;
	JLabel m_labelz;
	JLabel m_labelw;

	public ATHPlayer(Player virus) {
		m_virus = virus;
		
		ImageIcon heartIcon = new ImageIcon("src/ricm3/sprites/heart.png");
		m_heart = new JLabel();
		m_heart.setIcon(heartIcon);
		m_heart1 = new JLabel();
		m_heart1.setIcon(heartIcon);
		m_heart2 = new JLabel();
		m_heart2.setIcon(heartIcon);
	}

	public Container init() {
		Container vie = new Container();
		vie.setLayout(new FlowLayout());

		vie.add(m_heart);
		vie.add(m_heart1);
		vie.add(m_heart2);

		JProgressBar energie = new JProgressBar(0, 10);
		energie.setValue(8);

		Container VieEnergie = new Container();
		VieEnergie.setLayout(new BoxLayout(VieEnergie, BoxLayout.Y_AXIS));
		VieEnergie.add(vie);
		VieEnergie.add(Box.createRigidArea(new Dimension(10, 10)));
		VieEnergie.add(energie);

		JLabel sbire = new JLabel();
		sbire.setIcon(new ImageIcon("src/ricm3/sprites/sbire.png"));

		Container cont = new Container();
		cont.setLayout(new FlowLayout());

		JPanel panelDuSbire = new JPanel();
		panelDuSbire.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		panelDuSbire.add(sbire);

		Container sacCont = new Container();
		sacCont.setLayout(new FlowLayout());

		JPanel panelx = new JPanel();
		panelx.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		panelx.setPreferredSize(new Dimension(40, 40));

		m_labelx = new JLabel("X");
		panelx.add(m_labelx);

		sacCont.add(panelx);

		JPanel panelz = new JPanel();
		panelz.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		panelz.setPreferredSize(new Dimension(40, 40));

		m_labelz = new JLabel("Z");
		panelz.add(m_labelz);

		sacCont.add(panelz);

		JPanel panelw = new JPanel();
		panelw.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		panelw.setPreferredSize(new Dimension(40, 40));

		m_labelw = new JLabel("W");
		panelw.add(m_labelw);

		sacCont.add(panelw);

		cont.add(VieEnergie);
		cont.add(Box.createRigidArea(new Dimension(20, 20)));
		cont.add(panelDuSbire);
		cont.add(Box.createRigidArea(new Dimension(20, 20)));
		cont.add(sacCont);

		return cont;
	}
	
	public void step(long now) {
		switch (m_virus.getLife()) {
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
		
		if (m_virus.getSac().getItem(0) == null)
			m_labelx.setVisible(false);
		else
			m_labelx.setVisible(true);
		if (m_virus.getSac().getItem(1) == null)
			m_labelz.setVisible(false);
		else
			m_labelz.setVisible(true);
		if (m_virus.getSac().getItem(2) == null)
			m_labelw.setVisible(false);
		else
			m_labelw.setVisible(true);
	}       
}
