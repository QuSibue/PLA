package ricm3.game.mvc;

public class MainMap {
	
	public static void main(String[] args) {
		Model m = new Model();
		int i = 0;
		int j = 0;
		for (i = 0; i < m.carte.height; i++) {
			for (j = 0; j < m.carte.length; j++) {
				System.out.print(m.carte.matrice[i][j]);
			}
			System.out.println();
		}
	}

}
