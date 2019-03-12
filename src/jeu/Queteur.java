package jeu;

import java.util.HashMap;

public class Queteur extends Personnage {
	private HashMap<Mouton,Object> inventaire;
	private String queteDejaEnCours;
	private String dialogueLancementQuete;
	public Queteur(String nomPerso, String descriptionPerso, String imagePerso) {
		super(nomPerso, descriptionPerso, imagePerso);
		// TODO Auto-generated constructor stub
	}
	public HashMap<Mouton, Object> getInventaire() {
		return inventaire;
	}
}
