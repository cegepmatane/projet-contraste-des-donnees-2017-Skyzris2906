package informatique.cgmatane.qc.cq.databasestats.modele;

import java.util.Calendar;

/**
 * Created by 1743002 on 2017-11-20.
 */

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

    public static String dateFrancaise(Calendar date){

        String dateConvertie = String.format("%02d",date.get(Calendar.DATE)) + "/" + String.format("%02d",date.get(Calendar.MONTH)+1)
                + "/" + String.format("%02d",date.get(Calendar.YEAR)) + " " + String.format("%02d",date.get(Calendar.HOUR_OF_DAY))
                + ":" + String.format("%02d",date.get(Calendar.MINUTE));

        return dateConvertie;
    }
}
