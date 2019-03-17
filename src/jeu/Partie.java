package jeu;

import java.io.Serializable;
import java.util.ArrayList;

public class Partie implements Serializable {
	private static final long serialVersionUID = -5799011054091607425L;
	private Joueur joueur;
	private Zone zoneCourante;
	private Queteur guideDuJeu;
	public int nbCommandeMax;
	public int nbCommande;
	private Zone vaisseau;
	// implémentation par la suite de la zone courante ici;
	/**
	 * L'état de la carte actuellement avec les objets et les personnages dessus
	 */
	private ArrayList<Planete> carteActuel;
	private Zone salleDeRepos;
	private Zone sceneFinal;
	
	public void setsceneFinal(Zone zone) {
		sceneFinal = zone;
	}

	public void setSalleDeRepos(Zone salleDeReposArg) {
		this.salleDeRepos = salleDeReposArg;
	}
	public Partie() {
		joueur = null;
		carteActuel = new ArrayList<Planete>();
		nbCommande = 0;
		nbCommandeMax = 100;
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
		ArrayList<Zone> zones = new ArrayList<Zone>();
		for(Planete pl : carteActuel) {
			zones.addAll(pl.getZones());
		}
		return zones;		
	}
	public Quete queteEnCoursPartie() {
		return joueur.quete();
	}
	public void setEspace(ArrayList<Planete> planetes) {
		carteActuel= planetes;
	} 
	public Zone getZoneCourante()
	{
		return zoneCourante;
	}
	public void setZoneCourante(Zone newzone)
	{
		zoneCourante = newzone;
	}
	public Zone getSceneFinal() {
		return sceneFinal;
	}

	public Queteur getGuideDuJeu() {
		return guideDuJeu;
	}

	public void setGuideDuJeu(Queteur guideDuJeu) {
		this.guideDuJeu = guideDuJeu;
	}
	public Zone getVaisseau() {
		return vaisseau;
	}
	public void setVaisseau(Zone newVaisseau) {
		vaisseau = newVaisseau;
	}
	 
}
