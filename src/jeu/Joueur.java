package jeu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Joueur implements Serializable {
	private static final long serialVersionUID = 7414382947582054818L;
	public String nom;
	public ArrayList<Objets> inventaire;
	private Integer pointdAttaque;
	private Integer pointDeVie;
	// HashSet est utilisé dans le but d'éviter les doublons
	public HashSet friends;
	private Quete queteEnCours;
	public int niveauMaximum;
	public int niveauActuel;
	/**
	 * Compte le nombre de quête rêalisé par le joueur jusqu'à présent.
	 */
	private int nbQueterealise;
	/** Construit le joueur en lui initialisant ses pvs et ses point de pouvoir
	 * @param nom est un string représentant le nom du joueur
	 */
	public Joueur(String nom) {
		this.nom = nom;
		queteEnCours = null;
		inventaire = new ArrayList<Objets>();
		friends = new HashSet();
		niveauMaximum = 3;
		niveauActuel = 0;
		pointDeVie = 20;
		pointdAttaque = 5;
	}	
	/** Getteur des points d'attaque
	 * @return Integer point d'attaque
	 */
	public Integer getPointdAttaque() {
		return pointdAttaque;
	}
	/** Setter point d'attaque
	 * @param  pointdAttaque Integer
	 */
	public void setPointdAttaque(Integer pointdAttaque) {
		this.pointdAttaque = pointdAttaque;
	}
	/** Getter des points de vie du joueur
	 * @return Integer point de vie.
	 */
	public Integer getPointDeVie() {
		return pointDeVie;
	}
	/** Setter point de vie
	 * @param  pointDeVie Integer
	 */
	public void setPointDeVie(Integer pointDeVie) {
		this.pointDeVie = pointDeVie;
	}
	/** Getter niveau max du joueur
	 * @return int niveauMaximum
	 */
	public int getNiveauMaximum() {
		return niveauMaximum;
	}
	/** Setter niveauMaximum
	 * @param  niveauMaximum int
	 */
	public void setNiveauMaximum(int niveauMaximum) {
		this.niveauMaximum = niveauMaximum;
	}
	/** Getter niveauActuel
	 * @return int niveauActuel
	 */
	public int getNiveauActuel() {
		return niveauActuel;
	}
	/** Setter niveauActuel
	 * @param  niveauActuel int
	 */
	public void setNiveauActuel(int niveauActuel) {
		this.niveauActuel = niveauActuel;
	}
	/** Le joueur prends un objet et le met dans son inventaire
	 * @param  obj Objet
	 */
	public void prendreObjet(Objets obj)
	{	
		inventaire.add(obj);
	}
	/** Enlève un objet de l'inventaire du joueur pour le donner
	 * @param  obj Objet
	 * @return Objet obj
	 */
	public Objets donnerObjet(Objets obj)
	{	
		inventaire.remove(obj);
		return obj;	
	}
	/** Récupère un des moutons présents dans l'inventaire
	 * @return Mouton mouton
	 */
	public Mouton recupererMouton() {
		for(Objets obj : inventaire) {
			if(obj instanceof Mouton) {
				return (Mouton) obj;
			}
		}
		return null;
	}
	/** Getter nom
	 * @return String nom
	 */
	public String getNom()
	{
		return this.nom;
	}
	/** Setter nom
	 * @param  newName String
	 */
	public void setNom(String newName)
	{
		this.nom=newName;
	}
	/** Getter quete en cours
	 * @return Quete queteEnCours
	 */
	public Quete quete() {
		return queteEnCours;
	}
	/** Setter queteEnCours
	 * @param  quete Quete
	 */
	public void setQuete(Quete quete) {
		this.queteEnCours=quete;
	}
	/** Compte le nombre de mouton présent dans l'inventaire
	 * @return int nombreDeMouton
	 */
	public int nbMouton() {
		int cpt=0;
		for(Objets obj : inventaire) {
			if(obj instanceof Mouton) {
				cpt++;
			}
		}
		return cpt;
	}
	/** Permet au joueur d'attaquer un personnage passé en paramètre
	 * @param  personnage Personnage
	 */
	public void attaquer(PersonnageActifs personnage) {
		personnage.setPointDeVie(personnage.getPointDeVie()-pointdAttaque);
	}
}
