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
    public void setPartie(Partie lastPartie) {
    	this.partie = lastPartie;
    }
    public void setGUI( GUI g) { gui = g; }
    public void lancerDebutJeu() {
    	ThreadLauncher.jeu = this;
    	ThreadLauncher.LancerMusique();
    	if(Sauvegarde.Deserialize(partie)!=null) {
    		System.out.println("javais une partie");
    		Partie savePartie = Sauvegarde.Deserialize(partie);
    		this.partie = savePartie;
//    		partie.setZoneCourante(savePartie.getZoneCourante());
    		gui.addNameFrame(partie.getJoueur().getNom()); 
    		gui.addAllActionListener();
    		ThreadLauncher.checkPhaseOfGame();
    		afficherLocalisation();
    	} else {
    		partie = new Partie();
    	    creerCarte();
    	    afficherMessageDeBienvenue();
    		gui.afficher("Bienvenue ! Rentrez votre pr�nom \n");
    		sceneOuverture();
    	}
    }
    public void creationJoueur(String nomJoueur) {
    	partie.setJoueur(new Joueur(nomJoueur));
    	gui.addNameFrame(nomJoueur);
    	ThreadLauncher.checkPhaseOfGame();
//    	Thread t = new Thread(new vieDuJoueur(this.getPartie().getJoueur(),this));
//    	t.start();
    }
    public void sceneOuverture() {
    	boolean inProgress = true;
    	gui.afficher(partie.getGuideDuJeu().dialogueLancementQuete());
    	while(partie.getJoueur()==null) {
    		
    	}
    	while(inProgress) {
    		gui.afficher(partie.getGuideDuJeu().dialoguePendantQuete(0).replaceAll("joueur", partie.getJoueur().getNom()));
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		partie.setZoneCourante(partie.getVaisseau());
    		gui.addAllActionListener();
    		afficherLocalisation();
    		inProgress = false;
    	}
    	System.out.println("Bienvenue chez les Hyènes");
    }
    private void creerCarte() {
    	WorldBuilder constructorOfMap = new WorldBuilder();
    	ArrayList<Zone> zones = constructorOfMap.creerToutesLesZones();
    	zones = constructorOfMap.ajouterToutesLesSorties(zones);
    	ArrayList<Planete> espace = constructorOfMap.creerLesPlanetes(zones);
    	Zone vaisseau = zones.get(0);
    	vaisseau = constructorOfMap.ajouterLesSortiesAuVaisseau(vaisseau, espace);
    	ArrayList<Allies> tousLesAllies = constructorOfMap.creerTousLesAllies("allies.xml");
    	zones = constructorOfMap.positionneAlliees(zones, tousLesAllies);
    	ArrayList<Objets> objets = constructorOfMap.creerLesObjets();
    	ArrayList<Quete> quetes = constructorOfMap.creerLesQuetesDuJeu(objets);
    	zones = constructorOfMap.miseEnPlaceDesQueteurs(zones,quetes);
    	partie.setGuideDuJeu(constructorOfMap.CreerGuide());
    	partie.setSalleDeRepos(constructorOfMap.ajouterSortieZoneDeRepos(zones.get(1), "SUD", vaisseau));
    	partie.setsceneFinal(zones.get(zones.size()-1));
    	partie.setEspace(espace);
    	partie.setZoneCourante(espace.get(0).getZones().get(1));
    	partie.setVaisseau(zones.get(0));
    	System.out.println(espace.get(0).getZones().get(1).getNom());
//    	Thread music = new Thread(new Music("dust.wav"));
//    	music.start();
    }
    

    private void afficherLocalisation() {
    	  	gui.afficherMiniature(partie.getZoneCourante().nomImage(),partie.getGuideDuJeu().getImage());
    		gui.afficheImage(partie.getZoneCourante().nomImage());	
            gui.afficher(partie.getZoneCourante().descriptionLongue());
            gui.afficherElementZone(partie.getZoneCourante().getAnimauxDansLazone(),partie.getZoneCourante().getPersonnageDansLaZone());
            gui.afficherBoutonSortie(partie.getZoneCourante().getSorties());
            gui.afficher();
    }
    
    private void afficherMessageDeBienvenue() {
    	gui.afficher("Bienvenue !");
    	gui.afficher();
        gui.afficher("Tapez '?' pour obtenir de l'aide.");
        gui.afficher();
        gui.afficheImage(partie.getZoneCourante().nomImage());	
        gui.afficherElementZone(partie.getZoneCourante().getAnimauxDansLazone(),partie.getZoneCourante().getPersonnageDansLaZone());
        afficherLocalisation();
    }
    
    public void traiterCommande(String commandeLue) {
    	System.out.println("ok");
    }

    public void captureDeMouton(Mouton mouton) {
    	WorldBuilder constructorOfMap = new WorldBuilder();
    	partie.getJoueur().prendreObjet(mouton);
    	constructorOfMap.suppresionDuMouton(partie.getZoneCourante(), mouton);
    	
    }
    public void allerEn(String direction) {
    	Zone nouvelle = partie.getZoneCourante().obtientSortie(direction);
    	if ( nouvelle == null ) {
        	gui.afficher( "Pas de sortie " + direction);
    		gui.afficher();
    	}
        else {
        	partie.setZoneCourante(nouvelle);
        	afficherZone();
        }
    }
    private void afficherZone() {
		// TODO Auto-generated method stub
    	gui.afficher(partie.getZoneCourante().descriptionLongue());
    	gui.afficher();
        gui.afficheImage(partie.getZoneCourante().nomImage());	
        gui.afficherBoutonSortie(partie.getZoneCourante().getSorties());
        gui.afficherElementZone(partie.getZoneCourante().getAnimauxDansLazone(),partie.getZoneCourante().getPersonnageDansLaZone());
	}
	public void allerEn(Zone nouvelleZone) {
    	partie.setZoneCourante(nouvelleZone);
    	gui.afficher(partie.getZoneCourante().descriptionLongue());
    	gui.afficher();
        gui.afficheImage(partie.getZoneCourante().nomImage());	
        gui.afficherBoutonSortie(partie.getZoneCourante().getSorties());
        gui.afficherElementZone(partie.getZoneCourante().getAnimauxDansLazone(),partie.getZoneCourante().getPersonnageDansLaZone());
    }
    public void interractionPersonnage(Personnage personnage) {
    	if(personnage instanceof Allies) {
    		gui.afficher(personnage.parler());
    		partie.getJoueur().friends.add(personnage);
    		if(partie.getJoueur().friends.size()!=partie.getSalleDeRepos().getPersonnageDansLaZone().size()) {
    			partie.getSalleDeRepos().getPersonnageDansLaZone().add(personnage);
    		}
    	} else if (personnage instanceof Queteur) {
    		Queteur queteur = (Queteur) personnage;
    		if(!queteur.besoinAide()) {
    			if(getPartie().queteEnCoursPartie()==null) {
    				partie.setQuete((queteur).quete());
    				gui.afficher(queteur.parler(getPartie().getJoueur()));
    				if(queteur.quete() instanceof capturerMouton) {
//    					WorldBuilder mouton = new WorldBuilder();
//    					ArrayList<Zone> zone = mouton.positionMouton(partie.zones(),3);				
    				}
    				System.out.println("jsetelaquequete");
    				System.out.println(queteur.quete().getClass());
    			} else {
    				if(queteur.quete()==getPartie().queteEnCoursPartie()) {
    					gui.afficher(queteur.quete().executerQuete(getPartie().getJoueur(), queteur));
//    						if(checkPhaseFinale()) {
//    							lancerPhaseFinale();
//    							System.out.println("je suis dans la phase finale");
//    						}
    				} else {
    					gui.afficher(((Queteur) personnage).queteDejaEnCours());
    				}
    				
    			}
			} else {
				gui.afficher(queteur.remerciement());
			}
    	} 
    }
