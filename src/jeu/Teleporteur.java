package jeu;

public class Teleporteur extends Objets {
	private int nombreDeTeleportation;
	private int nombreDeTeleportationMax;
	public Teleporteur(String nom, String description, String nomImage,int nombre) {
		super(nom, description, nomImage);
		nombreDeTeleportation=0;
		nombreDeTeleportationMax=3;
		// TODO Auto-generated constructor stub
	}
	public Zone teleportation(Planete planete) {
		nombreDeTeleportation++;
		int idZone = (int) ((int) 0+(Math.random()*(3-0)+1);
		return planete.get(idZone);
	}
}
