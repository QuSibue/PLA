package ricm3.game.ath;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import ricm3.game.framework.GameATH;

public abstract class ATH extends GameATH {

	private static final long serialVersionUID = 1L;

	ATHP1 m_p1;
	ATHP2 m_p2;

	@Override
	public abstract Container init();

	public void ATHVisible() {
		m_p1 = new ATHP1();
		m_p2 = new ATHP2();

		Container c1 = m_p1.init();
		Container c2 = m_p2.init();

		Container c = new Container();
		c.setLayout(new FlowLayout());

		c.add(c1);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(20, 80));
		panel.setBackground(Color.GRAY);
		c.add(panel);

		c.add(c2);

		m_game.addSouth(c);
	}
}
