package jeu;

public class capturerMouton extends Quete {
	private int nombreMoutonRequis;
	public capturerMouton(Objets recompenseJoueur,int nbmouton) {
		super(recompenseJoueur);
		nombreMoutonRequis = nbmouton;
		// TODO Auto-generated constructor stub
	}
	

	public void lancerQuete(Joueur joueur, Queteur queteur) {
		
	}
	public int nbMouton() {
		return nombreMoutonRequis;
	}
}
