package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Transition;
import ricm3.game.other.Options;
import ricm3.game.other.Transversal;

public class Player extends Character {

	int num_aut;
	int energie;


	public Player(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, int ms,
			BufferedImage[] sprites, Automaton aut, int equipe) {
		super(x, y, moveable, pickable, killable, lethal, ms, sprites, aut, equipe, 3);
		num_aut = 0;
		energie = 5;
	}

	// @override
	public void step() {

		Iterator<Transition> iter = this.getAutomaton().getTransitions().iterator();
		Transition transi;
		boolean condition = false;

		// On va chercher la première transition utilisable
		// puis on met a jour l'etat courant
		// et on effectue l'action associée a la transition

		// ce code va surement être deplacé dans being, superclass de player, minion et
		// laser
		while (!condition && iter.hasNext()) {
			transi = iter.next();
			// les etats sont par aliasing on peut donc utiliser le double égale
			condition = this.getEtatCourant() == transi.getInitial()
					&& transi.getCondition().eval((Being) this, global_map);
			if (condition) {
				this.setEtatCourant(transi.getSortie());
				transi.getAction().executeAction(this);
			}

		}
		if (!condition)
			throw new RuntimeException("AUcune transition trouvée pour l'automate dans player");
	}

	// action

	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Transversal.positionRelative(this.getX(), this.getY(), x_res, y_res, d, this.getOrientation());
	}

	// Todo : trouver un moyen de rajouter le minion à la liste des entités
	// mouvantes
	public void pop() {
		int x_res = 0, y_res = 0;
		Minion m;
		if (global_map.caseLibre(this.getX(), this.getY(), x_res, y_res) && energie >= 1) {
			BufferedImage[] sprites = null;
			m = new Minion(x_res, y_res, sprites, new Automaton());
			global_map.setEntity(m);
			// trouver un moyen de rajouter à la liste model

			// cout en energie = 1
			energie--;
		}
		
		// graphiquement, si on ne trouve pas de place, on peut afficher quelque chose à
		// l'écran

	}

	// wizz ne coutent pas d'energie
	public void wizz() {
		num_aut = (num_aut++) % Options.NB_TYPE_MINION;
	}

	public void hit() {
		Entity E;
		int x_res, y_res;
		
		Transversal.positionRelative(this.getX(), this.getY(), x_res, y_res, Direction.FRONT, this.getOrientation());
		if(energie >= 1) {
			if ((E = global_map.getEntity(x_res, y_res)) instanceof Being) {
				//on tue l'entité
				//delete de la map, du model, du graphique
				//(le laser n'apparait jamais)
				
				//delete map 
				if(E instanceof Minion || E instanceof Laser) {
					//si c'est un sbires ou un laser ils disparaissent
					global_map.deleteEntity(E);
				}
				else {
					//faire perdre de la vie à un player, possible game over
				}
				
				//cout en energie = 1
				energie --;
			}
			else if( E == null) {
				//on lance un laser
				Laser l = new Laser();
				global_map.setEntity(l);
				//trouver un moyen de rajouter à la liste model
				
				//cout en energie = 1
				energie --;
			}
		}
		
	}

	public void power() {
		return;
	}

	public void protect() {
		return;
	}

	public void jump() {
		return;
	}

	public void pick() {
		return;
	}

	public void get() {
		return;
	}

	public void store() {
		return;
	}

	public void _throw() {
		return;
	}

	@Override
	public void paint(Graphics g) {
		// affiche un carré bleu pour le joueur
		int m_x = this.getX() * Options.TAILLE_CASE;
		int m_y = this.getY() * Options.TAILLE_CASE;
		g.setColor(Color.blue);
		g.fillRect(m_x, m_y, Options.TAILLE_CASE, Options.TAILLE_CASE);
	}

	@Override
	public void paint() {
		// TODO Auto-generated method stub

	}
}
