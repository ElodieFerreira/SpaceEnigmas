package jeu;

public class vieDuJoueur implements Runnable {
	private Joueur joueur;
	private Jeu jeu;
	public void run() {
		// TODO Auto-generated method stub
		while(joueur.getPointDeVie()>0) {
//			System.out.println("je suis pas mort");
		}
//		System.out.println("je suis mort");
		jeu.afficherScenePerdante();
	}
	public vieDuJoueur(Joueur joueur, Jeu jeu) {
		super();
		this.joueur = joueur;
		this.jeu = jeu;
	}

}
