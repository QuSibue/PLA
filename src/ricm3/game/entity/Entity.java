package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ricm3.game.automaton.Orientation;
import javax.swing.ImageIcon;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Transversal;

public abstract class Entity {

	private int m_coordinateX;
	private int m_coordinateY;
	private boolean m_moveable;
	private boolean m_pickable;
	private boolean m_killable;
	private boolean m_lethal;

	private BufferedImage[][] m_sprites;
	private int m_nbImageRefresh;
	private int m_indexRefresh = 0;
	private int m_numImage = 0;
	private int m_nbImage;
	private ImageIcon m_icon;

	public Map global_map;
	public Model m_model;
	// public ImageDataBase m_idb;

	public Entity(int x, int y, boolean move, boolean pick, boolean kill, boolean leth, BufferedImage[][] sprites,
			int nbImage, ImageIcon icon, Map map, Model model) {

		this.m_coordinateX = x;
		this.m_coordinateY = y;
		this.m_moveable = move;
		this.m_pickable = pick;
		this.m_killable = kill;
		this.m_lethal = leth;
		this.m_sprites = sprites;
		this.m_icon = icon;
		this.global_map = map;
		this.m_model = model;
		this.m_nbImage = nbImage;
		this.m_nbImageRefresh = (int) ricm3.game.framework.Options.FPS / m_nbImage;

	}

	public BufferedImage[] getDirectionSprite(Orientation direction) {
		switch (direction) {
		case UP:
			return m_sprites[0];
		case RIGHT:
			return m_sprites[1];
		case DOWN:
			return m_sprites[2];
		case LEFT:
			return m_sprites[3];
		default:
			throw new RuntimeException("Orientation du sprite invalide.");
		}

	}

	public Entity closestEntity(Entity e1, Entity e2) {
		int be1 = 0, be2 = 0;
		if (e1 == null) {
			if (e2 == null) {
				throw new RuntimeException("Les deux entités sont null");
			}
			return e2;
		} else if (e2 == null) {
			return e1;
		}
		if(e1 == this) {
			if(e2 == this) {
				return null;
			}
			else {
				return e2;
			}
		}
		else if ( e2 == this) {
			return e1;
		}
		
		/*
		be1 = (e1.getX() - this.getX()) * (e1.getY() - this.getY())
				+ (e1.getY() - this.getY()) * (e1.getY() - this.getY());
		be2 = (e2.getX() - this.getX()) * (e2.getY() - this.getY())
				+ (e2.getY() - this.getY()) * (e2.getY() - this.getY());
		*/
		be1 = Transversal.abs(e1.getX()- this.getX()) + Transversal.abs(e1.getY() - this.getY());
		be2 = Transversal.abs(e2.getX()- this.getX()) + Transversal.abs(e2.getY() - this.getY());
		
		
		if (be1 <= be2) {
			return e1;
		} else{
			return e2;
		}

	}

	// getters

	public int getX() {
		return m_coordinateX;
	}

	public int getY() {
		return m_coordinateY;
	}

	public boolean getMoveable() {
		return m_moveable;
	}

	public boolean getPickable() {
		return m_pickable;
	}

	public boolean getKillable() {
		return m_killable;
	}

	public boolean getLethal() {
		return m_lethal;
	}

	public BufferedImage[][] getSprites() {
		return m_sprites;
	}

	public ImageIcon getIcon() {
		return m_icon;
	}

	public int getNbImageRefresh() {
		return this.m_nbImageRefresh;
	}

	public int getIndexRefresh() {
		return this.m_indexRefresh;
	}

	public int getNumImage() {
		return this.m_numImage;
	}

	public int getNbImage() {
		return this.m_nbImage;
	}

	// setters
	public boolean setX(int x) {
		m_coordinateX = x;
		return true;
	}

	public boolean setY(int y) {
		m_coordinateY = y;
		return true;
	}

	public boolean setMoveable(boolean moveable) {
		m_moveable = moveable;
		return true;
	}

	public boolean setPickable(boolean pickable) {
		m_pickable = pickable;
		return true;
	}

	public boolean setKillable(boolean killable) {
		m_killable = killable;
		return true;
	}

	public boolean setLethal(boolean lethal) {
		m_lethal = lethal;
		return true;
	}

	public boolean setSprites(BufferedImage[][] sprites) {
		m_sprites = sprites;
		return true;
	}

	public boolean setNbImageRefresh(int a) {
		this.m_nbImageRefresh = a;
		return true;
	}

	public boolean setIndexRefresh(int a) {
		this.m_indexRefresh = a;
		return true;
	}

	public boolean setNumImage(int a) {
		this.m_numImage = a;
		return true;
	}

	public boolean setNbiMAGE(int a) {
		this.m_nbImage = a;
		return true;
	}

	public abstract void paint(Graphics g);

	public void pretty_print() {
		System.out.print("Entity");
	}
}
