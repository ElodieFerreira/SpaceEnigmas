package jeu;

import java.io.Serializable;

/**
 * Classe mère de tous les personnages disponibles dans le jeu
 *
 */
public class Personnage implements Serializable{
	private static final long serialVersionUID = -2531684055147232247L;
	private String nom;
	private String description;
	private String image;
	
	/** Constructeur personnage 
	 * @param nomPerso
	 * @param descriptionPerso
	 * @param imagePerso
	 */
	public Personnage(String nomPerso, String descriptionPerso,String imagePerso) {
		nom = nomPerso;
		description = descriptionPerso;
		image = imagePerso;
	}
	/**Setter de l'image qui prends en paramètre le nom de l'image
	 * @param imageName
	 */
	public void setImage(String imageName) {
		image = imageName;
	}
	/** Getter de l'image
	 * @return String nomImage
	 */
	public String getImage() {
		return image;
	}
	/** Getter de nom
	 * @return String nom
	 */
	public String getNom() {
		return nom;
	}
	/** Méthode pour faire parler un personnage de base qui n'a pas de dialogue
	 * @return String dialogue par défaut
	 */
	public String parler() {
		return "hmm, je ne sais pas quoi dire...";
	}
}

