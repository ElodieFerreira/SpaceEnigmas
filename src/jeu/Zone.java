package jeu;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Zone implements Serializable
{
	private static final long serialVersionUID = 6409281714245147128L;
	private String nom;
    private String description;
    private String nomImage;
    private HashMap<String,Zone> sorties;   
    private ArrayList<Mouton> animauxDansLazone;
    private ArrayList<Personnage> personnageDansLaZone;
   
	/** Constructeur de la zone
	 * @param nomZone
	 * @param image
	 * @param descriptionZone
	 */
	public Zone(String nomZone, String image, String descriptionZone) {
        this.nom = nomZone;
        description = descriptionZone;
        nomImage = image;
        sorties = new HashMap<>();
        animauxDansLazone = new ArrayList<Mouton>();
        personnageDansLaZone = new ArrayList<Personnage>();
    }
    /** Setter permettant d'ajouter des moutons à la zone
     * @param mouton
     */
    public void setArrayListMouton(ArrayList<Mouton> mouton) {
    	animauxDansLazone = mouton;
    }
    /** Getter permettant d'obtenir le nom de la zone 
     * @return String
     */
    public String getNom() {
		return nom;
	}

	
    /** Methode permettant d'ajouter une sortie à une zone
     * @param sortie
     * @param zoneVoisine
     */
    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        sorties.put(sortie.name(), zoneVoisine);
    }
    /** Getter permettant d'obtenir les sortie
     * @return hashMap de string et zone
     */
    public HashMap<String,Zone> getSorties() {
    	return sorties;
    }

    /** Getter permettant d'obtenir le nom de l'image de la zone
     * @return String
     */
    public String nomImage() {
        return nomImage;
    }
     
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	// redéfinition de  la métjode toString()
        return nom;
    }

    /** Getter permettant d'obtenir la description de la zone
     * @return String
     */
    public String descriptionLongue()  {
        return description;
    }

    /** Permet d'obtenir le nom de la zone grace à sa clé
     * @return String 
     */
    private String sorties() {
        return sorties.keySet().toString();
    }

    /** Permet d'obtenir les différentes sorties d'une zone
     * @param direction
     * @return zone
     */
    public Zone obtientSortie(String direction) {
    	return sorties.get(direction);
    }
	/** Getter permettant d'obtenir la liste des animaux dans la zone
	 * @return ArrayList de mouton
	 */
	public ArrayList<Mouton> getAnimauxDansLazone() {
		return animauxDansLazone;
	}
	/** Getter permettant d'obtenir la liste des personnages dans la zone 
	 * @return ArrayList de Personnage
	 */
	public ArrayList<Personnage> getPersonnageDansLaZone() {
		return personnageDansLaZone;
	}

	/** Getter permettant d'obtenir la descritpion de la zone
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/** Getter permettant d'obtenir le nom de l'image de la zone
	 * @return String
	 */
	public String getNomImage() {
		return nomImage;
	}
	/** Setter permettant d'ajouter les personnages d'une zone
	 * @param personnageToAdd
	 */
	public void setAllPersonnage(ArrayList<Personnage> personnageToAdd) {
		personnageDansLaZone.addAll(personnageToAdd);
	}
}

