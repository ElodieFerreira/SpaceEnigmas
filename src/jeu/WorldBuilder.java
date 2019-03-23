package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WorldBuilder {
	/** This function create all zones of the games. Even zones like spatialship and friend room. All these informations are selected on XML file in
	 * already written on the function. Every zones are written except spatialship.
	 * @see espace1.xml
	 * @return all zones of the game
	 */
	public static ArrayList<Zone> creerToutesLesZones() {
		// Création du vaisseau : 
		ArrayList<Zone> zones = new ArrayList<Zone>();
		zones.add(new Zone("Vaisseau","vaisseau.png","Voici votre vaisseau... Il vous permettra de vous balader de planète en planète"));
		// Création de la zone des alliés
		zones.add(new Zone("Mes amis","zoneVaisseauAmis.jpg","Tous vos amis attendent avec vous pour se battre contre Dyspros!"));
		//Création des autres zones
		ReaderXML spaceReader = new ReaderXML("espace1.xml");
		NodeList nodeZones = spaceReader.getDocument().getElementsByTagName("zone");
		for(int i=0;i<nodeZones.getLength();i++) {
			Element zone = (Element) nodeZones.item(i);
			String nomZone = zone.getElementsByTagName("nom").item(0).getTextContent();
			String descriptionZone = zone.getElementsByTagName("description").item(0).getTextContent();
			String nomImage = zone.getElementsByTagName("image").item(0).getTextContent();
			Integer indexZone = Integer.valueOf(zone.getAttribute("index"));
			zones.add(indexZone,new Zone(nomZone,nomImage,descriptionZone));
		}
		return zones;
	}	
	/** This function add all exits of each zone. It the simplest in order to by sure that each zone's exit 
	 * already exist.
	 * @param toutesLesZones
	 * @return It will return Zone with exit implemented.
	 */
	public static ArrayList<Zone> ajouterToutesLesSorties(ArrayList<Zone> toutesLesZones) {
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
	/** This function create all planets. When the method read the XML and the different planet, it add zones to it planet.
	 * @param toutesLesZones -> All zones of the game already written. 
	 * @return all the planet, it will be our map during the game
	 */
	public static ArrayList<Planete> creerLesPlanetes(ArrayList<Zone> toutesLesZones) {
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
				Element zone = (Element) zonesPlanete.item(i1);
				Integer indexZoneToAdd = Integer.valueOf(zone.getAttribute("index"));
				planete.ajouterZone(toutesLesZones.get(indexZoneToAdd));
			}
			planetesRetour.add(planete);
		}
		return planetesRetour;
	}

	/** This method put all the exit possible at the spatialship.
	 * @param vaisseau
	 * @param espace
	 * @return spatialship
	 */
	public static Zone ajouterLesSortiesAuVaisseau(Zone vaisseau, ArrayList<Planete> espace) {
		for(int i=0;i<espace.size()-1;i++) {
			vaisseau.ajouteSortie(Sortie.values()[i], espace.get(i).getZones().get(0));
		}
		return vaisseau;
	}

	/** This function called "ajouterSortie" in order to add spatialship as a possible exit to
	 * friends room. In the future, this method can be used to add more exit if our galaxy grow up
	 * @param repos
	 * @param direction
	 * @param sortie
	 * @return modified version of friends room with the exit.
	 */
	public static Zone ajouterSortieZoneDeRepos(Zone repos,String direction,Zone sortie) {
		repos.ajouteSortie(Sortie.valueOf(direction), sortie);
		return repos;
	}

	/**This method make appears sheep on some zones selected randomly
	 * @param ArrayList<Zone> where you want to make spawn all sheeps
	 * @param nbMouton represents how much moutons you want to spawn in your 
	 * space
	 * @return your new space with sheep added
	 */
	public static ArrayList<Zone> positionnerMouton(ArrayList<Zone> zone, int nbMouton)
	{
		HashSet<Integer> hs=new HashSet<Integer>();

		while(hs.size()<nbMouton){
			int nbaleat = 2 + (int)(Math.random()*((zone.size()-3)));
			hs.add(nbaleat);
			System.out.println(nbaleat+"MOUTON EN PLACE");
		}
		Iterator<Integer> it=hs.iterator();

		while(it.hasNext()){
			zone.get((int)it.next()).getAnimauxDansLazone().add(new Mouton());
		}
		return zone ;
	}
	/** This method read some futur friends in your game thanks to an XML file and generate them.
	 * @param name of your file
	 * @return the ArrayList<Allies> of your new friends.
	 */
	public static ArrayList<Allies> creerTousLesAllies(String nomFichier) {
		ReaderXML persoReader = new ReaderXML(nomFichier);
		ArrayList<Allies> tousLesPersos = new ArrayList<Allies>();
		NodeList allies = persoReader.getDocument().getElementsByTagName("allie");
		Random rand = new Random();
		HashSet hs = new HashSet();
		while(hs.size()<3){
			int num = (int) ((Math.random()*(allies.getLength()-1)));
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
	/** This method will place your friends in the map except in zones defined in the function ( Xénémos, spatialship...)
	 * @param zones represents the map actually of your entire space
	 * @param tousLesAllies
	 * @return ArrayList<Zone> wich represents your new space with your friends implemented.
	 */
	public static ArrayList<Zone> positionneAlliees(ArrayList<Zone> zones, ArrayList<Allies> tousLesAllies)
	{	
		HashSet hs=new HashSet();
		while(hs.size()<tousLesAllies.size()){
			Random rand = new Random();
			int nbaleat = 2 + (int)(Math.random()*((zones.size()-4)));
			hs.add(nbaleat);
		}
		Iterator it=hs.iterator();
		int i = 0;
		while(it.hasNext()){
			zones.get((int)it.next()).getPersonnageDansLaZone().add(tousLesAllies.get(i));
			i++;
		}
		return zones;
	}
	/** Deleted the sheep from a specific zone
	 * @param zone where you want to delete the sheep
	 * @param the sheep you want to delete
	 * @return the zone without your sheep
	 */
	public static Zone suppresionDuMouton(Zone zone,Mouton mouton) {
		zone.getAnimauxDansLazone().remove(mouton);
		return zone;
	}
	
	/** Read all the character who gives enigmas and place them
	 * @param toutesLesZones 
	 * @param toutesLesQuetes
	 * @return your space with your characters
	 */
	public static ArrayList<Zone> miseEnPlaceDesQueteurs(ArrayList<Zone> toutesLesZones,ArrayList<Quete> toutesLesQuetes) {
		ReaderXML queteurReader = new ReaderXML("queteurs.xml");
		NodeList queteursNode = queteurReader.getDocument().getElementsByTagName("queteur");
		for(int i=0;i<queteursNode.getLength()-1;i++) {
			// Selection du quêteurs 
			Element queteurElement = (Element) queteursNode.item(i);
			// Selection de la zone de spawn
			Integer indexZone = Integer.valueOf(queteurElement.getAttribute("index"));
			// Selection des données propres aux personnages
			String nom = queteurElement.getElementsByTagName("nom").item(0).getTextContent();
			String image = queteurElement.getElementsByTagName("image").item(0).getTextContent();
			// Creation du queteur
			Queteur queteur = new Queteur(nom," ",image);

			// Recuperation des dialogues
			String dialoguesQueteEnCours = queteurElement.getElementsByTagName("queteDejaEnCours").item(0).getTextContent();
			String dialogueLancementQuete = queteurElement.getElementsByTagName("avantQuete").item(0).getTextContent();
			ArrayList<String> dialoguePendantQuete = new ArrayList<String>();
			NodeList dialoguePendantNode = queteurElement.getElementsByTagName("pendantQuete");
			for(int j=0;j<dialoguePendantNode.getLength();j++) {
				Element dialogue = (Element) dialoguePendantNode.item(j);
				dialoguePendantQuete.add(dialogue.getTextContent());
			}
			String dialogueFinQuete = queteurElement.getElementsByTagName("aprèsQuete").item(0).getTextContent();
			String remerciements = queteurElement.getElementsByTagName("remerciement").item(0).getTextContent();
			queteur.setAllDialogues(dialoguesQueteEnCours, dialogueLancementQuete, dialoguePendantQuete, dialogueFinQuete, remerciements);
			queteur.setQuete(toutesLesQuetes.get(i));
			// mise en place du queteur dans sa zone
			toutesLesZones.get(indexZone).getPersonnageDansLaZone().add(queteur);
		}
		return toutesLesZones;
	}
	
	/** Create a specific character from the same xml as @see miseEnPlaceDesQueteurs
	 * @return the guide of your game
	 */
	public static Queteur CreerGuide() {
		ReaderXML queteurReader = new ReaderXML("queteurs.xml");
		NodeList queteursNode = queteurReader.getDocument().getElementsByTagName("queteur");
		Element guide = (Element) queteursNode.item(queteursNode.getLength()-1);
		String name = guide.getElementsByTagName("nom").item(0).getTextContent();
		String image = guide.getElementsByTagName("image").item(0).getTextContent();
		String avantQuete = guide.getElementsByTagName("avantQuete").item(0).getTextContent();
		String apresQuete = guide.getElementsByTagName("aprèsQuete").item(0).getTextContent();
		ArrayList<String> pendantQueteArrayList = new ArrayList<String>();
		NodeList pendantQuete = guide.getElementsByTagName("pendantQuete");
		for(int i= 0;i<pendantQuete.getLength();i++) {
			String dialogue = pendantQuete.item(i).getTextContent();
			pendantQueteArrayList.add(dialogue);
		}
		Queteur mentris = new Queteur(name," ",image);
		mentris.setAllDialogues(null, avantQuete, pendantQueteArrayList, apresQuete, null);
		return mentris;
	}
	
	/** Create all objects given after an enigma
	 * @return an ArrayList<Objets>
	 */
	public static ArrayList<Objets> creerLesObjets() {
		ArrayList<Objets> objets = new ArrayList<Objets>();
		ReaderXML objetsReader = new ReaderXML("Objet.xml");
		NodeList objetsNode = objetsReader.getDocument().getElementsByTagName("minerai");
		for(int i=0;i<objetsNode.getLength();i++) {
			Element objet = (Element) objetsNode.item(i);
			String nom = objet.getElementsByTagName("nom").item(0).getTextContent();
			String description = objet.getElementsByTagName("description").item(0).getTextContent();
			String image = objet.getElementsByTagName("image").item(0).getTextContent();
			objets.add(new Objets(nom,description,image));
		}
		return objets;
	}
	
	/** Create all instance of enigmas
	 * @param all recompenses available in the game
	 * @return all the quete available in the game
	 */
	public static ArrayList<Quete> creerLesQuetesDuJeu(ArrayList<Objets> recompenses) {
		ArrayList<Quete> quetes = new ArrayList<Quete>();
		//Creation de la premiere quete : Mouton
		capturerMouton capture = new capturerMouton(recompenses.get(0), 3);
		quetes.add(capture);
		//Creation de la seconde quete : Pendu
		ArrayList<String> mots = new ArrayList<String>();
		ReaderXML motPendu = new ReaderXML("mots.xml");
		NodeList motsPendus = motPendu.getDocument().getElementsByTagName("niveau");
		for(int i=0;i<motsPendus.getLength();i++) {
			Element niveau = (Element) motsPendus.item(i);
			NodeList motsDuNiveau = niveau.getElementsByTagName("mot");
			Random r = new Random();
			int intRand = r.nextInt(motsDuNiveau.getLength());
			String mot = motsDuNiveau.item(intRand).getTextContent();
			mots.add(mot);
		}
		Pendu pendu = new Pendu(recompenses.get(1),mots);
		quetes.add(pendu);
		//Creation de la troisieme quete : Enigme
		ArrayList<String> questionArray = new ArrayList<String>();
		ArrayList<String> reponseArray = new ArrayList<String>();
		ArrayList<String> indiceArray = new ArrayList<String>();
		ReaderXML enigmesXML = new ReaderXML("enigme.xml");
		NodeList enigmesComplete = enigmesXML.getDocument().getElementsByTagName("niveau");
		for(int i=0;i<enigmesComplete.getLength();i++) {
			Element niveau = (Element) enigmesComplete.item(i);
			NodeList enigmesDuNiveau = niveau.getElementsByTagName("enigme");
			Random r = new Random();
			System.out.println(enigmesDuNiveau.getLength());
			int intRand = r.nextInt(enigmesDuNiveau.getLength());
			Element enigme = (Element) enigmesDuNiveau.item(intRand);
			String question = enigme.getElementsByTagName("question").item(0).getTextContent();
			questionArray.add(question);
			String reponse = enigme.getElementsByTagName("reponse").item(0).getTextContent();
			reponseArray.add(reponse);
			String indice = enigme.getElementsByTagName("indice").item(0).getTextContent();
			indiceArray.add(indice);
		}
		EnigmeTextuel enigme = new EnigmeTextuel(recompenses.get(2), questionArray, reponseArray, indiceArray);
		quetes.add(enigme);
		return quetes;
	}
	/** This method is used to reinit each zone of a game without any sheep. It is used when we want to make
	 * move the sheep. Synchronized to assure that only one thread can run this method in the same time if we
	 * want to use it in several thread in the future
	 * @param zones
	 */
	public static synchronized void reInitSheep(ArrayList<Zone> zones) {
		ArrayList<Zone> zonesArrayList = zones;
		ArrayList<Mouton> moutons = new ArrayList<Mouton>();
		Lock verrou = new ReentrantLock();
		verrou.lock();
		for(Zone zone : zonesArrayList) {
			zone.setArrayListMouton(new ArrayList<Mouton>());
		}
		verrou.unlock();
	}
}

