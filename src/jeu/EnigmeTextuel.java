package jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class EnigmeTextuel extends Quete {
	

	private ArrayList<String> enonceeEnigme;
	private ArrayList<String> repEnigme;
	private ArrayList<String> indices;
	private int nombreDeCoups;
	private int nombreDeCoupsMax;
	
	public EnigmeTextuel(Objets recompenseJoueur,ArrayList<String> enoncee, ArrayList<String> reponses, ArrayList<String> indice) {
		super(recompenseJoueur);
		enonceeEnigme = enoncee; 
		repEnigme = reponses;
		indices = indice;
		nombreDeCoups = 0;
		nombreDeCoupsMax = 5;
		// TODO Auto-generated constructor stub
	}

	public String sujetEnigme(int niveau)
	{
		return this.enonceeEnigme.get(niveau);
	}

	
	public String reponseEnigme(int niveau)
	{
		return this.repEnigme.get(niveau);
	}
	public String indiceEnigme(int niveau)
	{
		return this.indices.get(niveau);
	}
	

	
	public boolean bonneReponse(String reponse, int niveau)
	{	System.out.println(reponse);
		System.out.println(this.reponseEnigme(niveau));
		int id = reponse.toUpperCase().indexOf(reponseEnigme(niveau).toUpperCase());
		if(id!=-1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public String executerQuete(Joueur joueur, Queteur queteur, String str) {
		nombreDeCoups++;
		if(bonneReponse(str,joueur.niveauActuel)) {
			terminer(joueur);
			return queteur.dialogueFinQuete();
		}
		if(nombreDeCoups==2)
		{ 
		return "voici un indice pour vous aider\n"+this.indiceEnigme(joueur.niveauActuel);
		} else if(nombreDeCoups<=5) {
			return queteur.dialoguePendantQuete(1);
		}
		joueur.alive=false;
		return "";
	}
}
	
	
