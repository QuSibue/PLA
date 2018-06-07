package ricm3.game.entity;

public class Sac {

	Entity[] sac;
	int index;
	int max;

	public Sac(int x) {
		max = x;
		sac = new Entity[max];
		index = 0;
	}

	public boolean setItem(Entity e, int i) {
		if (i >= 0 && i < max) {
			sac[i] = e;
			return true;
		}
		return false;
	}

	public boolean setIdem(Entity e) {
		if (index >= max) {
			return false;
		} else {
			setItem(e, index);
			index = (index + 1) % max;
			return true;
		}

	}

	public Entity getItem(int i) {
		if (i >= 0 && i < max) {
			Entity e = sac[i];
			sac[i] = null;
			return e;
		}

		return null;
	}

	public Entity getItem() {
		Entity e = getItem(index);
		index = (index - 1) % max;
		return e;
	}

}
