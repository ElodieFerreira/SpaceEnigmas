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
	public Queteur(String nomPerso, String descriptionPerso, String imagePerso) {
		super(nomPerso, descriptionPerso, imagePerso);
		inventaire = new ArrayList<Objets>();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Objets> getInventaire() {
		return inventaire;
	}

	public String parler(Joueur joueur) {
		quete.lancerQuete(joueur, this);
		if(quete instanceof Pendu) {
			return dialogueLancementQuete+"\n"+((Pendu) quete).motJeu();
		}
		if(quete instanceof EnigmeTextuel) {
			return dialogueLancementQuete+"\n"+((EnigmeTextuel) quete).sujetEnigme(joueur.niveauActuel);
		}
		return dialogueLancementQuete;
	}
	public Quete quete() {
		return quete;
	}
	public void prendre(Objets obj) {
		this.inventaire.add(obj);
	}
	// les getters 
	public String dialogueLancementQuete() {
		return dialogueLancementQuete;
	}
	public String dialoguePendantQuete(int index) {
		return dialoguePendantQuete.get(index);
	}
	public String dialogueFinQuete() {
		return dialogueFinQuete;
	}
	public String remerciement() {
		return remerciements;
	}
	public String queteDejaEnCours() {
		return queteDejaEnCours;
	}
	public ArrayList<Objets> inventaire() {
		return inventaire;
	}
	// Les setteurs 

	public void setQuete(Quete quete) {
		this.quete = quete;
	}
	public void setQueteDejaEnCours(String queteDejaEnCours) {
		this.queteDejaEnCours = queteDejaEnCours;
	}
	public void setDialogueLancementQuete(String dialogueLancementQuete) {
		this.dialogueLancementQuete = dialogueLancementQuete;
	}
	public void setDialoguePendantQuete(ArrayList<String> dialoguePendantQuete) {
		this.dialoguePendantQuete = dialoguePendantQuete;
	}
	public void setDialogueFinQuete(String dialogueFinQuete) {
		this.dialogueFinQuete = dialogueFinQuete;
	}
	public void setRemerciements(String remerciements) {
		this.remerciements = remerciements;
	}
	public void setAllDialogues(String dejaEnCours, String debut, ArrayList<String> pendant, String fin, String merci) {
		queteDejaEnCours = dejaEnCours;
		dialogueLancementQuete = debut;
		dialoguePendantQuete = pendant;
		dialogueFinQuete = fin;
		remerciements = merci;
	}
	public boolean besoinAide() {
		// TODO Auto-generated method stub
		return quete.isStatus();
	}

}
