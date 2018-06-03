package ricm3.game.automaton;

public class Etat {
	int m_etat;
	
	public Etat(int e){
		this.m_etat = e;
	}
	
	public Etat(Etat e) {
		this.m_etat = e.getEtat();
	}
	
	
	//getters
	public int getEtat() {
		return m_etat;
	}
	
	//setters
	
	public boolean setEtat(int etat) {
		m_etat = etat;
		return true;
	}
	
	
}
