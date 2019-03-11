package jeu;

import java.util.ArrayList;

public class Partie {
	private Joueur joueur;
	/**
	 * Définit par un int la phase de jeu actuel. 0 pour le début ( présentation de Mentris... ) 
	 * 1 lorsque l'utilisateur fait ses quêtes
	 * 2 est la dernière phase.
	 */
	private int phaseDeJeu;
	// implémentation par la suite de la zone courante ici;
	/**
	 * L'état de la carte actuellement avec les objets et les personnages dessus
	 */
	private ArrayList<Planete> carteActuel;
	private Zone salleDeRepos;

	public void setSalleDeRepos(Zone salleDeReposArg) {
		salleDeRepos = salleDeReposArg;
	}
	public Partie() {
		joueur = null;
		carteActuel = new ArrayList<Planete>();
		salleDeRepos = new Zone("ezez","ezez","éa");
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
}
