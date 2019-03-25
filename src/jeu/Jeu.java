package jeu;

import java.util.ArrayList;
import java.io.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Jeu implements Serializable {
	private static final long serialVersionUID = 4706534921209753458L;
	private GUI gui; 
    private Partie partie;
    
    public Jeu() {
        gui = null;
    }
    public synchronized Partie getPartie() {
    	return partie;
    }
    /** Restaure l'ancienne partie
     * @param Partie récupérer par la sauvegarde
     */
    public void restaurePartie(Partie partiePrécédente) {
    	this.partie = partiePrécédente;
    }
    public void setGUI( GUI g) { gui = g; }
    /**
     * Lance le début du jeu en vérifiant si il existe une sauvegarde
     */
    public void lancerDebutJeu() {
    	// Affecte le jeu de ThreadLauncher avec l'objet courant.
    	ThreadLauncher.jeu = this;
    	ThreadLauncher.LancerMusique();
    	if(Sauvegarde.Deserialize(partie)!=null) {
    		// Deserialize renvoie null si il n'a pas trouvé de partie. Donc, on ne 
    		// rentre dans cette boucle que si on trouve une partie.
    		Partie savePartie = Sauvegarde.Deserialize(partie);
    		this.partie = savePartie;
    		gui.addNameFrame(partie.getJoueur().getNom()); 
    		gui.addAllActionListener();
    		afficherLocalisation();
    		//La sauvegarde n'est possible que si un joueur a été crée, il faut donc relancer
    		//les threads propre au joeur.
    		lancerThreadJoueur();
    	} else {
    		lancerNouvellePartie();
    	}
    }
    /**
     * Lance une nouvelle partie.
     */
    public void lancerNouvellePartie() {
    	partie = new Partie();
	    creerCarte();
	    afficherMessageDeBienvenue();
		sceneOuverture();
    }
    /** Fonction utilisé avec la première entrée du texte pour créer un joueur
     * @param String nomJoueur
     */
    public void creationJoueur(String nomJoueur) {
    	partie.setJoueur(new Joueur(nomJoueur));
    	gui.addNameFrame(nomJoueur);
    	lancerThreadJoueur();
    }
    /**
     *  Lance une scène d'ouverture pour lancer l'énigme.
     */
    public void sceneOuverture() {
    	gui.afficher(partie.getGuideDuJeu().dialogueLancementQuete(),partie.getGuideDuJeu());
    	while(partie.getJoueur()==null) {
    		// Permet d'attendre la création du joueur avant la suite de la scène d'ouverture
    	}
    		gui.afficher(partie.getGuideDuJeu().dialoguePendantQuete(0).replaceAll("joueur", partie.getJoueur().getNom()),partie.getGuideDuJeu());
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		partie.setZoneCourante(partie.getVaisseau());
    		afficherLocalisation();
    		gui.afficher(partie.getGuideDuJeu().dialoguePendantQuete(1));
    		getPartie().getJoueur().prendreObjet(new Teleporteur("Teleporteur", "Vieux téléporteur, peu fiable...", "teleporteur.png",3));
    		gui.addAllActionListener();
    }
    /**
     * Crée la carte
     */
    private void creerCarte() {
    	ArrayList<Zone> zones = WorldBuilder.creerToutesLesZones();
    	zones = WorldBuilder.ajouterToutesLesSorties(zones);
    	ArrayList<Planete> espace = WorldBuilder.creerLesPlanetes(zones);
    	Zone vaisseau = zones.get(0);
    	vaisseau = WorldBuilder.ajouterLesSortiesAuVaisseau(vaisseau, espace);
    	ArrayList<PersonnageActifs> tousLesPersonnageActifs = WorldBuilder.creerTousLesPersonnageActifs("allies.xml");
    	zones = WorldBuilder.positionneAlliees(zones, tousLesPersonnageActifs);
    	ArrayList<Objets> objets = WorldBuilder.creerLesObjets();
    	ArrayList<Quete> quetes = WorldBuilder.creerLesQuetesDuJeu(objets);
    	zones = WorldBuilder.miseEnPlaceDesQueteurs(zones,quetes);
    	// Set les zones à sauvegarder dans la partie car elles seront cruciales pour le jeu
    	partie.setGuideDuJeu(WorldBuilder.CreerGuide());
    	partie.setSalleDeRepos(WorldBuilder.ajouterSortieZoneDeRepos(zones.get(1), "SUD", vaisseau));
    	partie.setsceneFinal(zones.get(zones.size()-1));
    	partie.setEspace(espace);
    	partie.setZoneCourante(espace.get(0).getZones().get(1));
    	partie.setVaisseau(zones.get(0));
    }
    /**
     * Méthode utilisé pour afficher toutes les éléments de l'IHM constituant la zone courante
     */
    void afficherLocalisation() {
    	gui.afficheImage(partie.getZoneCourante().nomImage());	
        gui.afficher(partie.getZoneCourante().descriptionLongue());
        gui.afficherElementZone(partie.getZoneCourante().getAnimauxDansLazone(),partie.getZoneCourante().getPersonnageDansLaZone());
        gui.afficherBoutonSortie(partie.getZoneCourante().getSorties());
        gui.afficherMiniature(partie.getZoneCourante().nomImage(),partie.getGuideDuJeu().getImage());
        if(partie.getJoueur()!=null ) {
            gui.afficherInventaire(getPartie().getJoueur().inventaire);
        }
    }
    /**
     * Affiche le message de bienvenenue
     */
    private void afficherMessageDeBienvenue() {
        gui.afficher("Cliquez sur 'Aide' dans le menu 'Joueur' pour obtenir de l'aide.");
        gui.afficheImage(partie.getZoneCourante().nomImage());	
        gui.afficherElementZone(partie.getZoneCourante().getAnimauxDansLazone(),partie.getZoneCourante().getPersonnageDansLaZone());
        afficherLocalisation();
    }

    /** Assure que le joueur attrape le mouton selon qu'il lui reste ou non de la place dans son inventaire
     * @param mouton
     */
    public void captureDeMouton(Mouton mouton) {
    	if(partie.getJoueur().inventaire.size()<8) {
        	partie.getJoueur().prendreObjet(mouton);
        	WorldBuilder.suppresionDuMouton(partie.getZoneCourante(), mouton);
    	}	
    }
    /**Aller dans une direction donnée
     * @param direction
     */
    public void allerEn(String direction) {
    	Zone nouvelle = partie.getZoneCourante().obtientSortie(direction);
    	if ( nouvelle == null ) {
        	gui.afficher( "Pas de sortie " + direction);
    		gui.afficher();
    	}
        else {
        	partie.setZoneCourante(nouvelle);
        	afficherLocalisation();
        }
    }
	/** Méthode qui déplace le joueur
	 * @param Zone nouvelleZone
	 */
	public void allerEn(Zone nouvelleZone) {
    	partie.setZoneCourante(nouvelleZone);
        afficherLocalisation();
    }
    /** Méthode permettant de gérer l'interraction avec les personnages
     * @param personnage
     */
    public void interractionPersonnage(Personnage personnage) {
    	if(personnage instanceof PersonnageActifs) {
    		gui.afficher(personnage.parler(),personnage);
    		//friends est utilisé dans le but d'avoir un HashSet empêchant d'aoir plusieurs fois les mêmes alliés
    		//sur la zone de combat final ou sur la salle de repos.
    		partie.getJoueur().friends.add(personnage);
    		// Si il n'y a pas de différence dans la taille, c'est que l'alliée à qui je viens de parler est 
    		// déjà dans ma salle d'amis/
    		if(partie.getJoueur().friends.size()!=partie.getSalleDeRepos().getPersonnageDansLaZone().size()) {
    			partie.getSalleDeRepos().getPersonnageDansLaZone().add(personnage);
    			partie.getSceneFinal().getPersonnageDansLaZone().add(personnage);
    		} // Maintenant si on est face à un quêteur : 
    	} else if (personnage instanceof Queteur) {
    		// On récupère le personnage pour le cast sous forme de quêteur
    		Queteur queteur = (Queteur) personnage;
    		if(!queteur.besoinAide()) {
    			// On vérifie si on a déjà aidé le quêteur, ici il a besoin d'aide
    			if(getPartie().queteEnCoursPartie()==null) {
    				// Ici on vérifie qu'on a aucune quête et qu'on peut venir aider le quêteur
    				partie.setQuete((queteur).quete());
    				// Quand on fait parler le quêteur on lance notre quête avec le dialogue de début
    				gui.afficher(queteur.parler(getPartie().getJoueur()),queteur);
    			} else { // Si on rentre ici c'est que le quêteur a besoin d'aide MAIS qu'on a une quête en cours
    				if(queteur.quete()==getPartie().queteEnCoursPartie()) {
    					// Ici le quêteur auquel on parle est celui dont on est en train d'effectuer la quête
    					// Donc on intéragit avec lui dans le cadre de la quête
    					gui.afficher(queteur.quete().executerQuete(getPartie().getJoueur(), queteur));
    				} else {
    					// Ici j'ai une quête en cours mais pas celle du quêteur auquel je parle, il va
    					// donc me dire qu'il est occupé.
    					gui.afficher(((Queteur) personnage).queteDejaEnCours(),queteur);
    				}
    				
    			}
			} else {
				// Ici le quêteur n'a pas besoin d'aide, donc cela veut dire que je l'ai déjà aidé, il me remercie
				gui.afficher(queteur.remerciement(),queteur);
			}
    	} 
    }
    /** getter de la zone courante
     * @return Zone zonecourante
     */
    public Zone getZoneCourante() {
    	return this.getZoneCourante();
    }
    /**
     * Permet d'incrémenter la zone courante
     */
    public void incrementerCommande() {
    	partie.nbCommande++;
    	if(partie.nbCommande==partie.nbCommandeMax) {
    		perdu();
    	}
    }
    /**
     * Le jeu est perdu, on affiche la zone perdante.
     */
    public void perdu() {
    	afficherScenePerdante();
    }
    /**
     * Création et affichage d'une zone perdante.
     */
    public synchronized void afficherScenePerdante() {
    	partie.setZoneCourante(new Zone("","zoneperdante.gif","Vous êtes mort. Sans votre aide, Dyspros continuera encore longtemps son règne de terreur sur la galaxie..."));
    	afficherLocalisation();
    }
    /**
     * Permet de supprimer la partie et donc la sauvegarde/
     */
    public void SupprimerPartie() {
		File fichier = new File("src/data/sauvegarde.txt");
		fichier.delete();
		gui.stopFenetre();
    }
    /**
     * Lance tous les Threads en lien avec le joueur
     */
    public void lancerThreadJoueur() {
		ThreadLauncher.checkPhaseOfGame();
		ThreadLauncher.checkLifeJoueur();
    }
    
	/** Envoie réponse à l'énigme en cours
	 * @param String réponse
	 * @param queteur
	 */
	public void envoyerReponseEnigme(String reponse,Queteur queteur) {
		if(queteur.quete() instanceof Pendu) {
			// Ici, la réponse est envoyé pour exécuter le pendu
			gui.afficher(((Pendu)queteur.quete()).executerQuete(getPartie().getJoueur(), queteur,reponse));
		} else if(queteur.quete() instanceof EnigmeTextuel) {
			// Ici la réponse est envoyé pour exécuter l'Enigme Textuelle
			gui.afficher(((EnigmeTextuel)queteur.quete()).executerQuete(getPartie().getJoueur(), queteur,reponse));
		}
	}
	/**
	 * Lance la phase finale avec le retournement de situation, ajoute la sortie pour la zone de combat
	 */
	public void lancerPhaseFinale() {
		partie.getSalleDeRepos().ajouteSortie(Sortie.valueOf("NORD"), partie.getSceneFinal());
		partie.setZoneCourante(partie.getSalleDeRepos());
		partie.getGuideDuJeu().setImage("mentrisMechante.gif");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		afficherLocalisation();
		gui.afficher(partie.getGuideDuJeu().dialogueFinQuete().replaceAll("Joueur",partie.getJoueur().getNom()));
	}
	/**
	 * Sauvegarde
	 */
	public void Sauvegarde() { Sauvegarde.Serialize(partie); }
	/** Gérer l'apparation ou disparition du méchant selon le boolean passé en paramètre
	 * @param isDisplayed ( True = Apparait / False = disparait )
	 */
	public void apparitionMechant(boolean isDisplayed) { gui.displayDyspros(partie.dyspros().getImage(),isDisplayed); }
	/** Affiche le remerciement de Mentris selon le niveau
	 * @param int cptNiveau
	 */
	public void mentrisRemerciement(int cptNiveau) {
		// TODO Auto-generated method stub
		partie.setZoneCourante(partie.getVaisseau());
		afficherLocalisation();
		gui.afficher(partie.getGuideDuJeu().dialoguePendantQuete(cptNiveau).replaceAll("joueur", getPartie().getJoueur().getNom()));
	}
	/** Exécute le tour du combat 
	 * @param Personnage personnage attaquant avec le joueur pour ce tour
	 */
	public void tourDuComabat(PersonnageActifs personnage) {
		partie.getJoueur().attaquer(partie.dyspros()); 
		if(partie.dyspros().getPointDeVie()<=0) {
			partie.setZoneCourante(new Zone("Galaxie","win.gif","Vous avez sauvé toute votre galaxie"));
			gui.afficherCredit();
			apparitionMechant(false);
			afficherLocalisation();
			return;
		}
		personnage.lancerPouvoir(partie.dyspros(), partie.getJoueur());
		partie.dyspros().attaquerJoueur(personnage, partie.getJoueur());
		gui.afficher(String.valueOf(personnage.getPointDeVie()));
		if(personnage.getPointDeVie()<=0) {
			retirerPersonnageMort(personnage);
		}
		if(partie.getZoneCourante().getPersonnageDansLaZone().size()==0) {
			partie.getJoueur().setPointDeVie(0);
		}
		gui.afficherElementZone(partie.getZoneCourante().getAnimauxDansLazone(), partie.getZoneCourante().getPersonnageDansLaZone());
		gui.afficher("LES PV DE DYSPROS SONT" + partie.dyspros().getPointDeVie()+ "\n LES PV QUE TU AS"+partie.getJoueur().getPointDeVie());	
	}
	/** Retire le perosnnage mort de la zone de combat
	 * @param Personnage personnagemort
	 */
	private void retirerPersonnageMort(PersonnageActifs personnage) {
		// TODO Auto-generated method stub
		partie.getZoneCourante().getPersonnageDansLaZone().remove(personnage);
	}
	/**
	 * Lance le combat en changeant les MouseListener des label des personnages
	 */
	public void lancerCombat() {
		gui.addActionListenerCombat();
	}
	/**
	 * Téléporte le joueur dans une zone aléatoire
	 */
	public void teleporterJoueur() {
		Objets obj = partie.getJoueur().inventaire.get(0);
		if(obj instanceof Teleporteur) {
			Zone zone = ((Teleporteur)obj).teleportation(partie.carteActuel());
			if(zone!=null) {
				partie.setZoneCourante(zone);
				afficherLocalisation();
			}
		}
	}
}
