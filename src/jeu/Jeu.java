package jeu;

import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Jeu implements Serializable {
	
    private GUI gui; 
	private Zone zoneCourante;
    private Partie partie;
    
    public Jeu() {
        creerCarte();
        gui = null;
    }
    public Partie getPartie() {
    	return partie;
    }
    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    public void lancerDebutJeu() {
    	if(false) {
    		
    	} else {
    		partie = new Partie();
    		gui.afficher("Bienvenue ! Rentrez votre prénom \n");
    	}
    }
    public void creationJoueur(String nomJoueur) {
    	partie.setJoueur(new Joueur(nomJoueur));
    	gui.afficher("Ton nom est donc"+nomJoueur);
    	gui.addNameFrame(nomJoueur);
    }
    private void creerCarte() {
    	ArrayList<Zone> zones = new ArrayList<Zone>();
    	zones.add(new Zone("Vaisseau","vaisseau.png","Un petit vaisseau"));
    	zones = Zone.creerToutesLesZones(zones);
    	zones.addAll(Zone.ajouterToutesLesSorties(zones));
    	ArrayList<Planete> cartes = Planete.creerLesPlanetes(zones);
    	zones.get(0).ajouteSortie(Sortie.valueOf("SUD"), cartes.get(0).getZones().get(0));
    	zones.get(0).ajouteSortie(Sortie.valueOf("NORD"), cartes.get(1).getZones().get(0));
    	zones.get(0).ajouteSortie(Sortie.valueOf("EST"), cartes.get(2).getZones().get(0));
    	zones.get(0).ajouteSortie(Sortie.valueOf("OUEST"), cartes.get(3).getZones().get(0));
    	zoneCourante = zones.get(1);
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
        afficherLocalisation();
        gui.afficheImage(zoneCourante.nomImage());	
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
        gui.afficher("Les commandes autorisÃ©es sont :");
        gui.afficher();
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher();
    }
    
    public void allerEn(String direction) {
    	Zone nouvelle = zoneCourante.obtientSortie( direction);
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
        }
    }
    
    private void terminer() {
    	gui.afficher( "Au revoir...");
    	gui.enable( false);
    }
    public Zone getZoneCourante() {
    	return this.getZoneCourante();
    }
}
