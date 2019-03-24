package jeu;

/**
 * Un personnage actif est un personnage ayant un pouvoir et des points de vie
 */
public class PersonnageActifs extends Personnage {
	private Integer pointDeVie;
	private Integer pointDePouvoir;
	/**
	 * Les healer soignent le joueur et les fighter attaque avec lui
	 */
	private Role statut;
	private String dialogue;

	/** Constructeur d'un personnage actif
	 * @param nomPerso
	 * @param descriptionPerso
	 * @param imagePerso
	 * @param pointDeVie
	 * @param pointDePouvoir
	 * @param statut
	 * @param dialogue
	 */
	public PersonnageActifs(String nomPerso, String descriptionPerso, String imagePerso, Integer pointDeVie, Integer pointDePouvoir,
			Role statut, String dialogue) {
		super(nomPerso, descriptionPerso, imagePerso);
		this.pointDeVie = pointDeVie;
		this.pointDePouvoir = pointDePouvoir;
		this.statut = statut;
		this.dialogue = dialogue;
	}
	/* (non-Javadoc)
	 * @see jeu.Personnage#parler()
	 */
	public String parler() {
		return dialogue;
	}
	/** Getter pointDeVie
	 * @return int pointDeVie
	 */
	public Integer getPointDeVie() {
		return pointDeVie;
	}
	/** Setter pointDeVie
	 * @param int pointDeVie
	 */
	public void setPointDeVie(Integer pointDeVie) {
		this.pointDeVie = pointDeVie;
	}
	/** Getter pointDePouvoir
	 * @return Integer pointDePouvoir
	 */
	public Integer getPointDePouvoir() {
		return pointDePouvoir;
	}
	/** Setter pointDePouvoir
	 * @param Integer pointDePouvoir
	 */
	public void setPointDePouvoir(Integer pointDePouvoir) {
		this.pointDePouvoir = pointDePouvoir;
	}
	/** Getter statut
	 * @return Role statut
	 */
	public Role getStatut() {
		return statut;
	}
	/** Lance le pouvoir du joueur lors d'un tour de combat
	 * @param PersonnageActifs personnage
	 * @param PersonnageActifs joueur
	 */
	public void lancerPouvoir(PersonnageActifs personnage,Joueur joueur) {
		//Vérifie quel type de pouvoir va utiliser le personnage, il le lance 
		//soit sur le joueur si c'est un Healer soit sur le méchant si c'est 
		// un fighter
		if(statut == Role.FIGHTER) {
			personnage.setPointDeVie(personnage.getPointDeVie()-pointDePouvoir);
		} else {
			joueur.setPointDeVie(joueur.getPointDeVie()+pointDePouvoir);
		}
	}
	/** Attaque joueur lors d'une réplique ainsi que celui qui l'a attaqué avec lui
	 * Utilisé par le méchant du jeu mais qui reste accessible à tous les personnages actifs
	 * pour d'éventuel nouveau scénario si les alliés peuvent devenir méchant
	 * @param PersonnageActifs personnage
	 * @param Joueur joueur
	 */
	public void attaquerJoueur(PersonnageActifs personnage,Joueur joueur) {
		if(statut == Role.FIGHTER) {
			personnage.setPointDeVie(personnage.getPointDeVie()-pointDePouvoir);
			joueur.setPointDeVie(joueur.getPointDeVie()-pointDePouvoir);
		}
	}
}
