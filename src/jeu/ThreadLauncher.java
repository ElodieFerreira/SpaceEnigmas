package jeu;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ThreadLauncher {
	public static Jeu jeu;
	public static void LancerMusique() {
		Thread t = new Thread(new Runnable(){
			public void run() {
					// TODO Auto-generated method stub
				while(true) {
					try {
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/music/dust.wav"));
						clip.open(inputStream);
						clip.start();
						Thread.sleep(clip.getMicrosecondLength());
					} catch (UnsupportedAudioFileException e) {
							// TODO Auto-generated catch block							e.printStackTrace();
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();						} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}
	public static void checkPhaseOfGame() {
		Thread t = new Thread(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				boolean setFinalScene=false;
				boolean setMechant = false;
				int cptNiveau = jeu.getPartie().getJoueur().niveauActuel;
				while(jeu.getPartie().getJoueur().getPointDeVie()>0) {
					while(jeu.getPartie().getJoueur().niveauActuel!=jeu.getPartie().getJoueur().niveauMaximum) {
						if(cptNiveau!=jeu.getPartie().getJoueur().niveauActuel && cptNiveau<jeu.getPartie().getJoueur().niveauMaximum-1) {
							try {
								Thread.sleep(7000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							jeu.mentrisRemerciement(cptNiveau+2);	
							cptNiveau++;
							System.out.println(cptNiveau);
						}
					}
					if(!setFinalScene) {
						jeu.lancerPhaseFinale();
						setFinalScene=true;
					}
					while(jeu.getPartie().getZoneCourante()!=jeu.getPartie().getSceneFinal()) {
						jeu.apparitionMechant();
					}
					if(!setMechant) {
						System.out.println("j'aichangerlesListener");
						jeu.lancerCombat();
						setMechant = true;
					}
				}
//				System.out.println("je suis mort");
				jeu.afficherScenePerdante();
			}
		});
		t.start();
	}
	public static void makeMoveMouton() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				capturerMouton quete = (capturerMouton) jeu.getPartie().queteEnCoursPartie();
				int nbMoutonToPlace=0;
				do {
					nbMoutonToPlace = quete.nbMouton()-jeu.getPartie().getJoueur().nbMouton();
					WorldBuilder r = new WorldBuilder();
					r.retirerMouton(jeu.getPartie().zones());
					System.out.println("mdrjsuisla");
					WorldBuilder.positionMouton(jeu.getPartie().zones(),nbMoutonToPlace);	
					try {
						Thread.sleep(7000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while(nbMoutonToPlace>0);
				System.out.println("srop");
			}
		});
		t.start();
	}
}
