package jeu;

public class Quete {
	/**
	 * True for -> déjà faite
	 * False for -> Pas encore faite
	 */
	protected int id;
	protected boolean status;
	private Objets recompense;
	public Quete(Objets recompenseJoueur) {
		recompense = recompenseJoueur;
		status = false;
	}
	protected void terminer(Joueur joueur) {
		joueur.prendreObjet(recompense);
		this.status=true;
	}
	public void lancerQuete(Joueur joueur, Queteur queteur) {
		
	}
}
