package jeu;

import java.io.File;
import java.io.IOException;
import javax.sound.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ThreadLauncher {
	public static Jeu jeu;
	private static Thread vieJoueur;
	private static Thread phaseJeu;
	public static void LancerMusique() {
		Thread t = new Thread(new Runnable(){
			public void run() {
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
					e.printStackTrace();						
					} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
		});
		t.start();
	}
	public static void checkPhaseOfGame() {
		phaseJeu = new Thread(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				boolean setFinalScene=jeu.getPartie().getZoneCourante()==jeu.getPartie().getSalleDeRepos();
				boolean setMechant = false;
				int cptNiveau = jeu.getPartie().getJoueur().niveauActuel;
					while(jeu.getPartie().getJoueur().getPointDeVie()>0) {
						while(jeu.getPartie().getJoueur().niveauActuel!=jeu.getPartie().getJoueur().niveauMaximum) {
							if(cptNiveau!=jeu.getPartie().getJoueur().niveauActuel && cptNiveau<jeu.getPartie().getJoueur().niveauMaximum-1) {
								try {
								Thread.sleep(4000);
								} catch (InterruptedException e) {
								// TODO Auto-generated catch block
									e.printStackTrace();
								}
								jeu.mentrisRemerciement(cptNiveau+2);	
								cptNiveau++;
								System.out.println(cptNiveau);
							}
						}
						if(!setFinalScene && jeu.getPartie().getZoneCourante()!=jeu.getPartie().getSceneFinal()) {
							jeu.lancerPhaseFinale();
							setFinalScene=true;
						}
						if(jeu.getPartie().getZoneCourante()==jeu.getPartie().getSceneFinal()) {
							if(!setMechant) {
								jeu.lancerCombat();
								jeu.apparitionMechant(true);
								setMechant = true;
							}
						}
					}
					jeu.afficherScenePerdante();
				}
		});
		phaseJeu.start();
	}
	public static void makeMoveMouton() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				capturerMouton quete = (capturerMouton) jeu.getPartie().queteEnCoursPartie();
				int nbMoutonToPlace=0;
				do {
					nbMoutonToPlace = quete.nbMouton()-jeu.getPartie().getJoueur().nbMouton();
					WorldBuilder.reInitSheep(jeu.getPartie().zones());
					System.out.println("mdrjsuisla");
					WorldBuilder.positionnerMouton(jeu.getPartie().zones(),nbMoutonToPlace);	
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
	public static void checkLifeJoueur() {
		vieJoueur = new Thread(new Runnable() {
			public void run() {
			while(jeu.getPartie().getJoueur().getPointDeVie()>0) {
				
			}
			jeu.afficherScenePerdante();
			}
		});
		vieJoueur.start();
	}
}
