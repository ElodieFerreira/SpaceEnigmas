package jeu;

public class Qu�te {
	/**
	 * True for -> d�j� faite
	 * False for -> Pas encore faite
	 */
	protected int id;
	protected boolean status;
	private Objets recompense;
	public Qu�te(Objets recompenseJoueur) {
		recompense = recompenseJoueur;
		status = false;
	}
	protected void terminer(Joueur joueur) {
		joueur.prendreObjet(recompense);
		this.status=true;
	}
	public void lancerQu�te(Joueur joueur, Qu�teur queteur) {
		
	}
}
