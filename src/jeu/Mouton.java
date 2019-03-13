package jeu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
public class Mouton extends Objets  {
	private static final long serialVersionUID = 2934790090291592942L;
	public static ArrayList<Zone> PositionMouton(ArrayList<Zone> zone, int nbMouton)
	{
		for(int i=0;i<3;i++)
		{
			Random rand = new Random();
			System.out.println(zone.size());
			int nbaleat = rand.nextInt((zone.size()-2-1));
			System.out.println(zone.get(nbaleat).getNom());
			zone.get(nbaleat).getAnimauxDansLazone().add(new Mouton());
			
		}
		return zone ;
	}

	public Mouton() {
		super("Mouton", "Mouton du berger", "mouton.gif");
	}
	public String getImage() {
		return super.getNomImage();
	}
	
}
