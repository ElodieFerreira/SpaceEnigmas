package jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Planete {
	String nom;
	String description;
	private ArrayList<Zone> cartePlanete;
	public Planete(String nomPlanete, String descriptionPlanete) {
		this.description = descriptionPlanete;
		this.nom = nomPlanete;
		this.cartePlanete = new ArrayList<Zone>();
	}
	public void ajouterZone(Zone zoneAjoutee) {
		this.cartePlanete.add(zoneAjoutee);
	}
	public ArrayList<Zone> getZones() {
		return cartePlanete;
	}

	public String getNom() {
		return this.nom;
	}
	public String getDescription() {
		return this.description;
	}
}
