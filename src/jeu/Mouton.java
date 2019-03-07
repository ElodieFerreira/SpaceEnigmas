package jeu;
import java.util.ArrayList;
import java.util.Random;
public class Mouton extends Objets {

	public ArrayList<Zone> PositionMouton(ArrayList<Zone> zone)
	{
		for(int i=0;i<3;i++)
		{
			Random rand = new Random();
			int nbaleat = rand.nextInt((zone.size()-1));
			zone.get(nbaleat);
			
		}
		return ;
	}
}
