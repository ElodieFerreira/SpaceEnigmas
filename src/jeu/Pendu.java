package jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class Pendu extends Quete{
	
	private ArrayList<String> mots;
	private int nombreDeCoups;
	private int nombreDeCoupsMax;
	private String reponse;
	private String motJeu;
	
	/** Constructor which initialize the hangman quest
	 * @param recompenseJoueur is the object that will be given to the player after the quest
	 * @param newMots represents a array of word which are the answers to the hangman
	 */
	public Pendu(Objets recompenseJoueur, ArrayList<String> newMots) {
		super(recompenseJoueur);
		// TODO Auto-generated constructor 
		nombreDeCoups = 0;
		nombreDeCoupsMax = 8;
		mots = new ArrayList<String>();
		motJeu = new String ("");
		mots.addAll(newMots);
	}
	
	/** This method check if the player wrote more than one letter
	 * @param st represents the letter that the player wrote on the keyboard
	 * @return a boolean : return true if the player wrote more than one letter else it returns false
	 */
	private boolean EstTropGrande(String st) {
		if(st.toUpperCase().length()>1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** This method check if the word written on the keyboard is contained in the answer
	 * @param rep is the word that the player wrote on the keyboard
	 * @return : a boolean which return true if the word written is contained in the answer else return false
	 */
	private boolean EstDansMot(String rep) {
		return this.reponse.contains(rep);
	}
	
	/** 
	 * @param lettre 
	 * @param id
	 */
	private void dévoileLettre(String lettre, int id) {
		int index=reponse.indexOf(lettre.toUpperCase(),id);
		System.out.println(motJeu.length());
		if(index!=-1) {
			String premièrePartie = motJeu.substring(0, index);
			String deuxièmePartie = motJeu.substring(index+1,motJeu.length());
			motJeu=premièrePartie+lettre.toUpperCase()+deuxièmePartie;
			dévoileLettre(lettre.toUpperCase(),index+1);
		}	
	}
	
	private boolean EstComplet() {
		return (motJeu.equals(reponse));
	}
		
	public String executerQuete(Joueur joueur, Queteur queteur, String str) {
		if(!EstTropGrande(str.toUpperCase())) {
			if(EstDansMot(str.toUpperCase())) {
				dévoileLettre(str.toUpperCase(),0);
				if(EstComplet()) {
					int pointPouvoirGagne = 40 - 4*nombreDeCoups;
					terminer(joueur,pointPouvoirGagne);
					return reponse+"\n"+queteur.dialogueFinQuete();
				}
				return queteur.dialoguePendantQuete(3)+"\n"+motJeu;
			}
			else {
				// Il a fait une erreur sur la lettre
				// C'est considéré comme un coup de plus : 
				nombreDeCoups++;
				if(nombreDeCoups==nombreDeCoupsMax) {
					System.out.println("morte");
					perdu(joueur);
					return "";
				} else {
					return queteur.dialoguePendantQuete(1).replaceAll(" nb",String.valueOf(nombreDeCoupsMax-nombreDeCoups))+"\n"+motJeu;
				}
			}
		}
		else  {
			if(str.toUpperCase().equals(reponse)){
				int pointPouvoirGagne = 40 - 4*nombreDeCoups;
				terminer(joueur,pointPouvoirGagne);
				return reponse+"\n"+queteur.dialogueFinQuete();
			}
			else {
				nombreDeCoups++;
				if(nombreDeCoups==nombreDeCoupsMax) {	
					perdu(joueur);
					return "";
				}
				return queteur.dialoguePendantQuete(2).replaceAll(" nb",String.valueOf(nombreDeCoupsMax-nombreDeCoups))+"\n"+motJeu;
			}
		}
	}
	public void lancerQuete(Joueur joueur, Queteur queteur) {
		int niveau = joueur.niveauActuel;
		reponse = mots.get(niveau);
		for(int i=0;i<reponse.length();i++) {
			System.out.println(reponse);
			motJeu += "-";
		}
//			return true;
//		} else {
//			return false;
//		}
	} 
	public String motJeu() {
		return motJeu;
	}
}
