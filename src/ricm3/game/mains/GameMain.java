package ricm3.game.mains;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import ricm3.game.framework.GameUI;
import ricm3.game.menu.Menu;
import ricm3.game.mvc.Controller;
import ricm3.game.mvc.Model;
import ricm3.game.mvc.View;
import ricm3.game.other.Options;
import ricm3.game.parser.Ast;
import ricm3.game.parser.AutomataParser;;

public class GameMain {

	public static boolean gameOn;
	public static Menu m_menu;
	public static JFrame window1, window2, window3, window4, window5;
	public static String pathPlayer;
	public static Ast m_tree = null;
	public static boolean autLoaded = false;

	public static void main(String[] args) throws IOException {

		pathPlayer = Options.pathPlayer;
		// construct the game elements: model, controller, and view.
		// TODO définir les path par défaut
		if (!Options.NEW_GAME) {
			try {
				m_tree = AutomataParser.parserAutomates(GameMain.pathPlayer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ArrayList<String> names = m_tree.getNames();
		m_menu = new Menu(names);
		afficherMenu();

		return;
	}

	public static void afficherMenu() {

		window1 = new JFrame("Short Circuit");
		// Indique de sortir du programme lorsque la fenêtre est fermée par
		// l'utilisateur
		window1.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		// Définit la taille de la fenêtre en pixels
		window1.setSize(450, 250);

		window1.setResizable(false);
		window1.setLocationRelativeTo(null);
		window1.setVisible(true);

		window1.setLayout(new BorderLayout());
		window1.add(m_menu, BorderLayout.CENTER);

		if (!autLoaded) {
			for (int i = 0; i < Options.NB_ENTITY; i++) {
				m_menu.getIndices().add(i);
				// System.out.println(components.get(i).getSelectedIndex());
			}
		}

		if (window3 != null) {
			window3.setVisible(false);
		}

		if (window4 != null) {
			window4.dispose();
		}

	}

	public static void afficherRegles() throws IOException {

		if (window4 == null) {
			window4 = new JFrame("Short Circuit");
			window4.setLayout(new BorderLayout());

			// Indique de sortir du programme lorsque la fenêtre est fermée par
			// l'utilisateur
			window4.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

			// Définit la taille de la fenêtre en pixels
			window4.setSize(450, 250);

			window4.setResizable(false);
			window4.setLocationRelativeTo(null);
			window4.setVisible(true);

			// Composant de la frame

			JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
			window4.add(panelNorth, BorderLayout.NORTH);

			JPanel panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
			window4.add(panelCenter, BorderLayout.CENTER);

			JPanel panelCenterLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panelCenter.add(panelCenterLeft, BorderLayout.CENTER);

			JLabel message = new JLabel(
					"<html>Virus:<br/>-Mouvements: flèches<br/>-Hit:H<br/>-Pop:P / Wizz : W<br/>-Pick : O / Get : B<br/><br/>Antivirus:<br/>-Mouvements:ZQSD<br/>-Hit:F<br/>-Pop:F / Wizz:T<br/>-Pick : O / Get : B<br/></html>");
			panelCenter.add(message);

			JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
			window4.add(panelSouth, BorderLayout.SOUTH);

			BufferedImage title = ImageIO.read(new File("images/regles.png"));
			JLabel picLabel = new JLabel(new ImageIcon(title));
			panelNorth.add(picLabel);

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

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					afficherMenu();

				}
			});
		} else {
			window4.setVisible(true);
		}

		window1.dispose();

	}

