package jeu;

import java.util.Scanner;

public class EnigmePereRoufas {
	
	private String[] enoncerEnigme;
	private String[] repEnigme;
	private String[] indices;
	

	
	public EnigmePereRoufas(int nb)
	{
		this.enoncerEnigme = new String[nb];
		this.repEnigme = new String[nb];
		this.indices = new String[nb];
	}
	public String sujetEnigme(int idTab)
	{
		this.enoncerEnigme[0] = "Parfois mauvaise et redouté.  Elle peut faire des blessés. Qu’elle soit d’eau ou de reins. Elle est aussi mot de la fin. Qui est-elle ?";
		this.enoncerEnigme[1] = "Meilleure ville de France";
		this.enoncerEnigme[2] = "Capitale d'un pays";
		this.enoncerEnigme[3] = "Capitale de région";
		return this.enoncerEnigme[idTab];
		
		
	}

	
	public String reponseEnigme(int idTab)
	{
		this.repEnigme[0] = "LA CHUTE";
		this.repEnigme[1] = "TOULON";
		this.repEnigme[2] = "PARIS";
		this.repEnigme[3] = "MARSEILLE";
		
		return this.repEnigme[idTab];
	}
	public String indiceEnigme(int idTab)
	{
		this.indices[0] = "Synonyme de Tomber";
		this.indices[1] = "Meilleure equipe de rugby";
		this.indices[2] = "dispose d'un obélisque";
		this.indices[3] = "Bonne mère";
		
		return this.indices[idTab];
	}
	
	public String testreponse(int idTab,int cpt)
	{
		if(cpt>=2)
		{
			System.out.println("voici un indice pour vous aider\n"+this.indiceEnigme(idTab));
		}

		
		Scanner rep = new Scanner(System.in);
		System.out.println("Tapez vore réponse");
		String str = rep.nextLine();
		return str;
	}
	
	public boolean bonneReponse(String reponse, int idTab)
	{
		if(reponse.equals(this.reponseEnigme(idTab)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
	
	
