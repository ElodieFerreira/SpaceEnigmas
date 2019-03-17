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
   
	public Zone(String nomZone, String image, String descriptionZone) {
        this.nom = nomZone;
        description = descriptionZone;
        nomImage = image;
        sorties = new HashMap<>();
        animauxDansLazone = new ArrayList<Mouton>();
        personnageDansLaZone = new ArrayList<Personnage>();
    }
    public void setArrayListMouton(ArrayList<Mouton> mouton) {
    	animauxDansLazone = mouton;
    }
    public String getNom() {
		return nom;
	}

	
    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        sorties.put(sortie.name(), zoneVoisine);
    }
    public HashMap<String,Zone> getSorties() {
    	return sorties;
    }

    public String nomImage() {
        return nomImage;
    }
     
    public String toString() {
        return nom;
    }

    public String descriptionLongue()  {
        return description;
    }

    private String sorties() {
        return sorties.keySet().toString();
    }

    public Zone obtientSortie(String direction) {
    	return sorties.get(direction);
    }
	public ArrayList<Mouton> getAnimauxDansLazone() {
		return animauxDansLazone;
	}
	public ArrayList<Personnage> getPersonnageDansLaZone() {
		return personnageDansLaZone;
	}

	public String getDescription() {
		return description;
	}

	public String getNomImage() {
		return nomImage;
	}
	public void setAllPersonnage(ArrayList<Personnage> personnageToAdd) {
		personnageDansLaZone.addAll(personnageToAdd);
	}
}

