package ricm3.game.mvc;

//import java.io.File;
//import java.io.FileInputStream;

public class Map {

	int height;
	int length;

	char[][] matrice;
	
	Map(){
		height = 3;
		length = 3;
		matrice = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrice[i][j] = 'n';
			}
		}
		
		matrice[1][1] = 'w';
	}

	Map(int h, int l) {
		height = h;
		length = l;
		matrice = new char[length][height];

		for (int i = 0; i < l; i++) {
			for (int j = 0; j < h; j++) {
				matrice[i][j] = 'N';
			}
		}
	}

	/*Map(String nom){
		File f = new File(nom);
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(f);
			
			byte[] buf = new byte[2];
			
			int n = 0;
			
			n = fis.read(buf);
			
			
			
			while((n = fis.read(buf))>=0) {
				
			}
		}
	}*/

}
