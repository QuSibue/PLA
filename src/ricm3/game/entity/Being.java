package ricm3.game.entity;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.swing.ImageIcon;

import ricm3.game.automaton.Automaton;
import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Etat;
import ricm3.game.automaton.Orientation;
import ricm3.game.automaton.Transition;
import ricm3.game.mvc.Map;
import ricm3.game.mvc.Model;
import ricm3.game.other.Options;
import ricm3.game.other.Transversal;

public abstract class Being extends Entity {
	public boolean isexploding;

	// attributs
	private int m_moveSpeed;
	private Automaton m_automaton;
	private Etat m_etatCourant;
	private Orientation m_orientation;
	private int m_life;
	private long m_lastMove;
	private int m_indexBuffer = 0;

	// Constructor
	public Being(int x, int y, boolean moveable, boolean pickable, boolean killable, boolean lethal, int ms,
			BufferedImage[][] sprites, int nbImage, ImageIcon icon, Automaton aut, Orientation orientation, Map map,
			Model model, int life, long lastMove) {

		// appel au constructeur de entity
		super(x, y, moveable, pickable, killable, lethal, sprites, nbImage, icon, map, model);

		m_moveSpeed = ms;
		m_automaton = aut; // alias
		// TODO rajouter l'orientation
		m_orientation = orientation;
		// ALiasing possible puisque on ne vas jamais modifier les objets
		m_etatCourant = m_automaton.getEtatInitial();
		m_life = life;
		m_lastMove = lastMove;
		isexploding = false;
	}

	// getters
	public int getIndexBuffer() {
		return m_indexBuffer;
	}

	public int getLife() {
		return m_life;
	}

	public int getMoveSpeed() {
		return m_moveSpeed;
	}

	public Automaton getAutomaton() {
		return m_automaton;
	}

	public Etat getEtatCourant() {
		return m_etatCourant;
	}

	public Orientation getOrientation() {
		return m_orientation;
	}

	// setters

	public boolean setIndexBuffer(int i) {
		m_indexBuffer = i;
		return true;
	}

	public boolean setLife(int life) {
		if (life <= 3) {
			m_life = life;
		}
		return true;
	}

	public boolean setMoveSpeed(int movespeed) {
		m_moveSpeed = movespeed;
		return true;
	}

	public boolean setAutomaton(Automaton automaton) {
		m_automaton = automaton;
		return true;
	}

	public boolean setEtatCourant(Etat etat) {
		m_etatCourant = etat;
		return true;
	}

	public boolean setOrientation(Orientation o) {
		m_orientation = o;
		return true;
	}

	public void reinitialisation() {
		m_etatCourant = m_automaton.getEtatInitial();
	}

	public long getLastMove() {
		return this.m_lastMove;
	}

	public void setLastMove(long lastMove) {
		this.m_lastMove = lastMove;
	}

	// methodes abstraites

	public void step(long now) {
		long elapsed = now - m_lastMove;
		if (elapsed > this.m_moveSpeed * 1L) {
			m_lastMove = now;
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
				condition = this.getEtatCourant().equals(transi.getInitial())
						&& transi.getCondition().eval((Being) this, global_map);
				if (condition) {
					this.setEtatCourant(transi.getSortie());
					transi.getAction().executeAction(this, now);
				}

			}
			if (!condition)
				throw new RuntimeException("AUcune transition trouvée pour l'automate dans player");
		}

	}

	public void move(Direction d) {
		int x_res = 0, y_res = 0;
		Point p = new Point(x_res, y_res);
		this.turn(d);
		Transversal.evalPosition(this.getX(), this.getY(), p, d, this.getOrientation());
		Entity e = global_map.getEntity(p.x, p.y);
		if (e == null || e instanceof Laser || e instanceof PowerUp) {
			if (e != null) {
				if (e.getLethal()) {
					this.getDamage();
					global_map.deleteEntity(e);
					m_model.m_laser.remove(e);
					if (((Laser) e).erased_powerup != null) {
						((Laser) e).erased_powerup.applyPowerUp(this);
						m_model.m_powerup.remove(((Laser) e).erased_powerup);
					}
				} else if (e instanceof PowerUp) {
					((PowerUp) e).applyPowerUp(this);

					global_map.deleteEntity(e);
					m_model.m_powerup.remove(e);
				}
			}
			global_map.moveEntity(this, p.x, p.y);
		} else if (e instanceof Drapeau && this.m_model.virus.equals(this)) {
			global_map.deleteEntity(e);
			global_map.moveEntity(this, p.x, p.y);
			this.m_model.flagCaptured = true;
		} else if (e instanceof Portal) {
			((Portal) e).use_portal(this, p);
		}
	}

	public abstract void pop(long now);

	public abstract void wizz();

	public abstract void hit(long now);

	public void getDamage() {
		this.m_life--;
		if (this.m_life <= 0) {
			this.global_map.deleteEntity(this);
			if (this instanceof Minion) {
				m_model.m_minions.remove(this);
			} else if (this instanceof Laser) {
				m_model.m_laser.remove(this);
			}
		}
	}

	public abstract void power(long now);

	public abstract void protect();

	public abstract void jump();

	public abstract void pick();

	public abstract void get();

	public abstract void store();

	public abstract void _throw();

	public void paint(Graphics g) {
		if (isexploding) {
			int i = getIndexRefresh();
			setIndexRefresh(i + 1);
			if (i == getNbImageRefresh()) {
				setIndexRefresh(0);
				int j = getNumImage();
				setNumImage(j + 1);
			}

			g.drawImage(m_model.m_idb.explosion[0][getNumImage() % getNbImage()],
					(this.getX() - 1) * Options.TAILLE_CASE, (this.getY() - 1) * Options.TAILLE_CASE,
					Options.TAILLE_CASE * 3, Options.TAILLE_CASE * 3, null);
			if ((getNumImage() % getNbImage()) == 8) {
				m_model.m_minions.remove(this);
			}
		} else {
			BufferedImage[] animation = getDirectionSprite(getOrientation());
			// cet indice va devoir bouger pour animer l'entité
			int i = getIndexRefresh();
			setIndexRefresh(i + 1);
			if (i == getNbImageRefresh()) {
				setIndexRefresh(0);
				int j = getNumImage();
				setNumImage(j + 1);
			}

			g.drawImage(animation[getNumImage() % getNbImage()], this.getX() * Options.TAILLE_CASE,
					this.getY() * Options.TAILLE_CASE, Options.TAILLE_CASE, Options.TAILLE_CASE, null);
		}
	}

	public void turn(Direction d) {
		// TODO Auto-generated method stub
		switch (d) {
		case NORTH:
			this.setOrientation(Orientation.UP);
			break;

		case SOUTH:
			this.setOrientation(Orientation.DOWN);
			break;

		case EAST:
			this.setOrientation(Orientation.RIGHT);
			break;

		case WEST:
			this.setOrientation(Orientation.LEFT);
			break;
		case FRONT:
			break;
		case BACK:
			break;
		case RIGHT:
			break;
		case LEFT:
			break;

		default:
			throw new RuntimeException("Direction invalid");
		}
	}

	public abstract void kamikaze();

}
