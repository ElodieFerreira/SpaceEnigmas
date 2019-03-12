package jeu;

public class Quête {
	/**
	 * True for -> d�j� faite
	 * False for -> Pas encore faite
	 */
	protected boolean status;
	private Objets recompense;
	public Quête(Objets recompenseJoueur) {
		recompense = recompenseJoueur;
		status = false;
	}
	private void terminer() {
		this.status=true;
	}
}
