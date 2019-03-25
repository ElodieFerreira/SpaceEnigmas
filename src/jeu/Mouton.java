package jeu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
public class Mouton extends Objets  {
	private static final long serialVersionUID = 2934790090291592942L;

	/**
	 * Constructeur du mouton, qui initialise son nom, sa description et son image
	 */
	public Mouton() {
		super("Mouton", "Mouton du berger", "mouton.gif");
	}
	/**
	 * @return String : le nom de l'image
	 */
	public String getImage() {
		return super.getNomImage();
	}
	
}
