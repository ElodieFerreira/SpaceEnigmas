
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
	/* (non-Javadoc)
	 * @see jeu.Quete#executerQuete(jeu.Joueur, jeu.Queteur)
	 */
	@Override
	// Redéfinition de la méthode exécuter quete
	public String executerQuete(Joueur joueur, Queteur queteur) {
		super.executerQuete(joueur, queteur);
		System.out.println("Check :"+verifierCaptureMouton(joueur));
		// Permet de récupérer un mouton
		if(!verifierCaptureMouton(joueur)) {
			return queteur.dialoguePendantQuete(2);
		}
		// Donner l'objet à savoir le mouton au queteur
		queteur.prendre(joueur.donnerObjet(joueur.recupererMouton()));
		nombreMoutonRecu++;
		//S'il manque un mouton
		if(nombreMoutonRecu<nombreMoutonRequis) {
			int manqueMouton = nombreMoutonRequis-nombreMoutonRecu;
			return queteur.dialoguePendantQuete(manqueMouton-1);
		} 
		else {// tous les moutons ont été donné
			terminer(joueur,10);
			return queteur.dialogueFinQuete();
		}
	}
	/** Vérifie si le joueur détient des moutons dans son inventaire
	 * @param joueur
	 * @return boolean true si le joueur a  1 ou plus de mouton false sinon
	 */
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
