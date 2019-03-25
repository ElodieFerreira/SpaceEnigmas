package jeu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Notre espace est constitué de planète auxquelles on peut accèder pour la plupart grâce à notre vaisseau
 *
 */
public class Planete implements Serializable{
	private static final long serialVersionUID = 5073736559358207072L;
	String nom;
	String description;
	private ArrayList<Zone> cartePlanete;
	public Planete(String nomPlanete, String descriptionPlanete) {
		this.description = descriptionPlanete;
		this.nom = nomPlanete;
		this.cartePlanete = new ArrayList<Zone>();
	}
	/** Ajoute une zone à notre planète
	 * @param Zone zoneAjoutee
	 */
	public void ajouterZone(Zone zoneAjoutee) {
		this.cartePlanete.add(zoneAjoutee);
	}
	/** Getter des zones sur la planètes
	 * @return ArrayList<Zone> les zones
	 */
	public ArrayList<Zone> getZones() {
		return cartePlanete;
	}
	/** Getter du nom
	 * @return String nom
	 */
	public String getNom() {
		return this.nom;
	}
	/** Getter de la description
	 * @return String description
	 */
	public String getDescription() {
		return this.description;
	}
	/**Retourne une zone à index particulier de la proprieté d'instance zones
	 * @param int index
	 * @return Zone zone
	 */
	public Zone getZone(int index) {
		return cartePlanete.get(index);
	}
}
