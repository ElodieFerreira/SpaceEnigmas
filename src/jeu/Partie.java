package jeu;

import java.util.ArrayList;

public class Partie {
	private Joueur joueur;
	/**
	 * D�finit par un int la phase de jeu actuel. 0 pour le d�but ( pr�sentation de Mentris... ) 
	 * 1 lorsque l'utilisateur fait ses qu�tes
	 * 2 est la derni�re phase.
	 */
	private int phaseDeJeu;
	// impl�mentation par la suite de la zone courante ici;
	/**
	 * L'�tat de la carte actuellement avec les objets et les personnages dessus
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
}
