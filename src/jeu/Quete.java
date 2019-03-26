package jeu;

import java.io.Serializable;

public class Quete implements Serializable {
	private static final long serialVersionUID = -3147278656841914623L;
	/**
	 * True for  déjà faite
	 * False for  Pas encore faite
	 */
	protected boolean status;
	private Objets recompense;
	private int nbrCoupDebut;
	private int nbrCoupFin;
	
	/* Constructeur servant uniquement au bon déroulement de la sérialisation car 
	nécessite un constructeur sans argument*/
	public Quete()
	{
		
	}
	
	/**
	 * 
	 * This constructor initialize quest status and player reward
	 * @param recompenseJoueur represent the reward of the player
	 */
	public Quete(Objets recompenseJoueur) {
		recompense = recompenseJoueur;
		status = false;
	}
	
	/**
	 * Cette méthode à chaque fin de quête change certaines propriétés du joueur 
	 * @param joueur représente le joueur 
	 * @param pointDattaque représente les points d'attaque qu'il gagne en accomplissant
	 * la quête 
	 */
	protected void terminer(Joueur joueur,int pointDattaque) {
		joueur.prendreObjet(recompense);
		joueur.setPointdAttaque(joueur.getPointdAttaque()+pointDattaque);
		this.status=true;
		joueur.setQuete(null);
		joueur.niveauActuel++;
	}
	public void lancerQuete(Joueur joueur, Queteur queteur) {
	
	}
	/**
	 * @param joueur le joueur
	 * @param queteur la personne qui lance la quête
	 * @return un String, phrase que le queteur dit si le joueur a déjà une quete en cours
	 */
	public String executerQuete(Joueur joueur, Queteur queteur) {
		return queteur.dialoguePendantQuete(0);
	}
	
	/**
	 * 
	 * Cette méthode renvoie le statut de la quête
	 * @return Boolean : false si n'a pas déjà était faite, sinon true 
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 *
	 * Cette méthode permet de faire perdre le joueur en initialisant ces points de vie à 0
	 * @param joueur représente le joueur 
	 */
	public void perdu(Joueur joueur) {
		joueur.setPointDeVie(0);
	}
}
