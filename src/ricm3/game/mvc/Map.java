package ricm3.game.mvc;

import java.io.FileInputStream;
import java.io.IOException;

import ricm3.game.entity.Entity;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Wall;
import ricm3.game.entity.Player;

//import java.io.File;
//import java.io.FileInputStream;

public class Map {

	private int m_height;
	private int m_length;

	private Entity[][] matrice;

	Map(int height, int length) {
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
					if(matrice[i][j] instanceof Player) {
						System.out.print("Player");
					}
					else if(matrice[i][j] instanceof Obstacle) {
						System.out.print("Obs");
					}
				} else {
					System.out.print(matrice[i][j]);
				}

				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public Map(String s) {
		int cmp = 0;
		height = 0;
		length = 0;

		try (FileInputStream fis = new FileInputStream(s)) {
			byte[] buf = new byte[8];
			if (fis.read(buf) < 0) {
				throw new IOException();
			}
			int i = 0;
			while (buf[i] != ' ') {
				height = height * 10 + (char) buf[i] - 48;
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
				length = length * 10 + buf[i] - 48;
				i++;
				if (i == 8) {
					if (fis.read(buf) < 0) {
						throw new IOException();
					}
					i = 0;
				}
			}

			matrice = new Entity[height][length];

			i++;
			if (i == 8) {
				if (fis.read(buf) < 0) {
					throw new IOException();
				}
				i = 0;
			}

			while (cmp < height * length) {
				switch ((char) buf[i]) {
				case 'w':
					matrice[cmp % height][cmp / height] = new Wall(cmp / height, cmp % height);
					break;
				case 'o':
					matrice[cmp % height][cmp / height] = new Obstacle(cmp / height, cmp % height, false, true, false,
							false, null);
					break;
				case 'n':

				default:
					break;
				}
				cmp++;
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

}
