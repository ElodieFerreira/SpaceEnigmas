package jeu;

import java.util.HashMap;

public class Qu�teur extends Personnage {
	private HashMap<Mouton,Object> inventaire;
	private String queteDejaEnCours;
	private String dialogueLancementQu�te;
	public Qu�teur(String nomPerso, String descriptionPerso, String imagePerso) {
		super(nomPerso, descriptionPerso, imagePerso);
		// TODO Auto-generated constructor stub
	}
	public HashMap<Mouton, Object> getInventaire() {
		return inventaire;
	}
}
