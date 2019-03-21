package jeu;

import java.io.Serializable;

public class Personnage implements Serializable{
	private static final long serialVersionUID = -2531684055147232247L;
	private String nom;
	private String description;
	private String image;
	
	public Personnage(String nomPerso, String descriptionPerso,String imagePerso) {
		nom = nomPerso;
		description = descriptionPerso;
		image = imagePerso;
	}
	public void setImage(String imageName) {
		image = imageName;
	}
	public String getImage() {
		return image;
	}
	public String getNom() {
		return nom;
	}
	public String parler() {
		return "hmm, je ne sais pas quoi dire...";
	}
	
}

