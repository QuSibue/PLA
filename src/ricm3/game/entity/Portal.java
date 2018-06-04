package ricm3.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ricm3.game.other.Options;

public class Portal extends Entity {

	private int coordinateX_end;
	private int coordinateY_end;

	private int nb_utilisation;

	private long lifespan;
	private long m_last;

	public Portal(int x_start, int y_start, int x_end, int y_end, BufferedImage[] sprites) {
		super(x_start, y_start, false, false, false, false, sprites);
		coordinateX_end = x_end;
		coordinateY_end = y_end;
		nb_utilisation = 3;
		lifespan = 15000; // aucune idée de la valeur ideal
		m_last = 0;
	}

	// getters

	public int getX_start() {
		return this.getX();
	}

	public int getY_start() {
		return this.getY();
	}

	public int getX_end() {
		return coordinateX_end;
	}

	public int getY_end() {
		return coordinateY_end;
	}

	// setters

	public boolean setX_start(int x) {
		this.setX(x);
		return true;
	}

	public boolean setY_start(int y) {
		this.setY(y);
		return true;
	}

	public boolean setX_end(int x) {
		coordinateX_end = x;
		return true;
	}

	public boolean setY_end(int y) {
		coordinateY_end = y;
		return true;
	}

	// faire attention à ne pas faire des aller retour entre end et start (ne devrait
	// pas arriver).
	//on appel la fonction quand / ou ??? 
	public void use_portal(Being E) {
		nb_utilisation--;

		// deplacement de l'entité
		if (E.getX() == getX_start() && E.getY() == getY_start()) {
			global_map.moveEntity(E, getX_end(), getY_end());
		} else if (E.getX() == getX_end() && E.getY() == getY_end()) {
			global_map.moveEntity(E, getX_start(), getY_start());
		}

		// il faut peut etre mettre ça dans step ???
		if (nb_utilisation == 0) {
			global_map.deleteEntity(this);
			// enlever de la liste des trucs à step
		}
	}

	public void step(long now) {
		long elapsed = 0;
		if (m_last != 0) {
			elapsed = now - m_last;
		}
		m_last = now;
		lifespan = lifespan - elapsed;
		if (lifespan <= 0) {
			// kaboum
			global_map.deleteEntity(this);
			// enlever de la liste des trucs à step
		}
	}

	public void paint(Graphics g) {
		// dessin portail debut
		int m_x = this.getX_start() * Options.TAILLE_CASE;
		int m_y = this.getY_start() * Options.TAILLE_CASE;
		g.setColor(Color.pink);
		g.fillRect(m_x, m_y, Options.TAILLE_CASE, Options.TAILLE_CASE);

		// dessin portail fin
		m_x = this.getX_end() * Options.TAILLE_CASE;
		m_y = this.getY_end() * Options.TAILLE_CASE;
		g.setColor(Color.magenta);
		g.fillRect(m_x, m_y, Options.TAILLE_CASE, Options.TAILLE_CASE);

		// il faudra peut être faire une animation de fin quand lifespan arrive à 0
	}

}
