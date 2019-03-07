package jeu;

public class Allies extends Personnage {
	private int pointDeVie;
	private int pointDePouvoir;
	private Role statut;
	public Allies(String nomPerso, String descriptionPerso, String imagePerso,String statut, int pointVie,int pointPouvoir) {
		super(nomPerso, descriptionPerso, imagePerso);
		pointDeVie = pointVie;
		pointDePouvoir = pointPouvoir;
	}

}
