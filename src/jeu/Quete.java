package jeu;

import java.io.Serializable;

public class Quete implements Serializable {
	/**
	 * True for -> déjà faite
	 * False for -> Pas encore faite
	 */
	protected int id;
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
	}
	public boolean lancerQuete(Joueur joueur, Queteur queteur) {
		if(!status) {
			return true;
		} else {
			return false;
		}
	}
	public String executerQuete(Joueur joueur, Queteur queteur) {
		return null;
	}
	public boolean isStatus() {
		return status;
	}
	
}
