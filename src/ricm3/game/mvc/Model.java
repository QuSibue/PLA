package ricm3.game.mvc;

import ricm3.game.entity.Wall;
import ricm3.game.framework.GameModel;

public class Model extends GameModel {
	Map carte;
	Wall mur;
	Overhead m_overhead = new Overhead();

	public Model() {
		carte = new Map();
		mur = new Wall(1,1);
		carte.add(mur, mur.getX(), mur.getY());
	}

	@Override
	public void shutdown() {

	}

	@Override
	public void step(long now) {
		
	}

		
}
