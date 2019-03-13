package jeu;

import java.io.Serializable;

public class capturerMouton extends Quete implements Serializable {
	private static final long serialVersionUID = 7412067131135157257L;
	private int nombreMoutonRequis;
	public capturerMouton(Objets recompenseJoueur,int nbmouton) {
		super(recompenseJoueur);
		nombreMoutonRequis = nbmouton;
	}
	

	public void lancerQuete(Joueur joueur, Queteur queteur) {
		
	}
	public int nbMouton() {
		return nombreMoutonRequis;
	}
}
