package jeu;

import java.io.Serializable;

public class Objets implements Serializable{
	
	private String Nom;
	private String Description;
	private String NomImage;
	
	/**  Getter qui permet d'obtenir le nom de l'image associé à l'objet
	 * @return String
	 */
	public String getNomImage() {
		return NomImage;
	}
	/** Getter qui permet d'obtenir le nom de l'objet
	 * @return String
	 */
	public String Nom() {
		return Nom;
	}

//	public Objets()
//	{
//		
//	}
	
	/** Constructeur de l'objet qui permet d'instancier ses propriétés
	 * @param nom
	 * @param description
	 * @param nomImage
	 */
	public Objets(String nom, String description, String nomImage) {
		this.Nom = nom;
		this.Description = description;
		this.NomImage = nomImage;
	}
	
	/** Getter permettant d'obtenir la description de l'objet
	 * @return String
	 */
	public String getDescription() {
		return this.Description;
	}
	
}