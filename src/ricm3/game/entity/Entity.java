package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ricm3.game.mvc.Map;

public abstract class Entity {

	private int m_coordinateX;
	private int m_coordinateY;
	private boolean m_moveable;
	private boolean m_pickable;
	private boolean m_killable;
	private boolean m_lethal;
	private BufferedImage[] m_sprites;
	public Map global_map;

	public Entity(int x, int y, boolean move, boolean pick, boolean kill, boolean leth, BufferedImage[] sprites,Map map) {
		this.m_coordinateX = x;
		this.m_coordinateY = y;
		this.m_moveable = move;
		this.m_pickable = pick;
		this.m_killable = kill;
		this.m_lethal = leth;
		this.m_sprites = sprites;
		this.global_map = map;

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
	
	public abstract void paint(Graphics g);
	
	public void pretty_print() {
		System.out.print("Entity");
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

	public BufferedImage[] getSprites() {
		return m_sprites;
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
	public boolean setSprites(BufferedImage[] sprites) {
		m_sprites = sprites;
		return true;
	}
}

