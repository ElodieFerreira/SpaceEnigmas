package jeu;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Zone implements Serializable
{
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
        return "Vous êtes dans " + nom + "\nSorties : " + sorties();
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
	
}

