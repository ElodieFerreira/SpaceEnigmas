package jeu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Sauvegarde
{
	private static final String fileName ="mygame.ser";
	/* Test de sauvegarde de la zoneCourante private Jeu save;*/
	public static Partie save;
	// Création d'un constructeur qui initialise save à la valeur de la zone courante
//	public Sauvegarde(int test,int test2) 
//	{
//		this.val=test;
//		this.val1=test2;
//	}
	public Sauvegarde(Partie partieCourante)
	{
		save=partieCourante;
		//save.getPartie().getJoueur().setNom(nomJoueur);
	}
	/*public void Serialize(String nomJoueur,Zone test)
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
		
	}*/
	public static void Serialize(Partie partieCourante)
	{
		try 
		{
			FileOutputStream file = new FileOutputStream("src/data/sauvegarde.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			System.out.println("Preparation de la serealisation de la zone");
			/*String nom = zone.getNom();
			String description = zone.getDescription();
			String nomImage = zone.getNomImage();
			HashMap<String,Zone> sorties = zone.getSorties();
			ArrayList<Mouton> animauxDansLazone = zone.getAnimauxDansLazone();
			ArrayList<Personnage> personnageDansLaZone = zone.getPersonnageDansLaZone();
			out.writeObject(nom + "\n" + description+ "\n" + nomImage+ "\n" + sorties + "\n" + animauxDansLazone + "\n" + personnageDansLaZone);*/
			out.writeObject(partieCourante);
			System.out.println("La zone a bien été serealisé");
			out.close();
			file.close();
		}
		catch(IOException IOE)
		{
			System.err.println("IOE Exception trouve ");
			//IOE.printStackTrace(System.err);
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
	
	public static Partie Deserialize(Partie partieCourante)
	{
		// Maintenant on va procéder à la déséralisation
				try 
				{
					// Lecture du fichier 
					FileInputStream file = new FileInputStream("src/data/sauvegarde.txt");
					ObjectInputStream in = new ObjectInputStream(file);
					// Méthode pour déséréalizer un objet 
					save = (Partie)in.readObject();
					//nomJoueur= (String)in.readObject();
					in.close();
					file.close();
					System.out.println("L'objet a été déséréalizer");
					// printdata(test);
					return save;
				}
				catch(IOException e)
				{
					System.err.println("IOException 1 a été trouvé ");
//					e.printStackTrace();
					return null;
				}
				catch (ClassNotFoundException ex)
				{
//					System.err.println("ClassNotFoundException a été trouvé ");
//					ex.printStackTrace();
					return null;
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
