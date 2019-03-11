package jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Joueur {
	private String nom;
	public ArrayList<Objets> inventaire;
	private Integer pointdAttaque;
	private Integer pointDeVie;
	public HashSet allies;
	
	public Joueur(String nom) {
		this.nom = nom;
		inventaire = new ArrayList<Objets>();
		allies = new HashSet();
		
	}
	public void prendreObjet(Objets A)
	{
		inventaire.add(A);
		System.out.println();
	}
	public Objets donnerObjet(int index)
	{	
		Objets obj =inventaire.get(index);
		inventaire.remove(index);
		return obj;	
	}
	
	
}
