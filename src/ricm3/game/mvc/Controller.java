package ricm3.game.mvc;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import ricm3.game.mvc.Model;
import ricm3.game.other.TypeKey;
import ricm3.game.framework.GameController;

public class Controller extends GameController {
	Model m_model; // on possède le modele, on a donc accès au ressource du jeu (joueurs,minions,
				   // etc...)

	public Controller(Model m) {
		m_model = m;
	}

	@Override
	public void notifyVisible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int Code = e.getKeyCode();
		switch (Code) {
		case KeyEvent.VK_UP:
			// handle up
			m_model.virus.setKey(TypeKey.UP);
			System.out.println("UP");
			break;
		case KeyEvent.VK_DOWN:
			// handle down
			m_model.virus.setKey(TypeKey.DOWN);
			System.out.println("DOWN");
			break;
		case KeyEvent.VK_LEFT:
			// handle left
			m_model.virus.setKey(TypeKey.LEFT);
			System.out.println("LEFT");
			break;
		case KeyEvent.VK_RIGHT:
			// handle right
			m_model.virus.setKey(TypeKey.RIGHT);
			System.out.println("RIGHT");
			break;
		case KeyEvent.VK_H:
			m_model.virus.setKey(TypeKey.HIT);
			System.out.println("Hit");
			break;
		case KeyEvent.VK_P:
			m_model.virus.setKey(TypeKey.POP);
			System.out.println("Pop");
			break;
		case KeyEvent.VK_Z:
			m_model.antivirus.setKey(TypeKey.UP);
			System.out.println("UP");
			break;
		case KeyEvent.VK_Q:
			m_model.antivirus.setKey(TypeKey.LEFT);
			System.out.println("LEFT");
			break;
		case KeyEvent.VK_S:
			m_model.antivirus.setKey(TypeKey.DOWN);
			System.out.println("DOWN");
			break;
		case KeyEvent.VK_D:
			m_model.antivirus.setKey(TypeKey.RIGHT);
			System.out.println("RIGHT");
			break;
		case KeyEvent.VK_F:
			m_model.antivirus.setKey(TypeKey.HIT);
			System.out.println("Hit");
			break;
		case KeyEvent.VK_G:
			m_model.antivirus.setKey(TypeKey.POP);
			System.out.println("Pop");
			break;

		default:
			m_model.antivirus.setKey(TypeKey.NONE);
			m_model.virus.setKey(TypeKey.NONE);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int Code = e.getKeyCode();
		if (Code == KeyEvent.VK_UP || Code == KeyEvent.VK_DOWN || Code == KeyEvent.VK_LEFT || Code == KeyEvent.VK_RIGHT
				|| Code == KeyEvent.VK_H || Code == KeyEvent.VK_P) {
			m_model.virus.setKey(TypeKey.NONE);
		}
		if (Code == KeyEvent.VK_Z || Code == KeyEvent.VK_Q || Code == KeyEvent.VK_S || Code == KeyEvent.VK_D
				|| Code == KeyEvent.VK_F || Code == KeyEvent.VK_G) {
			m_model.antivirus.setKey(TypeKey.NONE);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}