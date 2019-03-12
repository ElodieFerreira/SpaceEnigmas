package jeu;

public class Lettre {
	private boolean découvert;
	private char contenu;
	
	private Lettre(char cnt) {
		this.découvert = false;
		this.contenu = cnt;
	}
	
	public char toChar() {
		if(this.découvert) {
			return this.contenu;
		}
		else {
			return '-';
		}
	}
	
	
	
	
	
	
}
