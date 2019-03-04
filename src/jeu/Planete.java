package jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Planete {
	private String nom;
	private String description;
	private ArrayList<Zone> cartePlanete;
	
	public Planete(String nomPlanete, String descriptionPlanete) {
		this.description = descriptionPlanete;
		this.nom = nomPlanete;
		this.cartePlanete = new ArrayList<Zone>();
	}
	public void ajouterZone(Zone zoneAjoutee) {
		this.cartePlanete.add(zoneAjoutee);
	}
	public static ArrayList<Planete> creerLesPlanetes(ArrayList<Zone> toutesLesZones) {
		// le HashMap sera renvoyé et garder dans le jeu pour pouvoir avoir rapidement accès au planète
		// et qu'elles ne soient pas supprimer par le gc.
		ArrayList<Planete>  planetesRetour = new ArrayList<Planete>();
		ReaderXML planeteReader = new ReaderXML("espace1.xml");
        NodeList planetes = planeteReader.getDocument().getElementsByTagName("planete");
        for(int i=0;i<planetes.getLength();i++) {
        	Element planeteElement = (Element) planetes.item(i);
        	String nomPlanete = planeteElement.getElementsByTagName("nom").item(0).getTextContent();  
        	String description = planeteElement.getElementsByTagName("description").item(0).getTextContent();
        	NodeList zonesPlanete = planeteElement.getElementsByTagName("zone");
        	Planete planete = new Planete(nomPlanete, description);
        	for(int i1=0;i1<zonesPlanete.getLength();i1++) {
        		Element zone = (Element) zonesPlanete.item(0);
        		Integer indexZoneToAdd = Integer.valueOf(zone.getAttribute("index"));
        		planete.ajouterZone(toutesLesZones.get(indexZoneToAdd));
        	}
        	planetesRetour.add(new Planete(nomPlanete, description));
        }
//        for(Planete planete : planetesRetour) {
//        	 System.out.println(planete.nom);
//        	 System.out.println(planete.description);
//        }
		return planetesRetour;
	}
	public String getNom() {
		return this.nom;
	}
	public String getDescription() {
		return this.description;
	}
}
