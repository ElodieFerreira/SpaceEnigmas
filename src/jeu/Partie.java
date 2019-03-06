package jeu;

public class Partie {
	private Joueur joueur;
	public Partie() {
		joueur = null;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur newJoueur) {
		joueur=newJoueur;
	}
}