//	private void terminer() {
//    	gui.afficher( "Au revoir...");
//    	gui.enable( false);
//    }
    public Zone getZoneCourante() {
    	return this.getZoneCourante();
    }
    public void incrementerCommande() {
    	partie.nbCommande++;
    	System.out.println(partie.nbCommande);
    	if(partie.nbCommande==partie.nbCommandeMax) {
    		perdu();
    	}
    }
    public void perdu() {
    	System.out.println("T'as perdu wesh! ");
    	afficherScenePerdante();
//    	gui.stopFenetre();
    }
    public void afficherScenePerdante() {
    	partie.setZoneCourante(new Zone("","zoneperdante.gif","Vous êtes mort. Sans votre aide, Dyspros continuera encore longtemps son règne de terreur sur la galaxie..."));
    	afficherZone();
    }
    public void SupprimerPartie()
    {
		File fichier = new File("src/data/sauvegarde.txt");
		fichier.delete();
		partie = new Partie();
		this.setGUI(gui);
		gui.addNameFrame("");
		creerCarte();
		afficherMessageDeBienvenue();
		gui.afficher("Bienvenue ! Rentrez votre prénom \n");
    }
	public void envoyerReponseEnigme(String str,Queteur queteur) {
		if(queteur.quete() instanceof Pendu) {
			gui.afficher(((Pendu)queteur.quete()).executerQuete(getPartie().getJoueur(), queteur,str));
		} else if(queteur.quete() instanceof EnigmeTextuel) {
			gui.afficher(((EnigmeTextuel)queteur.quete()).executerQuete(getPartie().getJoueur(), queteur,str));
		}
	}
	public boolean checkPhaseFinale() {
		return (partie.getJoueur().niveauActuel==partie.getJoueur().niveauMaximum);
	}
	public void lancerPhaseFinale() {
		partie.getSalleDeRepos().ajouteSortie(Sortie.valueOf("NORD"), partie.getSceneFinal());
		partie.setZoneCourante(partie.getSalleDeRepos());
		getPartie().getSceneFinal().setAllPersonnage(getPartie().getSalleDeRepos().getPersonnageDansLaZone());
		afficherZone();
	}
	public void Sauvegarde()
    {
    	Sauvegarde save = new Sauvegarde(partie);
    	save.Serialize(partie);
    }
	public void apparitionMechant() {
		Allies Dyspros = new Allies("Dyspros","","gold.png",25,2,Role.valueOf("FIGHTER"),"ola");
		gui.afficherMechant(Dyspros.getImage());
	}

}
