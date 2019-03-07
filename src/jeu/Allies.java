package jeu;

public class Allies extends Personnage {
	private int pointDeVie;
	private int pointDePouvoir;
	private Role statut;
	public Allies(String nomPerso, String descriptionPerso, String imagePerso,String statutPerso, int pointVie,int pointPouvoir) {
		super(nomPerso, descriptionPerso, imagePerso);
		statut = Role.valueOf(statutPerso);
		pointDeVie = pointVie;
		pointDePouvoir = pointPouvoir;
	}
	public void lancerAttaque() {
		if()
	}
}
