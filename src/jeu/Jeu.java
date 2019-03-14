package jeu;

import java.util.ArrayList;
import java.io.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Jeu implements Serializable {
	private static final long serialVersionUID = 4706534921209753458L;
	private GUI gui; 
	private Zone zoneCourante;
    private Partie partie;
    
    public Jeu() {
        gui = null;
    }
    public Partie getPartie() {
    	return partie;
    }
    public void setPartie(Partie lastPartie) {
    	this.partie = lastPartie;
    }
    public void setGUI( GUI g) { gui = g; }
    public void lancerDebutJeu() {
    	Sauvegarde sauvegarde = new Sauvegarde(this);
    	if(sauvegarde.Deserialize(this)!=null) {
    		Jeu jeu = sauvegarde.Deserialize(this);
    		this.partie = jeu.getPartie();
    		this.zoneCourante = jeu.GetZoneCourante1();
    		gui.addNameFrame(partie.getJoueur().getNom());
    		afficherMessageDeBienvenue();
    	} else {
    		partie = new Partie();
    	    creerCarte();
    	    afficherMessageDeBienvenue();
    		gui.afficher("Bienvenue ! Rentrez votre pr�nom \n");
    	}
    }
    public void creationJoueur(String nomJoueur) {
    	partie.setJoueur(new Joueur(nomJoueur));
    	gui.afficher("Ton nom est donc"+nomJoueur);
    	gui.addNameFrame(nomJoueur);
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
    	ArrayList<Quete> quetes = constructorOfMap.creerLesQuetesDuJeu();
    	zones = constructorOfMap.miseEnPlaceDesQueteurs(zones,quetes);
    	partie.setSalleDeRepos(constructorOfMap.ajouterSortieZoneDeRepos(zones.get(1), "SUD", vaisseau));
    	partie.setsceneFinal(zones.get(zones.size()-1));
    	zoneCourante = espace.get(0).getZones().get(0); 	
    }
    

    private void afficherLocalisation() {
    	  	gui.afficheImage(zoneCourante.nomImage());	
            gui.afficher( zoneCourante.descriptionLongue());
            gui.afficherElementZone(zoneCourante.getAnimauxDansLazone(),zoneCourante.getPersonnageDansLaZone());
            gui.afficherBoutonSortie(zoneCourante.getSorties());
            gui.afficher();
    }

    private void afficherMessageDeBienvenue() {
    	gui.afficher("Bienvenue !");
    	gui.afficher();
        gui.afficher("Tapez '?' pour obtenir de l'aide.");
        gui.afficher();
        gui.afficheImage(zoneCourante.nomImage());	
        gui.afficherElementZone(zoneCourante.getAnimauxDansLazone(),zoneCourante.getPersonnageDansLaZone());
        afficherLocalisation();
    }
    
    public void traiterCommande(String commandeLue) {
//    	gui.afficher( "> "+ commandeLue + "\n");
//        switch (commandeLue.toUpperCase()) {
//        case "?" : case "AIDE" : 
//            afficherAide(); 
//        	break;
//        case "N" : case "NORD" :
//        	allerEn( "NORD"); 
//        	break;
//       case "S" : case "SUD" :
//        	allerEn( "SUD"); 
//        	break;
//        case "E" : case "EST" :
//        	allerEn( "EST"); 
//        	break;
//        case "O" : case "OUEST" :
//        	allerEn( "OUEST"); 
//        	break;
//        case "Q" : case "QUITTER" :
//        	terminer();
//        	break;
//       	default : 
//            gui.afficher("Commande inconnue");
//            break;
//        }
    	
    }

    public void captureDeMouton(Mouton mouton) {
    	WorldBuilder constructorOfMap = new WorldBuilder();
    	partie.getJoueur().prendreObjet(mouton);
    	constructorOfMap.suppresionDuMouton(zoneCourante, mouton);
    	
    }
    public void allerEn(String direction) {
    	Zone nouvelle = zoneCourante.obtientSortie(direction);
    	if ( nouvelle == null ) {
        	gui.afficher( "Pas de sortie " + direction);
    		gui.afficher();
    	}
        else {
        	zoneCourante = nouvelle;
        	gui.afficher(zoneCourante.descriptionLongue());
        	gui.afficher();
            gui.afficheImage(zoneCourante.nomImage());	
            gui.afficherBoutonSortie(zoneCourante.getSorties());
            gui.afficherElementZone(zoneCourante.getAnimauxDansLazone(),zoneCourante.getPersonnageDansLaZone());
        }
    }
    public void allerEn(Zone nouvelleZone) {
    	zoneCourante = nouvelleZone;
    	gui.afficher(zoneCourante.descriptionLongue());
    	gui.afficher();
        gui.afficheImage(zoneCourante.nomImage());	
        gui.afficherBoutonSortie(zoneCourante.getSorties());
        gui.afficherElementZone(zoneCourante.getAnimauxDansLazone(),zoneCourante.getPersonnageDansLaZone());
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
    				gui.afficher(queteur.parler(getPartie().getJoueur()));
    				if(queteur.quete() instanceof capturerMouton) {
    					WorldBuilder mouton = new WorldBuilder();
    					mouton.positionMouton(partie.zones(),3);
    				}
    				partie.setQuete((queteur).quete());
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
//	private void terminer() {
//    	gui.afficher( "Au revoir...");
//    	gui.enable( false);
//    }
    public Zone getZoneCourante() {
    	return this.getZoneCourante();
    }
    public Zone GetZoneCourante1()
    {
    	return this.zoneCourante;
    }
    public void Sauvegarde()
    {
    	Sauvegarde save = new Sauvegarde(this);
    	save.Serialize(this);
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
    	zoneCourante = new Zone("","","");
    	gui.afficher("Vous êtes mort. Sans votre aide, Dyspros continuera encore longtemps son règne de terreur sur la galaxie...");
    	gui.afficheImage("zoneperdante.gif");
    	gui.afficherElementZone(new ArrayList<Mouton>(), new ArrayList<Personnage>());
    	gui.afficherBoutonSortie(zoneCourante.getSorties());
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
		if(!getPartie().getJoueur().alive) {
			perdu();
		}
	}
}
