package jeu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Joueur implements Serializable {
	private static final long serialVersionUID = 7414382947582054818L;
	public String nom;
	public ArrayList<Objets> inventaire;
	private Integer pointdAttaque;
	private Integer pointDeVie;
	public HashSet friends;
	/**
	 * Compte le nombre de qu�te r�alis� par le joueur jusqu'� pr�sent.
	 */
	private int nbQueterealise;
	public Joueur(String nom) {
		this.nom = nom;
		inventaire = new ArrayList<Objets>();
		friends = new HashSet();
		
	}
	public void prendreObjet(Objets A)
	{
		inventaire.add(A);
		System.out.println();
	}
	public Objets donnerObjet(Objets obj)
	{	
		inventaire.remove(obj);
		return obj;	
	}
	public Mouton recupererMouton() {
		for(Objets obj : inventaire) {
			if(obj instanceof Mouton) {
				return (Mouton) obj;
			}
		}
		return null;
	}
	public String getNom()
	{
		return this.nom;
	}
	public void setNom(String newName)
	{
		this.nom=newName;
	}
	
}
