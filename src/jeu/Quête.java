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
	private void terminer() {
		this.status=true;
	}
	public void lancerQu�te() {
		
	}
}
