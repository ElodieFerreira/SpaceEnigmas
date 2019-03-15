package jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class Pendu extends Quete{
	
	private ArrayList<String> mots;
	private int nombreDeCoups;
	private int nombreDeCoupsMax;
	private String reponse;
	private char[] lettresJouées;
	private String motJeu;
	
	public Pendu(Objets recompenseJoueur, ArrayList<String> newMots) {
		super(recompenseJoueur);
		// TODO Auto-generated constructor 
		nombreDeCoups = 0;
		nombreDeCoupsMax = 8;
		mots = new ArrayList<String>();
		motJeu = new String ("");
		mots.addAll(newMots);
	}
	
	private boolean EstTropGrande(String st) {
		if(st.length()>1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean EstDansMot(String rep) {
		int id=this.reponse.indexOf(rep.toUpperCase());
		if(id==-1) {
			return false;
		}
		else {
			//do {
			return true;				
		}
	}
	
	private void dévoileLettre(String lettre, int id) {
		int index=reponse.indexOf(lettre.toUpperCase(),id);
		System.out.println(motJeu.length());
		if(index!=-1) {
			String premièrePartie = motJeu.substring(0, index);
			String deuxièmePartie = motJeu.substring(index+1,motJeu.length());
			motJeu=premièrePartie+lettre+deuxièmePartie;
			dévoileLettre(lettre.toUpperCase(),index+1);
		}	
	}
	
	private boolean EstComplet() {
		return (motJeu.equals(reponse));
	}
		
	public String executerQuete(Joueur joueur, Queteur queteur, String str) {
		if(!EstTropGrande(str)) {
			if(EstDansMot(str)) {
				dévoileLettre(str,0);
				if(EstComplet()) {
					terminer(joueur);
					return reponse+"\n"+queteur.dialogueFinQuete();
				}
				return queteur.dialoguePendantQuete(3)+"\n"+motJeu;
			}
			else {
				// Il a fait une erreur sur la lettre
				// C'est considéré comme un coup de plus : 
				nombreDeCoups++;
				if(nombreDeCoups==nombreDeCoupsMax) {
					perdu(joueur);
					return "";
				}
				return queteur.dialoguePendantQuete(1).replaceAll(" nb",String.valueOf(nombreDeCoupsMax-nombreDeCoups))+"\n"+motJeu;
			}
		}
		else  {
			if(str.toUpperCase().equals(reponse)){
				terminer(joueur);
				return reponse+"\n"+queteur.dialogueFinQuete();
			}
			else {
				nombreDeCoups++;
				if(nombreDeCoups==nombreDeCoupsMax) {
					perdu(joueur);
					return "";
				}
				return queteur.dialoguePendantQuete(2)+"\n"+motJeu;
			}
		}
	}
	public void lancerQuete(Joueur joueur, Queteur queteur) {
		int niveau = joueur.niveauActuel;
		reponse = mots.get(niveau);
		System.out.println(reponse);
		System.out.println("TailleMot"+reponse.length());
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
