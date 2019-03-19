package jeu;

import java.io.Serializable;

public class capturerMouton extends Quete {
	private static final long serialVersionUID = 7412067131135157257L;
	private int nombreMoutonRequis;
	private int nombreMoutonRecu;
	public capturerMouton(Objets recompenseJoueur,int nbmouton) {
		super(recompenseJoueur);
		nombreMoutonRecu = 0;
		nombreMoutonRequis = nbmouton;
	}
	

	public int nbMouton() {
		return nombreMoutonRequis-nombreMoutonRecu;
	}
	@Override
	public String executerQuete(Joueur joueur, Queteur queteur) {
		// TODO Auto-generated method stub
		super.executerQuete(joueur, queteur);
		System.out.println("Check :"+verifierCaptureMouton(joueur));
		if(!verifierCaptureMouton(joueur)) {
			return queteur.dialoguePendantQuete(2);
		}
		queteur.prendre(joueur.donnerObjet(joueur.recupererMouton()));
		nombreMoutonRecu++;
		if(nombreMoutonRecu<nombreMoutonRequis) {
			int manqueMouton = nombreMoutonRequis-nombreMoutonRecu;
			return queteur.dialoguePendantQuete(manqueMouton-1);
		} else {
			terminer(joueur,10);
			return queteur.dialogueFinQuete();
		}
	}
	public boolean verifierCaptureMouton(Joueur joueur) {
		int cptMouton =0;
		for(Objets obj : joueur.inventaire) {
			if(obj instanceof Mouton) {
				cptMouton++;
			}
		}
		System.out.println("NombreDeMouton"+cptMouton);
		return cptMouton>=1;
	}
}
