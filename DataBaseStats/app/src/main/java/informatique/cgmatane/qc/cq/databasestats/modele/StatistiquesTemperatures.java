package informatique.cgmatane.qc.cq.databasestats.modele;

import android.util.Log;

import java.util.List;

/**
 * Created by 1743002 on 2017-11-16.
 */

public class StatistiquesTemperatures {

    public static double calculerMoyenne(List<Temperature> listeTemperatures){

        double moyenne = 0.0;

        for(Temperature temperature : listeTemperatures){
            moyenne += temperature.getTemperature();
        }

        return (Math.round(moyenne*100)/100)/listeTemperatures.size();
    }

    public static double calculerMaximum(List<Temperature> listeTemperatures){

        double maximum = listeTemperatures.get(0).getTemperature();

        for(Temperature temperature : listeTemperatures){

            if (temperature.getTemperature() > maximum){
                maximum = temperature.getTemperature();
            }
        }

        return maximum;
    }

    public static  double calculerMinimum(List<Temperature> listeTemperatures){

        double minimum = listeTemperatures.get(0).getTemperature();

        for(Temperature temperature : listeTemperatures){

            if (temperature.getTemperature() < minimum){
                minimum = temperature.getTemperature();
            }
        }

        return minimum;
    }

}
