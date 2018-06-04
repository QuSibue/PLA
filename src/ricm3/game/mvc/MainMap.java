package ricm3.game.mvc;

public class MainMap {

	public static void main(String[] args) {
		Map m  = new Map("carte.txt");
		
		//System.out.print(m.height);
		//System.out.print(m.length);
		
		m.printMap();
	}

}
