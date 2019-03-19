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
    		afficherLocalisation();
    		lancerThreadJoueur();
    	} else {
    		System.out.println("coucou je suis là");
    		lancerNouvellePartie();
    	}
    }
    public void lancerNouvellePartie() {
    	partie = new Partie();
	    creerCarte();
	    afficherMessageDeBienvenue();
		gui.afficher("Bienvenue ! Rentrez votre pr�nom \n");
		sceneOuverture();
    }
    public void creationJoueur(String nomJoueur) {
    	partie.setJoueur(new Joueur(nomJoueur));
    	gui.addNameFrame(nomJoueur);
    	lancerThreadJoueur();
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
    		afficherLocalisation();
    		gui.afficher(partie.getGuideDuJeu().dialoguePendantQuete(1));
    		getPartie().getJoueur().prendreObjet(new Teleporteur("Teleporteur", "Vieux téléporteur, peu fiable...", "teleporteur.png",3));
    		gui.addAllActionListener();
    		inProgress = false;
    	}
    	System.out.println("Bienvenue chez les Hyènes");
    }
    private void creerCarte() {
    	ArrayList<Zone> zones = WorldBuilder.creerToutesLesZones();
    	zones = WorldBuilder.ajouterToutesLesSorties(zones);
    	ArrayList<Planete> espace = WorldBuilder.creerLesPlanetes(zones);
    	Zone vaisseau = zones.get(0);
    	vaisseau = WorldBuilder.ajouterLesSortiesAuVaisseau(vaisseau, espace);
    	ArrayList<Allies> tousLesAllies = WorldBuilder.creerTousLesAllies("allies.xml");
    	zones = WorldBuilder.positionneAlliees(zones, tousLesAllies);
    	ArrayList<Objets> objets = WorldBuilder.creerLesObjets();
    	ArrayList<Quete> quetes = WorldBuilder.creerLesQuetesDuJeu(objets);
    	zones = WorldBuilder.miseEnPlaceDesQueteurs(zones,quetes);
    	partie.setGuideDuJeu(WorldBuilder.CreerGuide());
    	partie.setSalleDeRepos(WorldBuilder.ajouterSortieZoneDeRepos(zones.get(1), "SUD", vaisseau));
    	partie.setsceneFinal(zones.get(zones.size()-1));
    	partie.setEspace(espace);
    	partie.setZoneCourante(espace.get(0).getZones().get(1));
    	partie.setVaisseau(zones.get(0));
    	System.out.println(espace.get(0).getZones().get(1).getNom());
//    	Thread music = new Thread(new Music("dust.wav"));
//    	music.start();
    }
    

    void afficherLocalisation() {
    	gui.afficheImage(partie.getZoneCourante().nomImage());	
        gui.afficher(partie.getZoneCourante().descriptionLongue());
        gui.afficherElementZone(partie.getZoneCourante().getAnimauxDansLazone(),partie.getZoneCourante().getPersonnageDansLaZone());
        gui.afficherBoutonSortie(partie.getZoneCourante().getSorties());
        gui.afficherMiniature(partie.getZoneCourante().nomImage(),partie.getGuideDuJeu().getImage());
        if(partie.getJoueur()!=null ) {
            gui.afficherInventaire(getPartie().getJoueur().inventaire);
        }
        if(partie.getZoneCourante()==partie.getSceneFinal()) {
         	apparitionMechant();
         	System.out.println("je suis dans la salle finale");
        }
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
        	afficherLocalisation();
        }
    }
    
	public void allerEn(Zone nouvelleZone) {
    	partie.setZoneCourante(nouvelleZone);
        afficherLocalisation();
    }
    public void interractionPersonnage(Personnage personnage) {
    	if(personnage instanceof Allies) {
    		gui.afficher(personnage.parler());
    		partie.getJoueur().friends.add(personnage);
    		if(partie.getJoueur().friends.size()!=partie.getSalleDeRepos().getPersonnageDansLaZone().size()) {
    			partie.getSalleDeRepos().getPersonnageDansLaZone().add(personnage);
    			partie.getSceneFinal().getPersonnageDansLaZone().add(personnage);
    		}
    	} else if (personnage instanceof Queteur) {
    		Queteur queteur = (Queteur) personnage;
    		if(!queteur.besoinAide()) {
    			if(getPartie().queteEnCoursPartie()==null) {
    				partie.setQuete((queteur).quete());
    				gui.afficher(queteur.parler(getPartie().getJoueur()));
    				System.out.println("jsetelaquequete");
    				System.out.println(queteur.quete().getClass());
    			} else {
    				if(queteur.quete()==getPartie().queteEnCoursPartie()) {
    					gui.afficher(queteur.quete().executerQuete(getPartie().getJoueur(), queteur));
    				} else {
    					gui.afficher(((Queteur) personnage).queteDejaEnCours());
    				}
    				
    			}
			} else {
				gui.afficher(queteur.remerciement());
			}
    	} 
    }
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
    }
    public synchronized void afficherScenePerdante() {
    	partie.setZoneCourante(new Zone("","zoneperdante.gif","Vous êtes mort. Sans votre aide, Dyspros continuera encore longtemps son règne de terreur sur la galaxie..."));
    	afficherLocalisation();
    }
    public void SupprimerPartie()
    {
		File fichier = new File("src/data/sauvegarde.txt");
		fichier.delete();
		gui.stopFenetre();
    }
    public void lancerThreadJoueur() {
		ThreadLauncher.checkPhaseOfGame();
		ThreadLauncher.checkLifeJoueur();
    }
	public void envoyerReponseEnigme(String str,Queteur queteur) {
		if(queteur.quete() instanceof Pendu) {
			gui.afficher(((Pendu)queteur.quete()).executerQuete(getPartie().getJoueur(), queteur,str));
		} else if(queteur.quete() instanceof EnigmeTextuel) {
			gui.afficher(((EnigmeTextuel)queteur.quete()).executerQuete(getPartie().getJoueur(), queteur,str));
		}
	}
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
		gui.afficher(partie.getGuideDuJeu().dialogueFinQuete().replaceAll("joueur",partie.getJoueur().getNom()));
		afficherLocalisation();
	}
	public void Sauvegarde() { Sauvegarde.Serialize(partie); }
	public void apparitionMechant() { gui.afficherMechant(partie.dyspros().getImage()); }
	
	public void mentrisRemerciement(int cptNiveau) {
		// TODO Auto-generated method stub
		partie.setZoneCourante(partie.getVaisseau());
		afficherLocalisation();
		gui.afficher(partie.getGuideDuJeu().dialoguePendantQuete(cptNiveau).replaceAll("joueur", getPartie().getJoueur().getNom()));
	}
	public void tourDuComabat(Allies personnage) {
		// TODO Auto-generated method stub
		partie.getJoueur().attaquer(partie.dyspros()); 
		gui.afficher("LES PV DE DYSPROS SONT DE "+partie.dyspros().getPointDeVie());
		personnage.lancerPouvoir(partie.dyspros(), partie.getJoueur());
		partie.dyspros().attaquerJoueur(personnage, partie.getJoueur());
		gui.afficher(String.valueOf(personnage.getPointDeVie()));
		if(personnage.getPointDeVie()<0) {
			retirerPersonnageMort(personnage);
		}
		if(partie.getZoneCourante().getPersonnageDansLaZone().size()==0) {
			partie.getJoueur().setPointDeVie(0);
		}
		gui.afficherElementZone(partie.getZoneCourante().getAnimauxDansLazone(), partie.getZoneCourante().getPersonnageDansLaZone());
		gui.afficher("LES PV QUE TU AS"+partie.getJoueur().getPointDeVie());	
	}
	private void retirerPersonnageMort(Allies personnage) {
		// TODO Auto-generated method stub
		partie.getZoneCourante().getPersonnageDansLaZone().remove(personnage);
	}
	public void lancerCombat() {
		gui.addActionListenerCombat();
	}
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
