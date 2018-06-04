package ricm3.game.mvc;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import ricm3.game.mvc.Model;
import ricm3.game.framework.GameController;

public class Controller extends GameController {
	Model m_model; //on possède le modele, on a donc accès au ressource du jeu (joueurs,minions, etc...)
	
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
		switch( Code ) { 
        case KeyEvent.VK_UP:
            // handle up
        	m_model.joueur1.setMovingUp(true);
            break;
        case KeyEvent.VK_DOWN:
            // handle down
        	m_model.joueur1.setMovingDown(true);
            break;
        case KeyEvent.VK_LEFT:
            // handle left
        	m_model.joueur1.setMovingLeft(true);
            break;
        case KeyEvent.VK_RIGHT :
            // handle right
        	m_model.joueur1.setMovingRight(true);
            break;
     }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
