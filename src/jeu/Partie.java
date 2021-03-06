package jeu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe est celle qui contiendra les informations à sauvegarder
 *
 */
public class Partie implements Serializable {
	private static final long serialVersionUID = -5799011054091607425L;
	private Joueur joueur;
	private Zone zoneCourante;
	private Queteur guideDuJeu;
	public int nbCommandeMax;
	public int nbCommande;
	private Zone vaisseau;
	private PersonnageActifs dyspros;
	// implémentation par la suite de la zone courante ici;
	/**
	 * L'état de la carte actuellement avec les objets et les personnages dessus
	 */
	private ArrayList<Planete> carteActuel;
	private Zone salleDeRepos;
	private Zone sceneFinal;
	
	/** Setter de la sceneFinal
	 * @param zone
	 */
	public void setsceneFinal(Zone zone) {
		sceneFinal = zone;
	}
	/** Renvoie le méchant Dyspros
	 * @return PersonnageActifs
	 */
	public PersonnageActifs dyspros() {
		return dyspros;
	}
	/** Setter de la salle de repos
	 * @param  salleDeReposArg Zone
	 */
	public void setSalleDeRepos(Zone salleDeReposArg) {
		this.salleDeRepos = salleDeReposArg;
	}
	/**
	 * Constructeur de la partie
	 */
	public Partie() {
		joueur = null;
		carteActuel = new ArrayList<Planete>();
		nbCommande = 0;
		nbCommandeMax = 200;
		dyspros = new PersonnageActifs("Dyspros","","Legrandmechant.png",350,3,Role.valueOf("FIGHTER"),"ola");
	}
	/** Getter de joueur
	 * @return Joueur
	 */
	public Joueur getJoueur() {
		return joueur;
	}
	/** Setter de joueur
	 * @param  newJoueur Joueur
	 */
	public void setJoueur(Joueur newJoueur) {
		joueur=newJoueur;
	}
	/** Getter salleDeRepos
	 * @return Zone salleDeRepos
	 */
	public Zone getSalleDeRepos() {
		return salleDeRepos;
	}
	/**Setter queteEnCours
	 * @param  quete Quete
	 */
	public void setQuete(Quete quete) {
		joueur.setQuete(quete);
	}
	/** Getter de la carteActuek
	 * @return carteActuel ArrayList de planete 
	 */
	public ArrayList<Planete> carteActuel() {
		return carteActuel;
	}
	/**Retourne toutes les zones de l'espace
	 * @return les zones des planètes grâce à un ArrayList de Zone
	 */
	public ArrayList<Zone> zones() {
		ArrayList<Zone> zones = new ArrayList<Zone>();
		// Parcours 
		for(Planete pl : carteActuel) {
			zones.addAll(pl.getZones());
		}
		return zones;		
	}
	/** Retourne la quête en cours
	 * @return Quete
	 */
	public Quete queteEnCoursPartie() {
		return joueur.quete();
	}
	/** Setter de l'espace
	 * @param  planetes ArrayList de Planete
	 */
	public void setEspace(ArrayList<Planete> planetes) {
		carteActuel= planetes;
	} 
	/** Getter zoneCourante
	 * @return Zone zoneCourante
	 */
	public Zone getZoneCourante()
	{
		return zoneCourante;
	}
	/** Setter zoneCourante
	 * @param  newzone Zone
	 */
	public void setZoneCourante(Zone newzone)
	{
		zoneCourante = newzone;
	}
	/** Getter sceneFinal
	 * @return Zone sceneFinal
	 */
	public Zone getSceneFinal() {
		return sceneFinal;
	}
	/** Getter guideDuJeu
	 * @return Queteur guideDuJeu
	 */
	public Queteur getGuideDuJeu() {
		return guideDuJeu;
	}
	/**Setter guideDuJeu
	 * @param guideDuJeu Queteur 
	 */
	public void setGuideDuJeu(Queteur guideDuJeu) {
		this.guideDuJeu = guideDuJeu;
	}
	/**Getter la zone vaisseau
	 * @return Zone vaisseau
	 */
	public Zone getVaisseau() {
		return vaisseau;
	}
	/**Setter de la zone du vaisseau
	 * @param newVaisseau Zone 
	 */
	public void setVaisseau(Zone newVaisseau) {
		vaisseau = newVaisseau;
	}	 
}
