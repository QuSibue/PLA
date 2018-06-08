package ricm3.game.mvc;

import java.awt.Point;

import java.io.FileInputStream;
import java.io.IOException;

import ricm3.game.entity.Entity;
import ricm3.game.entity.Laser;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Wall;
import ricm3.game.entity.Player;

//import java.io.File;
//import java.io.FileInputStream;

public class Map {

	private int m_height;
	private int m_length;

	private Entity[][] matrice;

	public Map(int height, int length) {
		m_height = height;
		m_length = length;

		matrice = new Entity[m_height][m_length];
		for (int i = 0; i < m_height; i++) {
			for (int j = 0; j < m_length; j++) {
				matrice[i][j] = null;
			}
		}
	}

	public boolean setEntity(Entity E) {
		matrice[E.getY()][E.getX()] = E;
		return true;
	}

	public boolean setEntity(int x, int y, Entity E) {
		E.setX(x);
		E.setY(y);
		setEntity(E);
		return true;
	}

	public Entity getEntity(int x, int y) {
		return matrice[y][x];
	}

	public void deleteEntity(Entity E) {
		matrice[E.getY()][E.getX()] = null;
	}

	public void moveEntity(Entity E, int x, int y) {
		deleteEntity(E);
		setEntity(x, y, E);
	}

	public void printMap() {
		int i = 0;
		int j = 0;
		for (i = 0; i < m_height; i++) {
			for (j = 0; j < m_length; j++) {
				if (matrice[i][j] != null) {
					if (matrice[i][j] instanceof Player) {
						System.out.print("Player");
					} else if (matrice[i][j] instanceof Obstacle) {
						System.out.print("Obs");
					} else if (matrice[i][j] instanceof Laser) {
						System.out.print("Las");

					} else if (matrice[i][j] instanceof Wall) {
						System.out.print("Wall");

					}
				} else {
					System.out.print(matrice[i][j]);
				}

				System.out.print(" ");
			}
			System.out.println();
		}
	}

	// renvoit la première case libre en absolue
	public boolean caseLibre(int x, int y, Point p) {
		p.x = -1;
		p.y = -1;

		// on test la case au dessus du personnage
		if (matrice[y - 1][x] == null) {
			p.x = x;
			p.y = y - 1;
			return true;
		}
		// on test la case a droite du personnage
		else if (matrice[y][x + 1] == null) {
			p.x = x + 1;
			p.y = y;
			return true;
		}
		// on test la case en bas du personnage
		if (matrice[y + 1][x] == null) {
			p.x = x;
			p.y = y + 1;
			return true;
		}
		// on test la case a gauche du personnage
		else if (matrice[y][x - 1] == null) {
			p.x = x - 1;
			p.y = y;
			return true;
		}
		// on test la case en haut à droite du personnage
		else if (matrice[y - 1][x + 1] == null) {
			p.x = x + 1;
			p.y = y - 1;
			return true;
		}
		// on test la case en bas à droite du personnage
		else if (matrice[y + 1][x + 1] == null) {
			p.x = x + 1;
			p.y = y + 1;
			return true;
		}
		// on test la case en bas à gauche du personnage
		else if (matrice[y + 1][x - 1] == null) {
			p.x = x - 1;
			p.y = y + 1;
			return true;
		}
		// on test la case en haut à gauche du personnage
		else if (matrice[y - 1][x - 1] == null) {
			p.x = x - 1;
			p.y = y - 1;

			return true;
		}

		return false;

	}

	public Map(String s, Model m) {
		int cmp = 0;
		m_height = 0;
		m_length = 0;

		try (FileInputStream fis = new FileInputStream(s)) {
			byte[] buf = new byte[8];
			if (fis.read(buf) < 0) {
				throw new IOException();
			}
			int i = 0;
			while (buf[i] != ' ') {
				m_height = m_height * 10 + (char) buf[i] - 48;
				i++;
				if (i == 8) {
					if (fis.read(buf) < 0) {
						throw new IOException();
					}
					i = 0;
				}
			}
			i++;
			if (i == 8) {
				if (fis.read(buf) < 0) {
					throw new IOException();
				}
				i = 0;
			}
			while (buf[i] != '\n') {
				m_length = m_length * 10 + buf[i] - 48;
				i++;
				if (i == 8) {
					if (fis.read(buf) < 0) {
						throw new IOException();
					}
					i = 0;
				}
			}
			matrice = new Entity[m_height][m_length];
			i++;
			if (i == 8) {
				if (fis.read(buf) < 0) {
					throw new IOException();
				}
				i = 0;
			}
			while (cmp < m_height * m_length) {
				switch ((char) buf[i]) {
				case 'w':
					matrice[cmp % m_height][cmp / m_height] = new Wall(cmp / m_height, cmp % m_height, this, m);
					cmp++;
					break;
				case 'o':
					matrice[cmp % m_height][cmp / m_height] = new Obstacle(cmp / m_height, cmp % m_height, false, true,
							false, false, null, this, m);
					cmp++;
					break;
				case 'n':
					matrice[cmp % m_height][cmp / m_height] = null;
					cmp++;
				default:
					matrice[cmp % m_height][cmp / m_height] = null;
					break;
				}
				i++;
				if (i == 8) {
					if (fis.read(buf) < 0) {
						throw new IOException();
					}
					i = 0;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map(Model m) {
		m_height = 1000;
		m_length = 800;

		int pourcentage = 70;

		matrice = new Entity[m_height][m_length];

		for (int i = 0; i < m_height; i++) {
			matrice[i][0] = new Wall(i, 0, this, m);
			matrice[i][m_length - 1] = new Wall(i, m_length - 1, this, m);
		}
		for (int i = 0; i < m_length; i++) {
			matrice[0][i] = new Wall(0, i, this, null);
			matrice[m_height - 1][i] = new Wall(m_height - 1, i, this, m);
		}

		for (int i = 1; i < m_length - 1; i++) {
			for (int j = 1; j < m_height - 1; j++) {
				int rand = (int) (Math.random() * 100);
				if (rand < pourcentage) {
					matrice[j][i] = new Obstacle(j, i, false, true, false, false, null, this, m);
				} else {
					matrice[j][i] = null;
				}
			}
		}

	}

}
