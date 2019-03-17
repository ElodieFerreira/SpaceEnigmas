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

	public String parler() {
		return dialogue;
	}

	public Integer getPointDeVie() {
		return pointDeVie;
	}

	public void setPointDeVie(Integer pointDeVie) {
		this.pointDeVie = pointDeVie;
	}

	public Integer getPointDePouvoir() {
		return pointDePouvoir;
	}

	public void setPointDePouvoir(Integer pointDePouvoir) {
		this.pointDePouvoir = pointDePouvoir;
	}

	public Role getStatut() {
		return statut;
	}
	public void lancerPouvoir(Allies personnage,Joueur joueur) {
		if(statut == Role.FIGHTER) {
			personnage.setPointDeVie(personnage.getPointDeVie()-pointDePouvoir);
		} else {
			joueur.setPointDeVie(joueur.getPointDeVie()+pointDePouvoir);
		}
	}
	public void attaquerJoueur(Allies personnage,Joueur joueur) {
		if(statut == Role.FIGHTER) {
			personnage.setPointDeVie(personnage.getPointDeVie()-pointDePouvoir);
			joueur.setPointDeVie(joueur.getPointDeVie()-pointDePouvoir);
		}
	}
}
