package jeu;

import java.util.HashMap;

public class Quêteur extends Personnage {
	private HashMap<Mouton,Object> inventaire;
	private String queteDejaEnCours;
	private String dialogueLancementQuête;
	public Quêteur(String nomPerso, String descriptionPerso, String imagePerso) {
		super(nomPerso, descriptionPerso, imagePerso);
		// TODO Auto-generated constructor stub
	}
	public HashMap<Mouton, Object> getInventaire() {
		return inventaire;
	}
}