	public static void afficherOptions(ArrayList<String> names) throws IOException {

		if (window3 != null) {
			window3.setVisible(true);

		} else {

			window3 = new JFrame("Short Circuit");
			window3.setLayout(new BorderLayout());

			// Indique de sortir du programme lorsque la fenêtre est fermée par
			// l'utilisateur
			window3.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

			// Définit la taille de la fenêtre en pixels
			window3.setSize(650, 400);

			window3.setResizable(false);
			window3.setLocationRelativeTo(null);
			window3.setVisible(true);

			// Composant de la frame
			String[] automaton = new String[names.size()];
			for (int i = 0; i < names.size(); i++) {
				automaton[i] = names.get(i);
			}

			ArrayList<JComboBox> components = new ArrayList<JComboBox>();

			JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
			window3.add(panelNorth, BorderLayout.NORTH);

			JPanel panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
			window3.add(panelCenter, BorderLayout.CENTER);

			JPanel boutonsCentre = new JPanel(new GridLayout(9, 2));
			panelCenter.add(boutonsCentre);

			JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
			window3.add(panelSouth, BorderLayout.SOUTH);

			BufferedImage title = ImageIO.read(new File("images/options.png"));
			JLabel picLabel = new JLabel(new ImageIcon(title));
			panelNorth.add(picLabel);

			JLabel autoPlayerText = new JLabel("Virus");
			boutonsCentre.add(autoPlayerText);

			JComboBox virus = new JComboBox(automaton);
			virus.setSelectedItem(automaton[0]);
			boutonsCentre.add(virus);
			components.add(virus);

			JLabel autoAntiText = new JLabel("Antivirus");
			boutonsCentre.add(autoAntiText);

			JComboBox antivirus = new JComboBox(automaton);
			antivirus.setSelectedItem(automaton[1]);
			boutonsCentre.add(antivirus);
			components.add(antivirus);

			JLabel autoLasersText = new JLabel("Lasers");
			boutonsCentre.add(autoLasersText);

			JComboBox laser = new JComboBox(automaton);
			laser.setSelectedItem(automaton[2]);
			boutonsCentre.add(laser);
			components.add(laser);

			

			

			JLabel autoBloqueurText = new JLabel("Bloqueur");
			boutonsCentre.add(autoBloqueurText);

			JComboBox bloqueur = new JComboBox(automaton);
			bloqueur.setSelectedItem(automaton[3]);
			boutonsCentre.add(bloqueur);
			components.add(bloqueur);
			
			JLabel autoRecolteurText = new JLabel("Récolteur");
			boutonsCentre.add(autoRecolteurText);

			JComboBox recolteur = new JComboBox(automaton);
			recolteur.setSelectedItem(automaton[4]);
			boutonsCentre.add(recolteur);
			components.add(recolteur);

			JLabel autoKamikazeText = new JLabel("Kamikaze");
			boutonsCentre.add(autoKamikazeText);

			JComboBox kamikaze = new JComboBox(automaton);
			kamikaze.setSelectedItem(automaton[5]);
			boutonsCentre.add(kamikaze);
			components.add(kamikaze);
			
			JLabel autoTeleporteurText = new JLabel("Téléporteur");
			boutonsCentre.add(autoTeleporteurText);

			JComboBox teleporteur = new JComboBox(automaton);
			teleporteur.setSelectedItem(automaton[6]);
			boutonsCentre.add(teleporteur);
			components.add(teleporteur);
			
			JLabel autoTankText = new JLabel("Tank");
			boutonsCentre.add(autoTankText);

			JComboBox tank = new JComboBox(automaton);
			tank.setSelectedItem(automaton[7]);
			boutonsCentre.add(tank);
			components.add(tank);

			JLabel autoTourelleText = new JLabel("Tourelle");
			boutonsCentre.add(autoTourelleText);

			JComboBox tourelle = new JComboBox(automaton);
			tourelle.setSelectedItem(automaton[8]);
			boutonsCentre.add(tourelle);
			components.add(tourelle);

			

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

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					m_menu.getIndices().clear();
					for (int i = 0; i < Options.NB_ENTITY; i++) {
						m_menu.getIndices().add(components.get(i).getSelectedIndex());
						
						// System.out.println(components.get(i).getSelectedIndex());
					}
					autLoaded = true;
					afficherMenu();
				}
			});

		}
		window1.dispose();

	}

	public static void afficherPartie(ArrayList<Integer> indices) {

		Model model = new Model(indices);
		View view = new View(model);
		Controller controller = new Controller(model);

		Dimension d = new Dimension(1920, 1080);
		Options.game = new GameUI(model, view, d, controller, model.m_ath);
		window1.dispose();

		return;
	}

	public static void afficherFinPartie(int fin, Model model) {
		String message = "";
		switch (fin) {
		case 1:
			message = "L'Antivirus à éliminé le virus et remporte la victoire !";
			break;
		case 2:
			message = "Le Virus à éliminé l'Antivurus et remporte la victoire !";
			break;
		case 3:
			message = "L'antivirus à défendu le système et remporte la victoire !";
			break;
		case 4:
			message="Le virus à capturer le drapeau, il remporte la victoire !";
			break;
		default:
			break;
		}

		window5 = new JFrame("Short Circuit");
		window5.setLayout(new BorderLayout());

		// Indique de sortir du programme lorsque la fenêtre est fermée par
		// l'utilisateur
		window5.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

		// Définit la taille de la fenêtre en pixels
		window5.setSize(450, 175);

		window5.setResizable(false);
		window5.setLocationRelativeTo(null);
		window5.setVisible(true);

		// Composant de la frame

		JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		window5.add(panelNorth, BorderLayout.NORTH);

		JPanel panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
		window5.add(panelCenter, BorderLayout.CENTER);

		JLabel finale = new JLabel(message);
		panelCenter.add(finale);

		JLabel question = new JLabel("Voulez-vous rejouer une partie ?");
		panelCenter.add(question);

		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		window5.add(panelSouth, BorderLayout.SOUTH);

		BufferedImage title;
		try {
			title = ImageIO.read(new File("images/gover.png"));
			JLabel picLabel = new JLabel(new ImageIcon(title));
			panelNorth.add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JButton rejouer = new JButton("Rejouer");
		rejouer.setFocusPainted(false);
		panelSouth.add(rejouer);

		JButton quitter = new JButton("Quitter");
		quitter.setFocusPainted(false);
		panelSouth.add(quitter);

		rejouer.addMouseListener(new MouseListener() {

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

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Options.NEW_GAME = true;
				model.getGameUI().getFrame().dispose();
				afficherPartie(m_menu.getIndices());
				window5.dispose();
			}
		});
		quitter.addMouseListener(new MouseListener() {

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

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

	}
}
