package jeu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
public class Mouton extends Objets  {
	private static final long serialVersionUID = 2934790090291592942L;

	public Mouton() {
		super("Mouton", "Mouton du berger", "mouton.gif");
	}
	public String getImage() {
		return super.getNomImage();
	}
	
}
