package ricm3.game.entity;

public class Sac {

	private Entity[] m_sac;
	private int m_index;
	private int m_max;

	public Sac(int x) {
		m_max = x;
		m_sac = new Entity[m_max];
		m_index = 0;
	}

	public boolean setSac(Entity[] a) {
		m_sac = a;
		m_max = a.length;
		m_index = 0;
		return true;
	}

	public boolean addItem(Entity e, int i) {
		if (i >= 0 && i < m_max) {
			m_sac[i] = e;
			return true;
		}
		return false;
	}

	public boolean addItem(Entity e) {
		if (m_index >= m_max) {
			return false;
		} else {
			while (m_index >= m_max && m_sac[m_index] != null) {
				m_index++;
			}
			if (m_index == m_max) {
				m_index = 0;
				while (m_sac[m_index] != null && m_index >= m_max) {
					m_index++;
				}
				if (m_index == m_max) {
					return false;
				}
			}
			addItem(e, m_index);
			m_index = (m_index + 1) % m_max;
			return true;
		}

	}

	public Entity removeItem(int i) {
		if (i >= 0 && i < m_max) {
			Entity e = m_sac[i];
			m_sac[i] = null;
			return e;
		}

		return null;
	}

	public Entity removeItem() {
		while (m_index - 1 > 0 && m_sac[m_index - 1] == null) {
			m_index--;
		}
		m_index = (m_index - 1) % m_max;
		return removeItem(m_index);

	}

}
