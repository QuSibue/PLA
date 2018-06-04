package ricm3.game.mvc;

import ricm3.game.entity.Entity;

//import java.io.File;
//import java.io.FileInputStream;

public class Map {

	private int height;
	private int length;

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

}
