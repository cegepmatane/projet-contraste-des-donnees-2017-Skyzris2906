package modele;

import java.util.List;

public class Statistiques 
{	
	 public static double moyenne(List<Temperature> listeTemperatures)
	 {
        double moy = 0.0;
        for(Temperature temperature : listeTemperatures)
        {
            moy += temperature.getTemperature();
        }
        return (Math.round(moy*100)/100)/listeTemperatures.size();
	 }

    public static double maximum(List<Temperature> listeTemperatures)
    {
        double max = listeTemperatures.get(0).getTemperature();
        for(Temperature temperature : listeTemperatures)
        {
            if (temperature.getTemperature() > max)
            {
                max = temperature.getTemperature();
            }
        }
        return max;
    }

    public static  double minimum(List<Temperature> listeTemperatures)
    {
        double min = listeTemperatures.get(0).getTemperature();
        for(Temperature temperature : listeTemperatures)
        {
            if (temperature.getTemperature() < min)
            {
                min = temperature.getTemperature();
            }
        }
        return min;
    }
}
