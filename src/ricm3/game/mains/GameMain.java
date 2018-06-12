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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import ricm3.game.other.Options;;

public class GameMain {

	public static boolean gameOn;
	public static Menu m_menu;
	public static JFrame window1, window2, window3, window4, window5;
	public static String pathPlayer, pathMinions, pathLaser;

	public static void main(String[] args) throws IOException {

		pathPlayer = Options.pathPlayer;
		// construct the game elements: model, controller, and view.
		// TODO définir les path par défaut
		m_menu = new Menu();
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

		if (window3 != null) {
			window3.setVisible(false);
		}

	}

	public static void afficherRegles() throws IOException {

		window3 = new JFrame("Short Circuit");
		window3.setLayout(new BorderLayout());

		// Indique de sortir du programme lorsque la fenêtre est fermée par
		// l'utilisateur
		window3.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

		// Définit la taille de la fenêtre en pixels
		window3.setSize(450, 250);

		window3.setResizable(false);
		window3.setLocationRelativeTo(null);
		window3.setVisible(true);

		// Composant de la frame

		JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		window3.add(panelNorth, BorderLayout.NORTH);

		JPanel panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
		window3.add(panelCenter, BorderLayout.CENTER);
		
		JPanel panelCenterLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelCenter.add(panelCenterLeft, BorderLayout.CENTER);

		JLabel message = new JLabel("<html>Virus:<br/>-Mouvements: flèches<br/>-Hit:H<br/>-Pop:P / Wizz : W<br/>-Pick : O / Get : B<br/><br/>Antivirus:<br/>-Mouvements:ZQSD<br/>-Hit:F<br/>-Pop:F / Wizz:T<br/>-Pick : O / Get : B<br/></html>");
		panelCenter.add(message);

		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		window3.add(panelSouth, BorderLayout.SOUTH);

		BufferedImage title = ImageIO.read(new File("images/regles.png"));
		JLabel picLabel = new JLabel(new ImageIcon(title));
		panelNorth.add(picLabel);

		JButton retour = new JButton("Retour");
		retour.setFocusPainted(false);
		panelSouth.add(retour);
		window1.dispose();
		
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

	}

	public static void afficherOptions() throws IOException {

		if (window3 != null) {
			window3.setVisible(true);

		} else {

			window3 = new JFrame("Short Circuit");
			window3.setLayout(new BorderLayout());

			// Indique de sortir du programme lorsque la fenêtre est fermée par
			// l'utilisateur
			window3.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

			// Définit la taille de la fenêtre en pixels
			window3.setSize(450, 250);

			window3.setResizable(false);
			window3.setLocationRelativeTo(null);
			window3.setVisible(true);

			// Composant de la frame

			JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
			window3.add(panelNorth, BorderLayout.NORTH);

			JPanel panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
			window3.add(panelCenter, BorderLayout.CENTER);

			JPanel boutonsCentre = new JPanel(new GridLayout(3, 2));
			panelCenter.add(boutonsCentre);

			JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
			window3.add(panelSouth, BorderLayout.SOUTH);

			BufferedImage title = ImageIO.read(new File("images/options.png"));
			JLabel picLabel = new JLabel(new ImageIcon(title));
			panelNorth.add(picLabel);

			JButton autoPlayer = new JButton("Automates Joueurs");
			autoPlayer.setFocusPainted(false);
			boutonsCentre.add(autoPlayer);

			JLabel autoPlayerText = new JLabel("Aucun fichier choisi");
			boutonsCentre.add(autoPlayerText);

			JButton autoMinions = new JButton("Automates Sbires");
			autoMinions.setFocusPainted(false);
			boutonsCentre.add(autoMinions);

			JLabel autoMinionsText = new JLabel("Aucun fichier choisi");
			boutonsCentre.add(autoMinionsText);

			JButton autoLaser = new JButton("Automates Lasers");
			autoLaser.setFocusPainted(false);
			boutonsCentre.add(autoLaser);

			JLabel autoLasersText = new JLabel("Aucun fichier choisi");
			boutonsCentre.add(autoLasersText);

			JButton retour = new JButton("Retour");
			retour.setFocusPainted(false);
			panelSouth.add(retour);

			autoPlayer.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					UIManager.put("FileChooser.readOnly", Boolean.TRUE);

					JFileChooser choix = new JFileChooser(System.getProperty("user.dir"));
					choix.setAcceptAllFileFilterUsed(false);
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers txt", "txt");
					choix.setFileFilter(filter);
					choix.addChoosableFileFilter(filter);

					int returnVal = choix.showOpenDialog(window3);
					if (choix.getSelectedFile() != null) {
						pathPlayer = choix.getSelectedFile().getPath();
						autoPlayerText.setText(choix.getSelectedFile().getName());
					}
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

				}
			});

			autoMinions.addMouseListener(new MouseListener() {

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
					UIManager.put("FileChooser.readOnly", Boolean.TRUE);

					JFileChooser choix = new JFileChooser(System.getProperty("user.dir"));
					choix.setAcceptAllFileFilterUsed(false);
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers txt", "txt");
					choix.setFileFilter(filter);
					choix.addChoosableFileFilter(filter);

					int returnVal = choix.showOpenDialog(window3);
					if (choix.getSelectedFile() != null) {
						pathMinions = choix.getSelectedFile().getPath();
						autoMinionsText.setText(choix.getSelectedFile().getName());
					}
				}
			});
			autoLaser.addMouseListener(new MouseListener() {

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
					UIManager.put("FileChooser.readOnly", Boolean.TRUE);

					JFileChooser choix = new JFileChooser(System.getProperty("user.dir"));
					choix.setAcceptAllFileFilterUsed(false);
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers txt", "txt");
					choix.setFileFilter(filter);
					choix.addChoosableFileFilter(filter);

					int returnVal = choix.showOpenDialog(window3);
					if (choix.getSelectedFile() != null) {
						pathLaser = choix.getSelectedFile().getPath();
						autoLasersText.setText(choix.getSelectedFile().getName());
					}
				}
			});

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

		}
		window1.dispose();

	}

	public static void afficherPartie() {

		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model);

		Dimension d = new Dimension(1024, 768);
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
			message = "L'antivirus à défendu le système et remporte la victoire";
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
				afficherPartie();
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
