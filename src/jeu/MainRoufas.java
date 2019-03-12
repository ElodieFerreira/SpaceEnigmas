package EnigmeRoufas;

import java.util.Scanner;

public class MainRoufas {

	public static void main(String[] args) {
		EnigmePereRoufas essai = new EnigmePereRoufas(4);
		
		int idTab;
		idTab= (int)(java.lang.Math.random()*4);
	
		System.out.println(essai.sujetEnigme(idTab));
		int cpt=1;
		String str = essai.testreponse(idTab, cpt).toUpperCase();
		
		//System.out.println(str);
		//System.out.println(essai.reponseEnigme(idTab));
		
		
		int chance = 1;
		while(!essai.bonneReponse(str, idTab)&&chance<4)
		{
			
			System.out.println("Mauvaie réponse. Recommencez");
			str = essai.testreponse(idTab, cpt);
			cpt=cpt+1;
			chance = chance+1;
			
			
		}
		if(chance==4)
		{
			System.out.println("Perdu!");
		}
		else
		{
		System.out.println("Bonne réponse!");
		}
		
		
		
		
		
		
	
		
		
		
				
				
				
		
		
		


	}

}
