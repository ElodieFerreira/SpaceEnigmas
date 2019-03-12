package jeu;

public class capturerMouton extends Quete {
	public capturerMouton(Objets recompenseJoueur) {
		super(recompenseJoueur);
		// TODO Auto-generated constructor stub
	}

	private int nombreMoutonRequis;

	public void lancerQuete(Joueur joueur, Queteur queteur) {
		while(!status) {
			if(queteur.getInventaire().get(0)!=null) {
				Mouton mouton = (Mouton) queteur.getInventaire().get(0);
				if(mouton.Nom().indexOf("mouton")!=-1) { 
					super.terminer(joueur);
				}
			}
		}
	}
}
