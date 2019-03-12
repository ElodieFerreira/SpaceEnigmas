package jeu;

public class Quête {
	/**
	 * True for -> déjà faite
	 * False for -> Pas encore faite
	 */
	protected int id;
	protected boolean status;
	private Objets recompense;
	public Quête(Objets recompenseJoueur) {
		recompense = recompenseJoueur;
		status = false;
	}
	protected void terminer(Joueur joueur) {
		joueur.prendreObjet(recompense);
		this.status=true;
	}
	public void lancerQuête(Joueur joueur, Quêteur queteur) {
		
	}
}
