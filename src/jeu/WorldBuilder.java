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
	/** Cette fonction va créer toutes les zones écrites sur un xml spécifique au jeu?
	 * @see espace1.xml
	 * @return toutes les zones du jeu
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
	/** Cette fonction rajoute toutes les sorties au zones existantes.
	 * @param toutesLesZones
	 * @return ArrayList<Zone> représentant toutes les zones du jeu avec leur sortie
	 */
	public static ArrayList<Zone> ajouterToutesLesSorties(ArrayList<Zone> toutesLesZones) {
		ReaderXML spaceReader = new ReaderXML("espace1.xml");
		NodeList zones = spaceReader.getDocument().getElementsByTagName("zone");
		//Cette structure permet de ne pas se soucier si les zones existent déjà au moment 
		//de créer les zones puisqu'elles sont déjà toutes créées
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
	/** Cette fonction retourne toutes les planètes du jeu avec leur zone
	 * @param toutesLesZones -> ArrayList<Zone>
	 * @return toutesLesPlanètes -> Retourne toutes les planètes du jeu
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

	/** Cette méthode ajoute toutes les sorties au vaisseau
	 * @param vaisseau ( Zone )
	 * @param espace (ArrayList<Planete>)
	 * @return Vaisseau (Zone) avec ses nouvelles sorties
	 */
	public static Zone ajouterLesSortiesAuVaisseau(Zone vaisseau, ArrayList<Planete> espace) {
		//La structure planète permet d'ajouter logiquement et par une simple boucle la première zone 
		//de chaque planète souhaitée pour qu'on est accès à chaque spatioport
		for(int i=0;i<espace.size()-1;i++) {
			vaisseau.ajouteSortie(Sortie.values()[i], espace.get(i).getZones().get(0));
		}
		return vaisseau;
	}

	/** Cette fonction rajoute uniquement la sortie à la zone du vaisseua dédiée aux alliés
	 * @param Zone Salle de repos
	 * @param direction de la sorrue
	 * @param Zone de la sortie
	 * @return La salle de repos retournée avec sa sortie vers le vaisseau
	 */
	public static Zone ajouterSortieZoneDeRepos(Zone repos,String direction,Zone sortie) {
		repos.ajouteSortie(Sortie.valueOf(direction), sortie);
		return repos;
	}

	/**Cette méthode positionne les moutons sur la carte
	 * @param ArrayList<Zone> possible d'apparition des moutons
	 * @param Int nombre de moutons 
	 * @return ArrayList<Zone> retourne les zones après le placement des moutons
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
	/** Cette méthode crée tous les alliés lu à partir d'un fichier XML
	 * @param Le nom du fichier
	 * @return ArrayList<Allies> avec tous les alliés de la partie
	 */
	public static ArrayList<PersonnageActifs> creerTousLesAllies(String nomFichier) {
		ReaderXML persoReader = new ReaderXML(nomFichier);
		ArrayList<PersonnageActifs> tousLesPersos = new ArrayList<PersonnageActifs>();
		NodeList allies = persoReader.getDocument().getElementsByTagName("allie");
		Random rand = new Random();
		// Génére les chiffres aléatoires des alliées avec une hashset pour ne pas avoir de doublon
		HashSet hs = new HashSet();
		while(hs.size()<3){
			int num = (int) ((Math.random()*(allies.getLength()-1)));
			hs.add(num);
		}
		Iterator it=hs.iterator();
		while(it.hasNext()){
			// Crée les alliés au numéros tirés au sort 
			Element allie = (Element) allies.item((int)it.next());
			String nom = allie.getElementsByTagName("nom").item(0).getTextContent();
			String image = allie.getElementsByTagName("image").item(0).getTextContent();
			String dialogue = allie.getElementsByTagName("dialogue").item(0).getTextContent();
			Role role = Role.valueOf(allie.getElementsByTagName("ROLE").item(0).getTextContent());
			Integer pv = Integer.valueOf(allie.getElementsByTagName("PV").item(0).getTextContent());
			Integer pp = Integer.valueOf(allie.getElementsByTagName("PP").item(0).getTextContent());
			tousLesPersos.add(new PersonnageActifs(nom,null,image,pv,pp,role,dialogue));
		}
		return tousLesPersos;
	}
	/** Cette méthode place les alliés sur la carte
	 * @param ArrayList<Zone> représentant l'espace actuelle sans alliés
	 * @param ArrayList<Allies> tous les alliés créé avec la méthode "creerTousLesAllies"
	 * @return ArrayList<Zone> qui représente le nouvel espace avec les alliés
	 */
	public static ArrayList<Zone> positionneAlliees(ArrayList<Zone> zones, ArrayList<PersonnageActifs> tousLesAllies)
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
	/**Supprime les moutons
	 * @param Zone où se trouve le mouton
	 * @param Le mouton a supprimé - Mouton -
	 * @return La Zone sans le mouton
	 */
	public static Zone suppresionDuMouton(Zone zone,Mouton mouton) {
		zone.getAnimauxDansLazone().remove(mouton);
		return zone;
	}
	
	/** Crée et place les quêteurs du jeu
	 * @param toutesLesZones ArrayList<Zone>
	 * @param toutesLesQuetes ArrayList<Quete>
	 * @return ArrayList<Zone> représente l'espace avec les quêteurs en place
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
	
	/** Crée @see miseEnPlaceDesQueteurs
	 * @return le guide de la galaxie
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
	
	/** Crée tous les objets récompenses des quêtes
	 * @return ArrayList<Objets>
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
	
	/** Instancie les classes d'énigmes
	 * @param ArrayList<Objets> toutes les objets récompense
	 * @return Toutes les quêtes disponibles dans le jeu ArrayList<Quete>
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
	/** Cette méthode réinitialise les moutons dans les zones
	 * @param ArrayList<Zone>
	 */
	public static synchronized void reInitSheep(ArrayList<Zone> zones) {
		// Cette méthode étant suceptible d'être utilisé par plusieurs Thread, on a essayé de la sécuriser
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

