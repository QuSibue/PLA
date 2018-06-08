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

public class ATHP2{
	
	Player m_antivirus;
	JLabel m_heart;
	JLabel m_heart1;
	JLabel m_heart2;
	
	public ATHP2(Player antivirus) {
		m_antivirus = antivirus;
		
		m_heart = new JLabel();
		m_heart.setIcon(new ImageIcon("src/ricm3/sprites/heart.png"));
		m_heart1 = new JLabel();
		m_heart1.setIcon(new ImageIcon("src/ricm3/sprites/heart.png"));
		m_heart2 = new JLabel();
		m_heart2.setIcon(new ImageIcon("src/ricm3/sprites/heart.png"));
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

		JLabel labelx = new JLabel("X");
		panelx.add(labelx);

		sacCont.add(panelx);

		JPanel panelz = new JPanel();
		panelz.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));

		JLabel labelz = new JLabel("Z");
		panelz.add(labelz);

		sacCont.add(panelz);

		JPanel panelw = new JPanel();
		panelw.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));

		JLabel labelw = new JLabel("W");
		panelw.add(labelw);

		sacCont.add(panelw);

		
		cont.add(sacCont);
		cont.add(Box.createRigidArea(new Dimension(50, 50)));
		cont.add(panelDuSbire);
		cont.add(Box.createRigidArea(new Dimension(50, 50)));
		cont.add(VieEnergie);
		
		return cont;
	}
	
	public void step(long now) {
		switch (m_antivirus.getLife()) {
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
	}
}
