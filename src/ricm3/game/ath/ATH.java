package ricm3.game.ath;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import ricm3.game.framework.GameATH;
import ricm3.game.mvc.Model;

public class ATH extends GameATH {

	private static final long serialVersionUID = 1L;

	Model m_model; 
	ATHPlayer m_p1;
	ATHPlayer m_p2;

	public ATH(Model model){
		m_model = model;
		m_p1 = new ATHPlayer(m_model.virus);
		m_p2 = new ATHPlayer(m_model.antivirus);
	}

	public void ATHVisible() {
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
	
	public void step(long now) {
		m_p1.step(now);
		m_p2.step(now);
	}
}
