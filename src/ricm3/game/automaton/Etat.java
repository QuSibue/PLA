package ricm3.game.automaton;

public class Etat {
	String m_etat;
	
	public Etat(String e){
		this.m_etat = e;
	}
	
	public Etat(Etat e) {
		this.m_etat = e.getEtat();
	}
	
	
	//getters
	public String getEtat() {
		return m_etat;
	}
	
	//setters
	
	public boolean setEtat(String etat) {
		m_etat = etat;
		return true;
	}
	
	public boolean equals(Etat e) {
		return m_etat.equals(e.getEtat());
	}
	
	
}
