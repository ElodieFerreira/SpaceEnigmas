package jeu;

public class Personnage {
	private String nom;
	private String description;
	private String image;
	
	public Personnage(String nomPerso, String descriptionPerso,String imagePerso) {
		nom = nomPerso;
		description = descriptionPerso;
		image = imagePerso;
	}

	public String getImage() {
		return image;
	}
	public String parler() {
		return "hmm, je ne sais pas quoi dire...";
	}
}
