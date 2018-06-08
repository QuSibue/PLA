package ricm3.game.menu;


import java.awt.BorderLayout;
import java.awt.Color;
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




public class Menu extends javax.swing.JPanel {
    private JTextField saisirInscription;
    private Integer numero=1;
    private JButton addJoueur,delJoueur,reiJoueur;

    public Menu() throws IOException { 
        super();
        
        initUIComponents(); 
       
    }
    
    private void initUIComponents() throws IOException{
        
        //Specification de la Frame en GridBagLayout
        this.setLayout(new BorderLayout());
        //Composant de la frame
        JPanel panelNord = new JPanel();
        panelNord.setLayout(new BorderLayout());
        this.add(panelNord,BorderLayout.NORTH);
        
        JPanel panelSud = new JPanel();
        panelSud.setLayout(new BorderLayout());
        this.add(panelSud,BorderLayout.SOUTH);
        
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        this.add(panelCenter,BorderLayout.CENTER);
        
        //Label du Joueur
        BufferedImage title = ImageIO.read(new File("images/title2.png"));
        JLabel picLabel = new JLabel(new ImageIcon(title));
        
       /* c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;*/
        panelNord.add(picLabel,BorderLayout.CENTER);

        
        //Saisie du Joueur
        JLabel joueursInscrits = new JLabel("Joueurs Inscrits:");

        
        //Bouton addJoueur/delJoueur/reiJoueur
        addJoueur = new JButton("Ajouter Joueur");
        addJoueur.setFocusPainted(false);
        panelSud.add(addJoueur,BorderLayout.WEST);
        delJoueur = new JButton("Supprimer Joueur");
        delJoueur.setFocusPainted(false);
        delJoueur.setEnabled(false);
        panelSud.add(delJoueur,BorderLayout.CENTER);
        reiJoueur = new JButton("Reinitialiser");
        reiJoueur.setFocusPainted(false);
        reiJoueur.setEnabled(false);
        panelSud.add(reiJoueur,BorderLayout.EAST);
 
        
        addJoueur.addMouseListener(new MouseListener() {
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
                //ajouterJoueur();
                // Inutilisé : rien à faire
            }
        });

        delJoueur.addMouseListener(new MouseListener() {
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
        
        reiJoueur.addMouseListener(new MouseListener() {
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
                //reinitialiserJoueur();;
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
        
        //Panel du Tableau
        JPanel JTableau = new JPanel();
        JTableau.setLayout(new BorderLayout());
        
        //Création du Tableau : Joueur 
        JTableau.add(joueursInscrits,BorderLayout.NORTH);
       
        this.add(JTableau,BorderLayout.CENTER);  
    }
    
    
    
    
public void afficherMenu() {
        
        JFrame window1 = new JFrame("Short Circuit");
        // Indique de sortir du programme lorsque la fenêtre est fermée par l'utilisateur
        window1.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window1.setSize(450, 250);
        
     
        
        
        window1.setResizable(false);
        window1.setLocationRelativeTo(null);
        window1.setVisible(true);
        
        window1.setLayout(new BorderLayout());
        window1.add(this,BorderLayout.CENTER);
        
        
        //Bouton CommencerPartie
        JButton commencer = new JButton("Commencer Partie");
        window1.add(commencer,BorderLayout.SOUTH);
        commencer.setFocusPainted(false);
       
        
        
        
        commencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //afficherPartie(); 
            }});
        
        
    }

}
