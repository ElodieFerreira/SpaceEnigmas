package jeu;

import java.util.ArrayList;
import java.io.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Fayda
 *
 */
public class Jeu implements Serializable {
	
    private GUI gui; 
	private Zone zoneCourante;
    private Partie partie;
    
    public Jeu() {
        gui = null;
        partie = new Partie();
        creerCarte();
    }
    public Partie getPartie() {
    	return partie;
    }
    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    public void lancerDebutJeu() {
    	if(false) {
    		
    	} else {
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
    	zones = constructorOfMap.positionMouton(zones, 3);
    	ArrayList<Allies> tousLesAllies = constructorOfMap.creerTousLesAllies("allies.xml");
    	zones = constructorOfMap.positionneAlliees(zones, tousLesAllies);
    	ArrayList<Quete> quetes = constructorOfMap.creerLesQuetesDuJeu();
    	zones = constructorOfMap.miseEnPlaceDesQueteurs(zones,quetes);
    	partie.setSalleDeRepos(constructorOfMap.ajouterSortieZoneDeRepos(zones.get(1), "SUD", vaisseau));
    	zoneCourante = espace.get(0).getZones().get(0); 	
    }
    

    private void afficherLocalisation() {
            gui.afficher( zoneCourante.descriptionLongue());
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
    	gui.afficher( "> "+ commandeLue + "\n");
        switch (commandeLue.toUpperCase()) {
        case "?" : case "AIDE" : 
            afficherAide(); 
        	break;
        case "N" : case "NORD" :
        	allerEn( "NORD"); 
        	break;
       case "S" : case "SUD" :
        	allerEn( "SUD"); 
        	break;
        case "E" : case "EST" :
        	allerEn( "EST"); 
        	break;
        case "O" : case "OUEST" :
        	allerEn( "OUEST"); 
        	break;
        case "Q" : case "QUITTER" :
        	terminer();
        	break;
       	default : 
            gui.afficher("Commande inconnue");
            break;
        }
    }

    private void afficherAide() {
        gui.afficher("Etes-vous perdu ?");
        gui.afficher();
        gui.afficher("Les commandes autorisées sont :");
        gui.afficher();
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher();
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
    		if(getPartie().queteEnCours()==null) {
    			gui.afficher(((Queteur) personnage).parler(getPartie().getJoueur()));
    			partie.setQuete(((Queteur) personnage).quete());
    		} else if(getPartie().queteEnCours() instanceof capturerMouton && ((Queteur) personnage).quete()==getPartie().queteEnCours() ) {
    			queteDesMoutons((Queteur) personnage);
    		}
    	} 
    }
    private void queteDesMoutons(Queteur personnage) {
    	if(verifierCaptureMouton()) {
			Mouton mouton = getPartie().getJoueur().recupererMouton();
			personnage.prendre(getPartie().getJoueur().donnerObjet(mouton));
			gui.afficher("Merci");
    	} else {
			gui.afficher("tu n'as pas assez de mouton...");
		}
    }
    private boolean verifierCaptureMouton() {
		// TODO Auto-generated method stub
		int nbMouton = ((capturerMouton) getPartie().queteEnCours()).nbMouton();
		int cptMouton =0;
		for(Objets obj : getPartie().getJoueur().inventaire) {
			if(obj instanceof Mouton) {
				cptMouton++;
			}
		}
		return cptMouton==nbMouton;
	}
	private void terminer() {
    	gui.afficher( "Au revoir...");
    	gui.enable( false);
    }
    public Zone getZoneCourante() {
    	return this.getZoneCourante();
    }
    public Zone GetZoneCourante1()
    {
    	return this.zoneCourante;
    }
}
