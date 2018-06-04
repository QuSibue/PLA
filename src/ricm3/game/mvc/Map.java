package ricm3.game.mvc;

import ricm3.game.entity.Entity;
import ricm3.game.entity.Obstacle;
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

}
