package jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Jeu {
	
    private GUI gui; 
	private Zone zoneCourante;
    
    public Jeu() {
        creerCarte();
        gui = null;
    }

    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    
    private void creerCarte() {
    	ArrayList<Zone> zones = new ArrayList<Zone>();
    	zones.add(new Zone("Vaisseau","Un petit vaisseau","vaisseau.png"));
    	zones = Zone.creerToutesLesZones(zones);
    	zones.addAll(Zone.ajouterToutesLesSorties(zones));
    	ArrayList<Planete> cartes = Planete.creerLesPlanetes(zones);
//        Zone[] zones = new Zone[4];
        //zones.add(new Zone("XpÈria","vaisseau.png","a"));
        //zones.add(new Zone("Lorniala Spatioport, c'est un peu mÈlancolique comme nom je trouve","p1.PNG","C'est un peu mÈlancolique comme nom je trouve"));
        //zones.get(0).ajouteSortie(Sortie.NORD,zones.get(1));
        //zones.get(1).ajouteSortie(Sortie.SUD, zones.get(0));
//        zones[0] = new Zone("le couloir", "test.gif","a");
//        zones[1] = new Zone("l'escalier", "test.gif","a" );
//        zones[2] = new Zone("la grande salle", "champ.gif","a" );
//        zones[3] = new Zone("la salle √† manger", "ZoneCombat.gif","a" );
//        zones[0].ajouteSortie(Sortie.EST, zones[1]);
//        zones[1].ajouteSortie(Sortie.OUEST, zones[0]);
//        zones[1].ajouteSortie(Sortie.EST, zones[2]);
//        zones[2].ajouteSortie(Sortie.OUEST, zones[1]);
//        zones[3].ajouteSortie(Sortie.NORD, zones[1]);
//        zones[1].ajouteSortie(Sortie.SUD, zones[3]);
        	zoneCourante = zones.get(2);
    }

    private void afficherLocalisation() {
            gui.afficher( zoneCourante.descriptionLongue());
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
        gui.afficher("Les commandes autoris√©es sont :");
        gui.afficher();
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher();
    }

    private void allerEn(String direction) {
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
        }
    }
    
    private void terminer() {
    	gui.afficher( "Au revoir...");
    	gui.enable( false);
    }
}
