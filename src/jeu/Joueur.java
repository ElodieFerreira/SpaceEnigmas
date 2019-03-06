package jeu;

import java.util.ArrayList;
import java.util.HashMap;

public class Joueur {
	private String nom;
	private HashMap<String,Objets> inventaires;
	private Integer nombreDeTeleportation;
	private Integer nombreDeTeleportationMax;
	private Integer pointdAttaque;
	private Integer pointDeVie;
	
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	
}
