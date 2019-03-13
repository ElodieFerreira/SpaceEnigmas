package jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class Pendu extends Quete{
	
	private ArrayList<String> mots;
	private int nombreDeCoups;
	private String reponse;
	private char[] lettresJouées;
	private String motJeu;
	
	public Pendu(Objets recompenseJoueur,String motADeviner) {
		super(recompenseJoueur);
		// TODO Auto-generated constructor stub
		nombreDeCoups = 0;
		mots = new ArrayList<String>();
		mots.add(motADeviner);
		motJeu = new String("");
		for(int i=0;i<motADeviner.length();i++) {
			motJeu += "-";
		}
		LancerQuête();
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
		int index=mots.get(0).indexOf(lettre,id);
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
	
	public void LancerQuête() {
		System.out.println(motJeu);
		while(!status) {
			Scanner rep = new Scanner(System.in);
			System.out.println("Tapez vore lettre ");
			String str = rep.nextLine().toUpperCase();
			if(!EstTropGrande(str)) {
				
				if(EstDansMot(str)) {
					dévoileLettre(str,0);
					System.out.println(motJeu);
					if(EstComplet()) {
						status=true;
					}
				}
				else {
					System.out.println("Mauvaise réponse !");
				}
				
			}
			else  {
				
				if(str.equals(mots.get(0))){
					status=true;
					System.out.println("Gagné!");
				}
				else {
					System.out.println("Elodie me dit de te dire que tu es nul, mais je trouve ça un peu méchant");
				}
			}
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	

	
}
