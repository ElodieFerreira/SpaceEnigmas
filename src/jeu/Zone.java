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
//    private ArrayList<Mouton> animauxDansLazone;
//    private ArrayList<Personnage> personnageDansLaZone;

    public Zone(String nomZone, String image, String descriptionZone) {
        this.nom = nomZone;
        description = descriptionZone;
        nomImage = image;
        sorties = new HashMap<>();
    }
    public static ArrayList<Zone> creerToutesLesZones(ArrayList<Zone> toutesLesZones) {
    	ReaderXML spaceReader = new ReaderXML("espace1.xml");
        NodeList zones = spaceReader.getDocument().getElementsByTagName("zone");
        for(int i=0;i<zones.getLength();i++) {
        	Element zone = (Element) zones.item(i);
        	String nomZone = zone.getElementsByTagName("nom").item(0).getTextContent();
        	String descriptionZone = zone.getElementsByTagName("description").item(0).getTextContent();
        	String nomImage = zone.getElementsByTagName("image").item(0).getTextContent();
        	Integer indexZone = Integer.valueOf(zone.getAttribute("index"));
        	System.out.println(indexZone);
        	toutesLesZones.add(indexZone,new Zone(nomZone,nomImage,descriptionZone));
        }
    	return toutesLesZones;
    }
    public static ArrayList<Zone> ajouterToutesLesSorties(ArrayList<Zone> toutesLesZones) {
    	ReaderXML spaceReader = new ReaderXML("espace1.xml");
        NodeList zones = spaceReader.getDocument().getElementsByTagName("zone");
        for(int i=0;i<zones.getLength();i++) {
        	Element zone = (Element) zones.item(i);
        	Integer indexZoneDeDepart = Integer.valueOf(zone.getAttribute("index"));
        	NodeList sorties = zone.getElementsByTagName("sortie");
        	for(int j=0;j<sorties.getLength();j++) {
        		Element sortie = (Element)sorties.item(j);
        		String directionSortie = sortie.getTextContent();
        		Integer indexZoneSortie = Integer.valueOf(sortie.getAttribute("index"));
        		toutesLesZones.get(indexZoneDeDepart).ajouteSortie(Sortie.valueOf(directionSortie),toutesLesZones.get(indexZoneSortie));
        	}
        }
    	return toutesLesZones;
    }
    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        sorties.put(sortie.name(), zoneVoisine);
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
}

