package jeu;

import java.io.Serializable;

public class Objets implements Serializable{
	
	private String Nom;
	private String Description;
	private String NomImage;
	
	public String getNomImage() {
		return NomImage;
	}
	public String Nom() {
		return Nom;
	}

//	public Objets()
//	{
//		
//	}
	
	public Objets(String nom, String description, String nomImage) {
		this.Nom = nom;
		this.Description = description;
		this.NomImage = nomImage;
	}
	public String getDescription() {
		return this.Description;
	}
	
}