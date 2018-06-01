package ricm3.game.mvc;

import ricm3.game.entity.Entity;
import ricm3.game.entity.Wall;

//import java.io.File;
//import java.io.FileInputStream;

public class Map {

	int height;
	int length;

	Entity[][] matrice;

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


	/*
	 * Map(String nom){ File f = new File(nom);
	 * 
	 * FileInputStream fis = null;
	 * 
	 * try { fis = new FileInputStream(f);
	 * 
	 * byte[] buf = new byte[2];
	 * 
	 * int n = 0;
	 * 
	 * n = fis.read(buf);
	 * 
	 * 
	 * 
	 * while((n = fis.read(buf))>=0) {
	 * 
	 * } } }
	 */
	
	public void add(Entity E, int x, int y) {
		matrice[x][y]=E;
	}

	// renvoit ce qu'il y a sur la case [x,y]
	public Entity contenu_case(int x, int y) {
		return matrice[x][y];
	}

}
