package jeu;

import java.io.Serializable;
import java.util.ArrayList;

public class Partie implements Serializable {
	private static final long serialVersionUID = -5799011054091607425L;
	private Joueur joueur;
	/**
	 * Définit par un int la phase de jeu actuel. 0 pour le d�but ( pr�sentation de Mentris... ) 
	 * 1 lorsque l'utilisateur fait ses qu�tes
	 * 2 est la derniére phase.
	 */
	private int phaseDeJeu;
	// implémentation par la suite de la zone courante ici;
	/**
	 * L'état de la carte actuellement avec les objets et les personnages dessus
	 */
	private ArrayList<Planete> carteActuel;
	private Zone salleDeRepos;

	public void setSalleDeRepos(Zone salleDeReposArg) {
		this.salleDeRepos = salleDeReposArg;
	}
	public Partie() {
		joueur = null;
		carteActuel = new ArrayList<Planete>();
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur newJoueur) {
		joueur=newJoueur;
	}
	public Zone getSalleDeRepos() {
		return salleDeRepos;
	}
	
	public void setQuete(Quete quete) {
		joueur.setQuete(quete);
	}
	public ArrayList<Planete> carteActuel() {
		return carteActuel;
	}
	public ArrayList<Zone> zones() {
		ArrayList<Zone> zones = ArrayList<Zone>();
		for(Planete pl : carteActuel) {
			zones.addAll(pl.getZones());
		}
		return zones;		
	}
	public Quete queteEnCoursPartie() {
		return joueur.quete();
	}
}
