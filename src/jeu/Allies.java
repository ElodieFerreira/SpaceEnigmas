package jeu;

public class Allies extends Personnage {
	private Integer pointDeVie;
	private Integer pointDePouvoir;
	private Role statut;
	private String dialogue;

	public Allies(String nomPerso, String descriptionPerso, String imagePerso, Integer pointDeVie, Integer pointDePouvoir,
			Role statut, String dialogue) {
		super(nomPerso, descriptionPerso, imagePerso);
		this.pointDeVie = pointDeVie;
		this.pointDePouvoir = pointDePouvoir;
		this.statut = statut;
		this.dialogue = dialogue;
	}

	public void lancerAttaque() {
	}
}
