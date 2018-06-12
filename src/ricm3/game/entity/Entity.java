package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import ricm3.game.automaton.TypeEntity;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;

public abstract class Entity {

	private int m_coordinateX;
	private int m_coordinateY;
	private boolean m_moveable;
	private boolean m_pickable;
	private boolean m_killable;
	private boolean m_lethal;
	private BufferedImage[] m_sprites;
	public Map global_map;
	public Model m_model;

	public Entity(int x, int y, boolean move, boolean pick, boolean kill, boolean leth, BufferedImage[] sprites,
			Map map, Model model) {
		this.m_coordinateX = x;
		this.m_coordinateY = y;
		this.m_moveable = move;
		this.m_pickable = pick;
		this.m_killable = kill;
		this.m_lethal = leth;
		this.m_sprites = sprites;
		this.global_map = map;
		this.m_model = model;

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

		be1 = (e1.getX() - this.getX()) * (e1.getY() - this.getY())
				+ (e1.getY() - this.getY()) * (e1.getY() - this.getY());
		be2 = (e2.getX() - this.getX()) * (e2.getY() - this.getY())
				+ (e2.getY() - this.getY()) * (e2.getY() - this.getY());

		if (be1 > be2) {
			return e2;
		} else if (be2 > be1) {
			return e1;
		}
		// si elle sont egale on renvoit la première plus proche
		else {
			return e1;
		}

	}

	public boolean evalEntity(Entity e, TypeEntity t) {
		if (e == null) {
			if (t.equals(TypeEntity.VOID)) {
				return true;
			} else {
				return false;
			}
		} else if (t.equals(TypeEntity.DANGER)) {
			return e instanceof Laser;
		} else if (t.equals(TypeEntity.TEAM)) {
			return ((Character) e).getEquipe() == ((Character) this).getEquipe();
		} else if (t.equals(TypeEntity.ENEMY)) {
			return ((Character) e).getEquipe() != ((Character) this).getEquipe();
		} else if (t.equals(TypeEntity.PICKABLE)) {
			return e instanceof Obstacle || e instanceof Laser || e instanceof Minion || e instanceof Portal;
		} else if (t.equals(TypeEntity.PROJECTILE)) {
			return e instanceof Laser;
		} else if (t.equals(TypeEntity.GATE)) {
			return e instanceof Portal;
		} else {
			throw new RuntimeException("Type non trouvé");
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

	public abstract void paint(Graphics g);

	public void pretty_print() {
		System.out.print("Entity");
	}
}
