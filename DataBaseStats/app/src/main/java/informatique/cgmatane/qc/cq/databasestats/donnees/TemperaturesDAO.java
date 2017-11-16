package informatique.cgmatane.qc.cq.databasestats.donnees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import informatique.cgmatane.qc.cq.databasestats.modele.Temperature;

/**
 * Created by 1743002 on 2017-11-16.
 */

public class TemperaturesDAO {

    private List<Temperature> listeTemperatures;
    private List<HashMap<String,String>> listeTemperaturesEnHashMap;

    public TemperaturesDAO(){

        listeTemperatures = new ArrayList<>();
        listeTemperaturesEnHashMap = new ArrayList<>();
    }

    public List<Temperature> listerToutesLesTemperatures(){

        listeTemperatures.clear();

        Temperature temperature1 = new Temperature(1,25.3,"25/11","11:45");
        Temperature temperature2 = new Temperature(2,26.0,"13/12","14:22");
        Temperature temperature3 = new Temperature(3,27.8,"18/08","23:55");
        Temperature temperature4 = new Temperature(4,14.1,"01/05","02:30");

        listeTemperatures.add(temperature1);
        listeTemperatures.add(temperature2);
        listeTemperatures.add(temperature3);
        listeTemperatures.add(temperature4);

        return  listeTemperatures;
    }

    public List<HashMap<String,String>> listerLesTemperaturesEnHashMap(){

        listeTemperaturesEnHashMap.clear();

        List<Temperature> listeTemperatures = listerToutesLesTemperatures();

        for(Temperature temperature : listeTemperatures){
            listeTemperaturesEnHashMap.add(temperature.exporteEnHashMap());
        }

        return listeTemperaturesEnHashMap;
    }
}
