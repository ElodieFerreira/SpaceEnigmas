package jeu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Planete implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5073736559358207072L;
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
	public synchronized ArrayList<Zone> getZones() {
		return cartePlanete;
	}

	public String getNom() {
		return this.nom;
	}
	public String getDescription() {
		return this.description;
	}
	public Zone getZone(int index) {
		return cartePlanete.get(index);
	}
}
