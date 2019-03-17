package jeu;

import java.io.Serializable;

public class Quete implements Serializable {
	/**
	 * True for -> déjà faite
	 * False for -> Pas encore faite
	 */
	protected boolean status;
	private Objets recompense;
	private int nbrCoupDebut;
	private int nbrCoupFin;
	
	public Quete()
	{
		
	}
	
	public Quete(Objets recompenseJoueur) {
		recompense = recompenseJoueur;
		status = false;
	}
	protected void terminer(Joueur joueur) {
		joueur.prendreObjet(recompense);
		this.status=true;
		joueur.setQuete(null);
		joueur.niveauActuel++;
	}
	public void lancerQuete(Joueur joueur, Queteur queteur) {
	
	}
	public String executerQuete(Joueur joueur, Queteur queteur) {
		return queteur.dialoguePendantQuete(0);
	}
	public boolean isStatus() {
		return status;
	}
	public void perdu(Joueur joueur) {
		joueur.setPointDeVie(0);
	}
}
