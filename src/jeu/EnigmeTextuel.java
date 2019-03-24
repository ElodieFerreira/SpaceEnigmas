package jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class EnigmeTextuel extends Quete {
	

	private ArrayList<String> enonceeEnigme;
	private ArrayList<String> repEnigme;
	private ArrayList<String> indices;
	private int nombreDeCoups;
	private int nombreDeCoupsMax;
	
	/**<b>EnigmeTextuel(Objets,ArrayList String, ArrayList String, ArrayList String</b>
	 * Ce constructeur initialise les propriétés d'instance 
	 * @param recompenseJoueur représente la récompense que le joueur gagnera
	 * @param enoncee représente l'énoncée de l'enigme 
	 * @param reponses représente la réponse de l'énoncée de l'enigme
	 * @param indice représente l'indice que de l'enigme
	 */
	public EnigmeTextuel(Objets recompenseJoueur,ArrayList<String> enoncee, ArrayList<String> reponses, ArrayList<String> indice) {
		super(recompenseJoueur);
		enonceeEnigme = enoncee; 
		repEnigme = reponses;
		indices = indice;
		nombreDeCoups = 0;
		nombreDeCoupsMax = 5;
	
	}

	/**<b> sujetEnigme(int)</b>
	 * Cette méthode sélectionne l'énigme en fonction du niveau du joueur 
	 * @param niveau représente le niveau du joueur 
	 * @return l'énoncée de l'énigme 
	 */
	public String sujetEnigme(int niveau)
	{
		return this.enonceeEnigme.get(niveau);
	}

	
	/**<b> reponseEnigme(int)</b>
	 * Cette méthode sélectionne la réponse associée à l'enigme
	 * @param niveau représente le niveau du joueur
	 * @return String correspondant à la réponse
	 */
	public String reponseEnigme(int niveau)
	{
		return this.repEnigme.get(niveau);
	}
	/**
	 * <b>indiceEnigme(int) </b>
	 * Cette méthode sélectionne l'indice associé à l'enigme 
	 * @param niveau représente le niveau du joueur
	 * @return String correspondant à l'indice
	 */
	public String indiceEnigme(int niveau)
	{
		return this.indices.get(niveau);
	}
	

	
	/**<b>bonneReponse(String, int) </b>
	 * Cette méthode vérifie si la réponse donnée figure dans la réponse de l'enigme
	 * @param reponse représente la réponse
	 * @param niveau représente le niveau du joueur 
	 * @return true : si la réponse donnée est dans l'enigme sinon false 
	 */
	public boolean bonneReponse(String reponse, int niveau)
	{	System.out.println(reponse);
		System.out.println(this.reponseEnigme(niveau));
		return reponse.toUpperCase().contains(reponseEnigme(niveau).toUpperCase());
	}
	/**<b>executerQuete(Joueur,Queteur,String)</b>
	 * Cette méthode permet d'exécuter la quête de l'enigme
	 * @param joueur
	 * @param queteur
	 * @param str
	 * @return String indiquant au joueur s'il a réussi ou non
	 */
	public String executerQuete(Joueur joueur, Queteur queteur, String str) {
		nombreDeCoups++;
		if(bonneReponse(str,joueur.niveauActuel)) {
			int pointDattaqueGagne = 50 - 5*nombreDeCoups;
			terminer(joueur,pointDattaqueGagne);
			return queteur.dialogueFinQuete();
		}
		if(nombreDeCoups==2)
		{
		return "voici un indice pour vous aider\n"+this.indiceEnigme(joueur.niveauActuel);
		} else if(nombreDeCoups<=nombreDeCoupsMax) {
			return queteur.dialoguePendantQuete(1);
		}
		perdu(joueur);
		return "";
	}
}
	
	
