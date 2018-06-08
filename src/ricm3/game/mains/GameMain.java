package ricm3.game.mains;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ricm3.game.framework.GameUI;
import ricm3.game.mvc.Controller;
import ricm3.game.mvc.Model;
import ricm3.game.mvc.View;
import ricm3.game.menu.Menu;;

public class GameMain {
	
	public static boolean gameOn;
	public static Menu m_menu;
	public static JFrame window1,window2,window3;
	
	public static void main(String[] args) throws IOException {

		// construct the game elements: model, controller, and view.
		m_menu = new Menu();
		afficherMenu();
	
		return;
	}
	
	
	
	public static void afficherMenu() {
        
        window1 = new JFrame("Short Circuit");
        // Indique de sortir du programme lorsque la fenêtre est fermée par l'utilisateur
        window1.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window1.setSize(450, 250);
       
        window1.setResizable(false);
        window1.setLocationRelativeTo(null);
        window1.setVisible(true);
        
        window1.setLayout(new BorderLayout());
        window1.add(m_menu,BorderLayout.CENTER);
        
        if(window3 != null) {
        	window3.dispose();
        }
        
    }
	
	public static void afficherOptions() {
        
		
		window3 = new JFrame("Short Circuit");
		window3.setLayout(new BorderLayout());
		
        // Indique de sortir du programme lorsque la fenêtre est fermée par l'utilisateur
        window3.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
        // Définit la taille de la fenêtre en pixels
        window3.setSize(450, 250);
      
        window3.setResizable(false);
        window3.setLocationRelativeTo(null);
        window3.setVisible(true);
                
        //Composant de la frame    
        
        JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));        
        window3.add(panelNorth,BorderLayout.NORTH);
        
        
        JPanel panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));        
        window3.add(panelCenter,BorderLayout.CENTER);
        
        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));        
        window3.add(panelSouth,BorderLayout.SOUTH);
        
        JButton retour = new JButton("Retour");
        retour.setFocusPainted(false);
        panelSouth.add(retour);
        
        retour.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				((JButton) e.getSource()).setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				((JButton) e.getSource()).setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				afficherMenu();
			}
		});
        
        window1.dispose();
 
    }
	
	public static void afficherPartie() {
        
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model);

		Dimension d = new Dimension(1024, 768);
		new GameUI(model, view, d, controller);
		window1.dispose();
        
    }
}
