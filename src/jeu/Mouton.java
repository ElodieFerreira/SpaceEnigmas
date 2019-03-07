package jeu;
import java.util.ArrayList;
import java.util.Random;
public class Mouton extends Objets {

	public static ArrayList<Zone> PositionMouton(ArrayList<Zone> zone, int nbMouton)
	{
		for(int i=0;i<3;i++)
		{
			Random rand = new Random();
			int nbaleat = rand.nextInt((zone.size()-2));
			System.out.println(zone.get(nbaleat).getNom());
			zone.get(nbaleat).getAnimauxDansLazone().add(new Mouton());
			
		}
		return zone ;
	}

	public Mouton() {
		super("Mouton", "Mouton du berger", "MoutonpourGif.gif");
	}
	
}
