package jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class Pendu extends Quete{
	
	private ArrayList<String> mots;
	private int nombreDeCoups;
	private int nombreDeCoupsMax;
	private String reponse;
	private String motJeu;
	
	/**
	 * 
	 * Constructor which initialize the hangman quest
	 * @param recompenseJoueur is the object that will be given to the player after the quest
	 * @param newMots represents a array of word which are the answers to the hangman
	 */
	public Pendu(Objets recompenseJoueur, ArrayList<String> newMots) {
		super(recompenseJoueur);
		nombreDeCoups = 0;
		nombreDeCoupsMax = 8;
		// Prise en compte d'un ensemble de mots qui peuvent tomber au pendu
		mots = new ArrayList<String>();
		motJeu = new String ("");
		mots.addAll(newMots);
	}
	
	/** 
	 * This method check if the player wrote more than one letter
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
	
	/**
	 * This method check if the word written on the keyboard is contained in the answer
	 * @param rep is the word that the player wrote on the keyboard
	 * @return : a boolean which return true if the word written is contained in the answer else return false
	 */
	private boolean EstDansMot(String rep) {
		return this.reponse.contains(rep);
	}

	/**
	 * This method launch the quest.
	 * @param joueur the player
	 * @param queteur the person who launch the quest 
	 */
	public void lancerQuete(Joueur joueur, Queteur queteur) {
		int niveau = joueur.niveauActuel;
		reponse = mots.get(niveau);
		for(int i=0;i<reponse.length();i++) {
			motJeu += "-";
		}
	} 
	
	/**
	 * This method show the letter to the players
	 * @param lettre is the letter that the player wrote on the keyboard and we search it place its place
	 * @param id the index from which to start the search.
	 */
	private void devoileLettre(String lettre, int id) {
		// On prend l'index de la lettre trouvé dans la réponse
		int index=reponse.indexOf(lettre.toUpperCase(),id);
		// Rappel : motJeu = "-----"
		// Si l'index est égal à moins 1 ça signifie que la lettre est bien dans le mot
		if(index!=-1) {
			// On va parcourir motJeu jusqu'à l'index de la lettre trouvé et afficher cette lettre
			String premierePartie = motJeu.substring(0, index);
			/*On va parcourir  de nouveau motJeu mais au-delà de l'index (en cas de doublon de lettre)
			et l'afficher */
			String deuxiemePartie = motJeu.substring(index+1,motJeu.length());
			// motJeu change de valeur prend en compte les lettres dévoilées
			motJeu=premierePartie+lettre.toUpperCase()+deuxiemePartie;
			/* Invocation de la même méthode jusqu'à ce que toutes les lettres soient trouvées ou que les coups max
			on été atteint */
			devoileLettre(lettre.toUpperCase(),index+1);
		}	
	}
	
	/**
	 *
	 * This method check if the word is complete
	 * @return boolean : true if the word we play is complete else false
	 */
	private boolean EstComplet() {
		return (motJeu.equals(reponse));
	}
		
	/** This method allows you to run the hangman quest.
	 * @see EstTropGrande
	 * @see EstDansMot 
	 * @see devoileLettre
	 * @see terminer
	 * @param joueur represents the player
	 * @param queteur represents the person who give to the player the quest
	 * @param str represents the letter that the player wrote
	 * @return String which can be : 
	 * <ul>
	 * <li>Thank you sentence of the person who launched the quest</li>
	 * <li>The number of the chance remaining</li>
	 * <li> A sentence that prevents the execution of two quests (because the players have already a quest remaining)</li>
	 * </ul>
	 */
	public String executerQuete(Joueur joueur, Queteur queteur, String str) {
		// Si tout est bon 
		if(!EstTropGrande(str.toUpperCase())) {
			if(EstDansMot(str.toUpperCase())) {
				devoileLettre(str.toUpperCase(),0);
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
					perdu(joueur);
					return "";
					
				} else {
					// Si le joueur s'est trompé mais qu'il n'a pas atteint le nombre de coups max
					return queteur.dialoguePendantQuete(1).replaceAll(" nb",String.valueOf(nombreDeCoupsMax-nombreDeCoups))+"\n"+motJeu;
				}
			}
		}
		
		else  {
			// Si la lettre est supérieur à 1. On vérifie que le mot égal à la réponse
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

	/** Getter permettant d'obtenir le mot du jeu 
	 * @return String
	 */
	public String motJeu() {
		return motJeu;
	}
}
