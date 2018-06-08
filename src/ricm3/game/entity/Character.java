package ricm3.game.entity;

import java.awt.Point;
import java.awt.image.BufferedImage;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Orientation;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Transversal;

public abstract class Character extends Being {
	private int m_equipe;
	private Sac m_sac;
private int m_vie;

	public Character(BufferedImage[] sprites, int x, int y, boolean moveable, boolean pickable, boolean killable,
			boolean lethal, int moveSpeed, Automaton automate, Orientation orientation, int equipe, Map map,
			Model model, int life, long lastMove) {
		super(x, y, moveable, pickable, killable, lethal, moveSpeed, sprites, automate, orientation, map, model, life,
				lastMove);
		m_equipe = equipe;
		m_sac = new Sac(5);
	

	/*public Character(int x, int y, boolean moveable, boolean pickable, boolean killable,
			boolean lethal, int moveSpeed, BufferedImage[] sprites,Automaton automate, int equipe, int vie) {
		super(x, y, moveable, pickable, killable, lethal, moveSpeed, sprites, automate);
		m_equipe = equipe;
		m_vie = vie;
>>>>>>> origin/classePortail*/
	}

	public int getEquipe() {
		return m_equipe;
	}

	public void setEquipe(int equipe) {
		m_equipe = equipe;
	}

	@Override
	public void pick() {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		Transversal.evalPosition(this.getX(), this.getY(), p, Direction.FRONT, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if (e.getPickable()) {
			if (m_sac.addItem(e)) {
				global_map.deleteEntity(e);
				this.m_model.m_laser.remove(e);
			}
		}
	}

	@Override
	public void get() {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		Transversal.evalPosition(this.getX(), this.getY(), p, Direction.FRONT, this.getOrientation());
		if(global_map.getEntity(p.x, p.y) == null) {
			Entity e = m_sac.removeItem();
			if(e != null) {
				global_map.setEntity(e);
				if(e instanceof Laser) {
					this.m_model.m_laser.add((Laser)e);
				}
				else if(e instanceof Minion) {
					this.m_model.m_minions.add((Minion)e);
				}
				else if(e instanceof Obstacle) {
					this.m_model.m_obstacles.add((Obstacle)e);
				}
				else if(e instanceof PowerUp) {
					this.m_model.m_powerup.add((PowerUp)e);
				}
			}
		}
	}

}
