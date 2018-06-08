package ricm3.game.mains;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import ricm3.game.framework.GameUI;
import ricm3.game.mvc.Controller;
import ricm3.game.mvc.Model;
import ricm3.game.mvc.View;
import ricm3.game.menu.Menu;;

public class GameMain {
	
	public static boolean gameOn;
	public static Menu m_menu;
	public static JFrame window1,window2;
	
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
        
    }
	
	
	public static void afficherPartie() {
        
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model);

		Dimension d = new Dimension(1024, 768);
		new GameUI(model, view, d, controller);
        
    }
}
