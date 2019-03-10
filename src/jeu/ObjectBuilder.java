package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ObjectBuilder {
	public ArrayList<Zone> creerToutesLesZones() {
		// Cr�ation du vaisseau : 
		ArrayList<Zone> zones = new ArrayList<Zone>();
    	zones.add(new Zone("Vaisseau","vaisseau.png","test.gif"));
    	//Cr�ation des autres zones
    	ReaderXML spaceReader = new ReaderXML("espace1.xml");
        NodeList nodeZones = spaceReader.getDocument().getElementsByTagName("zone");
        for(int i=0;i<nodeZones.getLength();i++) {
        	Element zone = (Element) nodeZones.item(i);
        	String nomZone = zone.getElementsByTagName("nom").item(0).getTextContent();
        	String descriptionZone = zone.getElementsByTagName("description").item(0).getTextContent();
        	String nomImage = zone.getElementsByTagName("image").item(0).getTextContent();
        	Integer indexZone = Integer.valueOf(zone.getAttribute("index"));
        	System.out.println(indexZone);
        	zones.add(indexZone,new Zone(nomZone,nomImage,descriptionZone));
        }
    	return zones;
    }	
	public ArrayList<Planete> creerLesPlanetes(ArrayList<Zone> toutesLesZones) {
		ArrayList<Planete>  planetesRetour = new ArrayList<Planete>();
		ReaderXML planeteReader = new ReaderXML("espace1.xml");
        NodeList planetes = planeteReader.getDocument().getElementsByTagName("planete");
        for(int i=0;i<planetes.getLength();i++) {
        	Element planeteElement = (Element) planetes.item(i);
        	String nomPlanete = planeteElement.getElementsByTagName("nom").item(0).getTextContent();  
        	String description = planeteElement.getElementsByTagName("description").item(0).getTextContent();
        	NodeList zonesPlanete = planeteElement.getElementsByTagName("zone");
        	Planete planete = new Planete(nomPlanete, description);
        	for(int i1=0;i1<zonesPlanete.getLength();i1++) {
        		Element zone = (Element) zonesPlanete.item(0);
        		Integer indexZoneToAdd = Integer.valueOf(zone.getAttribute("index"));
        		planete.ajouterZone(toutesLesZones.get(indexZoneToAdd));
        	}
        	planetesRetour.add(planete);
        }
         for(Planete planete : planetesRetour) {
        	 System.out.println(planete.nom);
        	 System.out.println(planete.description);
        }
		return planetesRetour;
	}
	public ArrayList<Zone> ajouterToutesLesSorties(ArrayList<Zone> toutesLesZones) {
    	ReaderXML spaceReader = new ReaderXML("espace1.xml");
        NodeList zones = spaceReader.getDocument().getElementsByTagName("zone");
        for(int i=0;i<zones.getLength();i++) {
        	Element zone = (Element) zones.item(i);
        	Integer indexZoneDeDepart = Integer.valueOf(zone.getAttribute("index"));
        	NodeList sorties = zone.getElementsByTagName("sortie");
        	for(int j=0;j<sorties.getLength();j++) {
        		Element sortie = (Element)sorties.item(j);
        		String directionSortie = sortie.getTextContent();
        		Integer indexZoneSortie = Integer.valueOf(sortie.getAttribute("index"));
        		toutesLesZones.get(indexZoneDeDepart).ajouteSortie(Sortie.valueOf(directionSortie),toutesLesZones.get(indexZoneSortie));
        	}
        }
    	return toutesLesZones;
    }
	public Zone ajouterLesSortiesAuVaisseau(Zone vaisseau, ArrayList<Planete> espace) {
		for(int i=0;i<espace.size()-1;i++) {
			vaisseau.ajouteSortie(Sortie.values()[i], espace.get(i).getZones().get(0));
		}
		return vaisseau;
	}
	public ArrayList<Zone> positionMouton(ArrayList<Zone> zone, int nbMouton)
	{
		HashSet hs=new HashSet();

		while(hs.size()<nbMouton){
			Random rand = new Random();
			int nbaleat = rand.nextInt((zone.size()-2));
			hs.add(nbaleat);
		}
		Iterator it=hs.iterator();

		while(it.hasNext()){
			zone.get((int)it.next()).getAnimauxDansLazone().add(new Mouton());
		}
		return zone ;
	}
	public ArrayList<Allies> creerTousLesAllies(String nomFichier) {
		ReaderXML persoReader = new ReaderXML(nomFichier);
		ArrayList<Allies> tousLesPersos = new ArrayList<Allies>();
		NodeList allies = persoReader.getDocument().getElementsByTagName("allie");
		Random rand = new Random();
		HashSet hs = new HashSet();
		int nbaleat = rand.nextInt(5-3+1)+3;
		while(hs.size()<nbaleat){
			int num = rand.nextInt((allies.getLength()-1+1));
			System.out.println(num);
			hs.add(num);
		}
		Iterator it=hs.iterator();
		while(it.hasNext()){
		 Element allie = (Element) allies.item((int)it.next());
		 String nom = allie.getElementsByTagName("nom").item(0).getTextContent();
		 String image = allie.getElementsByTagName("image").item(0).getTextContent();
		 String dialogue = allie.getElementsByTagName("dialogue").item(0).getTextContent();
		 Role role = Role.valueOf(allie.getElementsByTagName("ROLE").item(0).getTextContent());
		 Integer pv = Integer.valueOf(allie.getElementsByTagName("PV").item(0).getTextContent());
		 Integer pp = Integer.valueOf(allie.getElementsByTagName("PP").item(0).getTextContent());
		 tousLesPersos.add(new Allies(nom,null,image,pv,pp,role,dialogue));
		}
		return tousLesPersos;
	}
	public ArrayList<Zone> positionneAlliees(ArrayList<Zone> zones, ArrayList<Allies> tousLesAllies)
	{	
		HashSet hs=new HashSet();

		while(hs.size()<tousLesAllies.size()){
			Random rand = new Random();
			int nbaleat = rand.nextInt((zones.size()-2));
			hs.add(nbaleat);
		}
		Iterator it=hs.iterator();
		int i = 0;
		while(it.hasNext()){
			zones.get((int)it.next()).getPersonnageDansLaZone().add(tousLesAllies.get(i));
			i++;
		}
		return zones ;

	}
	
}
