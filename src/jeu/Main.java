package jeu;

public class Main {
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		GUI gui = new GUI( jeu);
		jeu.setGUI( gui);
		jeu.lancerDebutJeu();
		/*Sauvegarde save = new Sauvegarde(jeu);
		save.Serialize(jeu.GetZoneCourante1());
		System.out.println(jeu.GetZoneCourante1());
		save.Deserialize(jeu.GetZoneCourante1());*/
	}
}
