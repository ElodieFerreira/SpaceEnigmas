package jeu;

public class Qu�te {
	/**
	 * True for -> d�j� faite
	 * False for -> Pas encore faite
	 */
	private boolean status;
	private Objets recompense;
	public Qu�te(Objets recompenseJoueur) {
		recompense = recompenseJoueur;
		status = false;
	}
	private void terminer() {
		this.status=true;
	}
}
