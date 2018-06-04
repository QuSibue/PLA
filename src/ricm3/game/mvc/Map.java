package ricm3.game.mvc;

import java.io.FileInputStream;
import java.io.IOException;

import ricm3.game.entity.Entity;
import ricm3.game.entity.Obstacle;
import ricm3.game.entity.Wall;

//import java.io.File;
//import java.io.FileInputStream;

public class Map {

	public int height;
	public int length;

	private Entity[][] matrice;

	Map() {
		height = 3;
		length = 3;
		matrice = new Entity[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrice[i][j] = null;
			}
		}
	}

	public boolean setEntity(Entity E) {
		matrice[E.getX()][E.getY()] = E;
		return true;
	}

	public boolean setEntity(int x, int y, Entity E) {
		E.setX(x);
		E.setY(y);
		setEntity(E);
		return true;
	}

	public Entity getEntity(int x, int y) {
		return matrice[x][y];
	}

	public void deleteEntity(Entity E) {
		matrice[E.getX()][E.getY()] = null;
	}

	public void moveEntity(Entity E, int x, int y) {
		deleteEntity(E);
		setEntity(x, y, E);
	}

	public void printMap() {
		int i = 0;
		int j = 0;
		for (i = 0; i < height; i++) {
			for (j = 0; j < length; j++) {
				if (matrice[i][j] != null) {
					(matrice[i][j]).pretty_print();
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
