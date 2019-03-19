package jeu;

import java.util.ArrayList;

public class Teleporteur extends Objets {
	private int nombreDeTeleportation;
	private int nombreDeTeleportationMax;
	public Teleporteur(String nom, String description, String nomImage,int nombre) {
		super(nom, description, nomImage);
		nombreDeTeleportation=0;
		nombreDeTeleportationMax=3;
		// TODO Auto-generated constructor stub
	}
	public Zone teleportation(ArrayList<Planete> planetes) {
		nombreDeTeleportation++;
		if(nombreDeTeleportation<=nombreDeTeleportationMax) {
			System.out.println("test");
			int idPlanete = (int) ((int) 0+(Math.random()*(3-0)+1));
			System.out.println(idPlanete);
			int idZone = (int) ((int) 0+(Math.random()*(2-0)+1));
			System.out.println(idZone);
			return planetes.get(idPlanete).getZone(idZone);
		} else {
			return null;
		}
	}
}
