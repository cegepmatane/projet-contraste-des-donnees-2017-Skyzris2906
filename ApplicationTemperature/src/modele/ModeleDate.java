package modele;

import java.util.Calendar;

public class ModeleDate {
	
	 public static Calendar getDate(String date, String heure){

	        String[] separateurDate = date.split("/");
	        String[] separateurHeure = heure.split(":");

	        int jour = Integer.parseInt(separateurDate[0]);
	        int mois = Integer.parseInt(separateurDate[1])-1;
	        int annee = Integer.parseInt(separateurDate[2]);

	        int heureSeparee = Integer.parseInt(separateurHeure[0]);
	        int minuteSeparee = Integer.parseInt(separateurHeure[1]);

	        Calendar dateFinale = Calendar.getInstance();

	        dateFinale.set(annee,mois,jour,heureSeparee,minuteSeparee,00);

	        return dateFinale;

	    }
	
}
