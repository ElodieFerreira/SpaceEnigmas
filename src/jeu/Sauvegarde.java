package jeu;

import java.io.*;
public class Sauvegarde implements Serializable
{
	private static final String fileName ="mygame.ser";
	
	// Test de sauvegarde de la zoneCourante
	//private Jeu save;
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
		Zone place = save.GetZoneCourante1();
	}
	public void Serialize(Zone test )
	{
		// Procédons maintenant à la Séréalization 
		try 
		{
			// Sauvegarder un objet dans le fichier 
			FileOutputStream file = new FileOutputStream("mygame.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			//System.out.println(out);
			// Méthode pour sérialiser un objet
			out.writeObject(test);
			System.out.println(test +"a été séréalisé");
			out.close();
			file.close();
			System.out.println("L'objet a bien été séréalisé");
			// printdata(test);
		}
		catch(IOException e)
		{
			System.out.println("IOException a été trouvé ");
		}
	}
	
	public void Deserialize(Zone test )
	{
		// Maintenant on va procéder à la déséralisation
				try 
				{
					// Lecture du fichier 
					
					FileInputStream file = new FileInputStream("mygame.txt");
					ObjectInputStream in = new ObjectInputStream(file);
					// Méthode pour déséréalizer un objet 
					test = (Zone)in.readObject();
					in.close();
					file.close();
					System.out.println(test + "L'objet a été déséréalizer");
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
