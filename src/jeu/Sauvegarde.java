package jeu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Sauvegarde
{
	/**
	 * @param partieCourante
	 */
	public static void Serialize(Partie partieCourante) {
		try 
		{
			FileOutputStream file = new FileOutputStream("src/data/sauvegarde.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			System.out.println("Preparation de la serealisation de la zone");
			out.writeObject(partieCourante);
			System.out.println("La zone a bien été serealisé");
			out.close();
			file.close();
		}
		catch(IOException IOE)
		{
			//System.err.println("IOE Exception trouve ");
			//IOE.printStackTrace(System.err);
		}
	}

	
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
	
	
}
