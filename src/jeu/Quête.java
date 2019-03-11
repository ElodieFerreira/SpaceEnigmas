package jeu;

public class Quête {
	/**
	 * True for -> déjà faite
	 * False for -> Pas encore faite
	 */
	private boolean status;
	private Objets recompense;
	public Quête(Objets recompenseJoueur) {
		recompense = recompenseJoueur;
		status = false;
	}
	private void terminer() {
		this.status=true;
	}
}
