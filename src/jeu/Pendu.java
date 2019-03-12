package jeu;

import java.util.ArrayList;
import java.util.Scanner;




public class Pendu extends Quête{
	
	private ArrayList<String> mots;
	private int nombreDeCoups;
	private String reponse;
	private char[] lettresJouées;
	private String motJeu;
	
	public Pendu(Objets recompenseJoueur,String motADeviner) {
		super(recompenseJoueur);
		// TODO Auto-generated constructor stub
		nombreDeCoups = 0;
		ArrayList<String> mots = new ArrayList<String>();
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
	
	private void checkCaractère(String rep) {
		
		int id=this.mots.get(0).indexOf(rep);
		if(id==-1) {
			System.out.println("Perdu!");
			
		}
		else {
			//do {
				id=mots.get(0).indexOf(rep,id);
				
			
			
		}
	}
	
	public void LancerQuête() {
		System.out.println(motJeu);
		while(!status) {
			Scanner rep = new Scanner(System.in);
			System.out.println("Tapez vore lettre ");
			String str = rep.nextLine();
			if(!EstTropGrande(str)) {
				System.out.println("Okay!");
				checkCaractère(str);
			}
			else if(str.toUpperCase().equals("SOLUTION")) {
				Scanner prop = new Scanner(System.in);
				System.out.println("Rentrez la soluton : ");
				String Prop = prop.nextLine();
			}
			else {
				System.out.println("Tapez un seul caractère");
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	

	
}
