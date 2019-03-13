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
		int id=this.mots.get(0).indexOf(rep);
		if(id==-1) {
			return false;
		}
		else {
			//do {
			return true;				
			
			
		}
	}
	
	private void dévoileLettre(String lettre, int id) {
		int index=reponse.indexOf(lettre,id);
		System.out.println(motJeu.length());
		if(index!=-1) {
			String premièrePartie = motJeu.substring(0, index);
			String deuxièmePartie = motJeu.substring(index+1,motJeu.length());
			motJeu=premièrePartie+lettre+deuxièmePartie;
			dévoileLettre(lettre,index+1);
		}	
	}
	
	private boolean EstComplet() {
		return (motJeu.equals(mots.get(0)));
	}
		
	public String executerQuete(Joueur joueur, Queteur queteur, String str) {
		if(!EstTropGrande(str)) {
			if(EstDansMot(str)) {
				dévoileLettre(str,0);
				if(EstComplet()) {
					terminer(joueur);
					return queteur.dialogueFinQuete();
				}
				return queteur.dialoguePendantQuete(3)+"\n"+motJeu;
			}
			else {
				return queteur.dialoguePendantQuete(1);
			}
		}
		else  {
			if(str.equals(mots.get(0))){
				terminer(joueur);
				return queteur.dialogueFinQuete();
			}
			else {
				return queteur.dialoguePendantQuete(2);
			}
		}
	}
	public boolean lancerQuete(Joueur joueur, Queteur queteur) {
		if(!status) {
			int niveau = joueur.niveauActuel;
			reponse = mots.get(niveau);
			System.out.println(reponse);
			System.out.println("TailleMot"+reponse.length());
			for(int i=0;i<reponse.length();i++) {
				System.out.println(reponse);
				motJeu += "-";
			}
			return true;
		} else {
			return false;
		}
	} 
	public String motJeu() {
		return motJeu;
	}
}
