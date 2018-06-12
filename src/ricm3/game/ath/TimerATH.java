package ricm3.game.ath;

import java.awt.Font;

import javax.swing.JLabel;

import ricm3.game.other.Options;

public class TimerATH {
	public long m_lastMove;
	private static int m_time;
	JLabel m_timer;

	public TimerATH() {
		m_time = Options.TIMER;
		m_timer = new JLabel(Integer.toString(m_time));
		m_timer.setFont(new Font("Dialog", Font.BOLD, 30));
	}
	
	public JLabel getTimer() {
		return m_timer;
	}
	
	public static int getTime() {
		return m_time;
	}

	public void step(long now) {
		long elapsed = now - m_lastMove;
		
		if (elapsed > 1000L) {
			m_lastMove = now;
			m_time--;
			m_timer.setText(Integer.toString(m_time));
		}
	}

	
}
