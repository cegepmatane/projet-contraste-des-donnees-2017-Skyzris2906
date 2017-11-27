package modele;

import java.util.List;

public class StatistiquesT 
{

    public static double calculerMoyenne(List<Temperature> listeTemperatures){

        double moyenne = 0.0;

        if (listeTemperatures.size()==0)
        {
            return moyenne;
        }

        for(Temperature temperature : listeTemperatures){
            moyenne += temperature.getTemperature();
        }

        return (Math.round(moyenne*100)/100)/listeTemperatures.size();
    }

    public static double calculerMaximum(List<Temperature> listeTemperatures){

        double maximum = 0.0;

        if (listeTemperatures.size()==0)
        {
            return maximum;
        }

        maximum =  listeTemperatures.get(0).getTemperature();

        for(Temperature temperature : listeTemperatures){

            if (temperature.getTemperature() > maximum){
                maximum = temperature.getTemperature();
            }
        }

        return maximum;
    }

    public static  double calculerMinimum(List<Temperature> listeTemperatures){

        double minimum = 0.0;

        if (listeTemperatures.size()==0)
        {
            return minimum;
        }
        minimum = listeTemperatures.get(0).getTemperature();

        for(Temperature temperature : listeTemperatures){

            if (temperature.getTemperature() < minimum){
                minimum = temperature.getTemperature();
            }
        }

        return minimum;
    }

}