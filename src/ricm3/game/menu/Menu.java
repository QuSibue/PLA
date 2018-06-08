package ricm3.game.menu;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import ricm3.game.mains.GameMain;




public class Menu extends javax.swing.JPanel {
  
    private JButton jouer,regles,quitter;

    public Menu() throws IOException { 
        super();
        
        initUIComponents(); 
       
    }
    
    private void initUIComponents() throws IOException{
        
        //Specification de la Frame en GridBagLayout
        this.setLayout(new BorderLayout());
        
        
        //Composant de la frame    
        JPanel panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));        
        this.add(panelCenter,BorderLayout.CENTER);
        
        JPanel boutonsCentre = new JPanel(new GridLayout(3,1));
        panelCenter.add(boutonsCentre);
        
        JPanel panelNord = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.add(panelNord,BorderLayout.NORTH);
   
        
        
        
        //Label du Joueur
        BufferedImage title = ImageIO.read(new File("images/title3.png"));
        JLabel picLabel = new JLabel(new ImageIcon(title));
        panelNord.add(picLabel);
     
        //Bouton addJoueur/delJoueur/reiJoueur
        jouer = new JButton("Jouer");
        jouer.setFocusPainted(false);
        boutonsCentre.add(jouer);
        
        regles = new JButton("Règles");
        regles.setFocusPainted(false);
        boutonsCentre.add(regles);
       
        quitter = new JButton("Quitter");
        quitter.setFocusPainted(false);
        boutonsCentre.add(quitter);
        
        jouer.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Met en bleu le texte du bouton sur lequel l'événément s'est
                // déclenché
                ((JButton) e.getSource()).setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Met en noir le texte du bouton sur lequel l'événément s'est
                // déclenché
                ((JButton) e.getSource()).setForeground(Color.BLACK);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            	GameMain.afficherPartie();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Inutilisé : rien à faire
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //ajouterJoueur();
                // Inutilisé : rien à faire
            }
        });

        regles.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Met en bleu le texte du bouton sur lequel l'événément s'est
                // déclenché
                ((JButton) e.getSource()).setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Met en noir le texte du bouton sur lequel l'événément s'est
                // déclenché
                ((JButton) e.getSource()).setForeground(Color.BLACK);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                //liste.isSelectionEmpty()
                  
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Inutilisé : rien à faire
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
                // Inutilisé : rien à faire
            }
        });
        
        quitter.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Met en bleu le texte du bouton sur lequel l'événément s'est
                // déclenché
                ((JButton) e.getSource()).setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Met en noir le texte du bouton sur lequel l'événément s'est
                // déclenché
                ((JButton) e.getSource()).setForeground(Color.BLACK);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Inutilisé : rien à faire
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
                // Inutilisé : rien à faire
            }
        });

        
       
       
         
    }
}
