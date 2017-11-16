package informatique.cgmatane.qc.cq.databasestats.donnees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import informatique.cgmatane.qc.cq.databasestats.modele.Accelerometre;
import informatique.cgmatane.qc.cq.databasestats.modele.Temperature;

/**
 * Created by 1743002 on 2017-11-16.
 */

public class AccelerometreDAO {

    private List<Accelerometre> listeAccelerometre;
    private List<HashMap<String,String>> listeAccelerometreEnHashMap;

    public AccelerometreDAO (){

        listeAccelerometre = new ArrayList<>();
        listeAccelerometreEnHashMap = new ArrayList<>();
    }

    public List<Accelerometre> listerToutesLesValeursAccelerometre(){

        listeAccelerometre.clear();

        Accelerometre accelerometre1 = new Accelerometre(1,22.3,55.1,64.2,"14/09","13:26");
        Accelerometre accelerometre2 = new Accelerometre(2,52.6,89.1,23.89,"26/10","19:40");
        Accelerometre accelerometre3 = new Accelerometre(3,29.27,38.13,95.14,"11/04","22:54");
        Accelerometre accelerometre4 = new Accelerometre(4,101.8,360.0,54.9,"05/01","12:17");

        listeAccelerometre.add(accelerometre1);
        listeAccelerometre.add(accelerometre2);
        listeAccelerometre.add(accelerometre3);
        listeAccelerometre.add(accelerometre4);

        return  listeAccelerometre;
    }

    public List<HashMap<String,String>> listerLesValeursAccelerometreEnHashMap(){

        listeAccelerometreEnHashMap.clear();

        List<Accelerometre> listeAccelerometre = listerToutesLesValeursAccelerometre();

        for(Accelerometre accelerometre : listeAccelerometre){
            listeAccelerometreEnHashMap.add(accelerometre.exporteEnHashMap());
        }

        return listeAccelerometreEnHashMap;
    }
}
