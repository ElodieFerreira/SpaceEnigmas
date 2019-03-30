package jeu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Sauvegarde
{
	/**
	 * <b> Serialize(Partie) : </b>
	 * This method is used to serialize the game
	 * @param partieCourante represents the current game
	 */
	public static void Serialize(Partie partieCourante) {
		try 
		{
			// Préparation de l'écriture de l'objet dans le fichier 
			FileOutputStream file = new FileOutputStream("src/data/sauvegarde.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			// Ecriture de l'objet dans le fichier
			out.writeObject(partieCourante);
			//Fermeture des streams 
			out.close();
			file.close();
		}
		catch(IOException IOE)
		{
			//System.err.println("IOE Exception trouve ");
			//IOE.printStackTrace(System.err);
		}
	}

	
	/**
	 * <b>Deserialize(Partie) : </b>
	 * This method is used to deserialize the current game that we already serialize
	 * @param partieCourante represents the current part
	 * @return The last part that the player has saved 
	 */
	public static Partie Deserialize(Partie partieCourante)
	{
		// Maintenant on va procéder à la déséralisation
				try 
				{
					// Lecture du fichier 
					FileInputStream file = new FileInputStream("src/data/sauvegarde.txt");
					ObjectInputStream in = new ObjectInputStream(file);
					// Méthode pour déséréalizer un objet
					Partie save = (Partie)in.readObject();
					in.close();
					file.close();
					return save;
				}
				catch(IOException e)
				{
					//System.err.println("IOException 1 a été trouvé ");
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
	
	
}
