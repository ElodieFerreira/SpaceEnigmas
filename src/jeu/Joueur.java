package jeu;

import java.util.ArrayList;
import java.util.HashMap;

public class Joueur {
	private String nom;
	public ArrayList<Objets> inventaire;
	private Integer pointdAttaque;
	private Integer pointDeVie;
	
	public Joueur(String nom) {
		this.nom = nom;
		inventaire = new ArrayList<Objets>();
		
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
