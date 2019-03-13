package jeu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Sauvegarde
{
	private static final String fileName ="mygame.ser";
	/* Test de sauvegarde de la zoneCourante private Jeu save;*/
	public transient Jeu save;
	// Création d'un constructeur qui initialise save à la valeur de la zone courante
//	public Sauvegarde(int test,int test2) 
//	{
//		this.val=test;
//		this.val1=test2;
//	}
	public Sauvegarde(Jeu JeuCourant)
	{
		save=JeuCourant;
		//save.getPartie().getJoueur().setNom(nomJoueur);
		Zone place = save.GetZoneCourante1();
	}
	public void Serialize(String nomJoueur,Zone test)
	{
		try 
		{
			FileOutputStream file= new FileOutputStream("myname.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			System.out.println("préparation Séréalisation nom");
			out.writeObject(nomJoueur+" "+test);
			System.out.println("Le nom du joueur  bien été séréaliser");
			out.close();
			file.close();
		}
		catch(IOException IOE)
		{
			System.out.println("IO EXCEPTION TROUVE");
		}
		
	}
	public void Serialize(Jeu zone)
	{
		try 
		{
			FileOutputStream file = new FileOutputStream("myzone.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			System.out.println("Preparation de la serealisation de la zone");
			/*String nom = zone.getNom();
			String description = zone.getDescription();
			String nomImage = zone.getNomImage();
			HashMap<String,Zone> sorties = zone.getSorties();
			ArrayList<Mouton> animauxDansLazone = zone.getAnimauxDansLazone();
			ArrayList<Personnage> personnageDansLaZone = zone.getPersonnageDansLaZone();
			out.writeObject(nom + "\n" + description+ "\n" + nomImage+ "\n" + sorties + "\n" + animauxDansLazone + "\n" + personnageDansLaZone);*/
			out.writeObject(zone);
			System.out.println("La zone a bien été serealisé");
			out.close();
			file.close();
		}
		catch(IOException IOE)
		{
			System.err.println("IOE Exception trouve ");
			IOE.printStackTrace(System.err);
		}
	}
	/*public void Serialize(Zone zone)
	{
		// Procédons maintenant à la Séréalization 
		try 
		{
			// Sauvegarder un objet dans le fichier 
			FileOutputStream file = new FileOutputStream("mygame2.txt");
			//FileOutputStream fileName = new FileOutputStream("name.txt");
			ObjectOutputStream out= new ObjectOutputStream(file);
			//ObjectOutputStream outName = new ObjectOutputStream(fileName);
			//System.out.println(out);
			// Méthode pour sérialiser un objet
			System.out.println("non");
			out.writeObject(zone);
			//outName.writeObject(nomJoueur);
			System.out.println(zone +" a été séréalisé");
			out.close();
			//outName.close();
			file.close();
			//fileName.close();
			System.out.println("L'objet a bien été séréalisé");
			// printdata(test);
		}
		catch(IOException e)
		{
			System.out.println("IOException a été trouvé ");
		}
	}*/
	
	public void Deserialize(Jeu zoneCourante)
	{
		// Maintenant on va procéder à la déséralisation
				try 
				{
					// Lecture du fichier 
					FileInputStream file = new FileInputStream("myzone.txt");
					ObjectInputStream in = new ObjectInputStream(file);
					// Méthode pour déséréalizer un objet 
					zoneCourante= (Jeu)in.readObject();
					//nomJoueur= (String)in.readObject();
					in.close();
					file.close();
					System.out.println(zoneCourante + "L'objet a été déséréalizer");
					// printdata(test);
				}
				catch(IOException e)
				{
					System.err.println("IOException 1 a été trouvé ");
					e.printStackTrace();
				}
				catch (ClassNotFoundException ex)
				{
					System.err.println("ClassNotFoundException a été trouvé ");
					ex.printStackTrace();
				}
	}
	public void Deserialize(String nomJoueur)
	{
		// Maintenant on va procéder à la déséralisation
				try 
				{
					// Lecture du fichier 
					FileInputStream file = new FileInputStream("mygame.txt");
					ObjectInputStream in = new ObjectInputStream(file);
					// Méthode pour déséréalizer un objet 
					nomJoueur = (String)in.readObject();
					//nomJoueur= (String)in.readObject();
					in.close();
					file.close();
					System.out.println(nomJoueur + "L'objet a été déséréalizer");
					// printdata(test);
				}
				catch(IOException e)
				{
					System.out.println("IOException 1 a été trouvé ");
				}
				catch (ClassNotFoundException ex)
				{
					System.out.println("ClassNotFoundException a été trouvé ");
				}
	}
	
	
}
