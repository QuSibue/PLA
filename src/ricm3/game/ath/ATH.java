package ricm3.game.ath;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JLabel;

import ricm3.game.framework.GameATH;
import ricm3.game.mvc.Model;

public class ATH extends GameATH {

	private static final long serialVersionUID = 1L;

	Model m_model;
	ATHPlayer m_p1;
	ATHPlayer m_p2;
	TimerATH m_timer;

	public ATH(Model model) {
		m_model = model;
		m_p1 = new ATHPlayer(m_model.virus, m_model.m_icb);
		m_p2 = new ATHPlayer(m_model.antivirus, m_model.m_icb);
		m_timer = new TimerATH();

	}

	public void ATHVisible() {
		Container c1 = m_p1.init();
		Container c2 = m_p2.init();

		Container c = new Container();
		c.setLayout(new FlowLayout());

		c.add(c1);

		c.add(Box.createRigidArea(new Dimension(40, 1)));
		JLabel time = m_timer.getTimer();
		c.add(time);
		c.add(Box.createRigidArea(new Dimension(40, 1)));

		c.add(c2);

		
		m_game.addSouth(c);
		c.getParent().setBackground(new Color(111, 140, 111));
	}

	public void step(long now) {
		m_p1.step(now);
		m_p2.step(now);
		m_timer.step(now);
	}

	public int getTimer() {
		return m_timer.getTime();
	}
}
