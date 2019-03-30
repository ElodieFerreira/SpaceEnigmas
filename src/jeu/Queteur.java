package jeu;

import java.util.ArrayList;
import java.util.HashMap;

public class Queteur extends Personnage {
	private String queteDejaEnCours;
	private String dialogueLancementQuete;
	private ArrayList<String> dialoguePendantQuete;
	private String dialogueFinQuete;
	private String remerciements;
	private ArrayList<Objets> inventaire;
	private Quete quete;
	
	/** Constructeur de la classe queteur
	 * @param nomPerso
	 * @param descriptionPerso
	 * @param imagePerso
	 */
	public Queteur(String nomPerso, String descriptionPerso, String imagePerso) {
		super(nomPerso, descriptionPerso, imagePerso);
		inventaire = new ArrayList<Objets>();
		
	}
	/** Getter permettant d'obtenir les éléments de l'inventaire
	 * @return ArrayList d'objets
	 */
	public ArrayList<Objets> getInventaire() {
		return inventaire;
	}

	/**
	 * Méthode permettant de définir la phrase de lancement de la quête en fonction du queteur qui l'a lancé
	 * @param joueur
	 * @return String
	 */
	public String parler(Joueur joueur) {
		quete.lancerQuete(joueur, this);
		if(quete instanceof Pendu) {
			return dialogueLancementQuete+"\n"+((Pendu) quete).motJeu();
		}
		if(quete instanceof EnigmeTextuel) {
			return dialogueLancementQuete+"\n"+((EnigmeTextuel) quete).sujetEnigme(joueur.niveauActuel);
		} else {
			ThreadLauncher.makeMoveMouton();
		}
		return dialogueLancementQuete;
	}
	/** Getter qui permet d'obtenir la quete 
	 * @return Quete
	 */
	public Quete quete() {
		return quete;
	}
	/** Methode permettant de récupérer un objet et de l'ajouter à son inventaire
	 * @param obj
	 */
	public void prendre(Objets obj) {
		this.inventaire.add(obj);
	}
	// les getters 
	/** Getter permettant d'obtenir le dialogue de lancement de la quête
	 * @return String
	 */
	public String dialogueLancementQuete() {
		return dialogueLancementQuete;
	}
	/** Getter permettant d'obtenir l'un des dialogues pendant la quête
	 * @param index permet de savoir quel dialogue prendre
	 * @return String
	 */
	public String dialoguePendantQuete(int index) {
		return dialoguePendantQuete.get(index);
	}
	/** Getter permet d'obtenir le dialogue de fin de quête
	 * @return String
	 */
	public String dialogueFinQuete() {
		return dialogueFinQuete;
	}
	/** Getter permettant d'obtenir le diaogue de remerciement
	 * @return String 
	 */
	public String remerciement() {
		return remerciements;
	}
	/** Getter permettant d'obtenir le dialogue de quete dejà en cours 
	 * @return String
	 */
	public String queteDejaEnCours() {
		return queteDejaEnCours;
	}
	/** Getter permettant d'obtenir l'inventaire 
	 * @return ArrayList d'objet
	 */
	public ArrayList<Objets> inventaire() {
		return inventaire;
	}
	// Les setteurs 

	/** Setter permettant de modifier la quête courante
	 * @param quete
	 */
	public void setQuete(Quete quete) {
		this.quete = quete;
	}
	/** Setter permettant de modifier le dialogue de quete déjà en cours 
	 * @param queteDejaEnCours
	 */
	public void setQueteDejaEnCours(String queteDejaEnCours) {
		this.queteDejaEnCours = queteDejaEnCours;
	}
	/** Setter permettant demodifier le dialogue de lancement de quête 
	 * @param dialogueLancementQuete
	 */
	public void setDialogueLancementQuete(String dialogueLancementQuete) {
		this.dialogueLancementQuete = dialogueLancementQuete;
	}
	/** Setter permettant de modifier le dialogue pendant quête 
	 * @param dialoguePendantQuete
	 */
	public void setDialoguePendantQuete(ArrayList<String> dialoguePendantQuete) {
		this.dialoguePendantQuete = dialoguePendantQuete;
	}
	/** Setter permettant de modifier le dialogue de fin de quete 
	 * @param dialogueFinQuete
	 */
	public void setDialogueFinQuete(String dialogueFinQuete) {
		this.dialogueFinQuete = dialogueFinQuete;
	}
	/** Setter permettant de modifier le dialogue de remerciement
	 * @param remerciements
	 */
	public void setRemerciements(String remerciements) {
		this.remerciements = remerciements;
	}
	/** Setter permettant de modifier tous les dialogues 
	 * @param dejaEnCours
	 * @param debut
	 * @param pendant
	 * @param fin
	 * @param merci
	 */
	public void setAllDialogues(String dejaEnCours, String debut, ArrayList<String> pendant, String fin, String merci) {
		queteDejaEnCours = dejaEnCours;
		dialogueLancementQuete = debut;
		dialoguePendantQuete = pendant;
		dialogueFinQuete = fin;
		remerciements = merci;
	}
	/** Méthode permettant de vérifier si le joueur a besoin d'aide 
	 * @return boolean false si la quête n'a oa été faute subib trye 
	 */
	public boolean besoinAide() {
		return quete.isStatus();
	}

}
