package jeu;
public class Objets {
	
	private String Nom;
	private String Description;
	private String NomImage;
	
	public String getNomImage() {
		return NomImage;
	}
	public String Nom() {
		return Nom;
	}

	public Objets()
	{
		
	}
	
	public Objets(String nom, String description, String nomImage) {
		this.Nom = nom;
		this.Description = description;
		this.NomImage = nomImage;
	}
	
}