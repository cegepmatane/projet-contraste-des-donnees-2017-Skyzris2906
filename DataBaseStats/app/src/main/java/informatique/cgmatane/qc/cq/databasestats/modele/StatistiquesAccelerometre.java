package informatique.cgmatane.qc.cq.databasestats.modele;

import java.util.List;

/**
 * Created by 1732986 on 2017-11-19.
 */

public class StatistiquesAccelerometre {

    public static String calculerMoyenne(List<Accelerometre> listeAccelerometre){

        String moyenne = "";
        double moyenneX = 0.0;
        double moyenneY = 0.0;
        double moyenneZ = 0.0;

        if (listeAccelerometre.size()==0)
        {
            return moyenne = "x=0.0 y=0.0 z=0.0";
        }

        for(Accelerometre accelerometre : listeAccelerometre){
            moyenneX += accelerometre.getX();
            moyenneY += accelerometre.getY();
            moyenneZ += accelerometre.getZ();

        }

        moyenneX = (Math.round(moyenneX*100)/100)/listeAccelerometre.size();
        moyenneY = (Math.round(moyenneY*100)/100)/listeAccelerometre.size();
        moyenneZ = (Math.round(moyenneZ*100)/100)/listeAccelerometre.size();

        return moyenne = "x= " + moyenneX + " y= " + moyenneY + " z= " + moyenneZ;
    }

    public static String calculerMaximum(List<Accelerometre> listeAccelerometre){

        String maximum = "";

        double maximumX = 0.0;
        double maximumY = 0.0;
        double maximumZ = 0.0;

        if (listeAccelerometre.size()==0)
        {
            return maximum = "x=0.0 y=0.0 z=0.0";
        }

        maximumX =  listeAccelerometre.get(0).getX();
        maximumY =  listeAccelerometre.get(0).getY();
        maximumZ =  listeAccelerometre.get(0).getZ();

        for(Accelerometre accelerometre : listeAccelerometre){

            if (accelerometre.getX() > maximumX){
                maximumX = accelerometre.getX();
            }
            if (accelerometre.getY() > maximumY){
                maximumY = accelerometre.getY();
            }
            if (accelerometre.getZ() > maximumZ){
                maximumZ = accelerometre.getZ();
            }
        }

        return maximum = "x= " + maximumX + " y= " + maximumY + " z= " + maximumZ;
    }

    public static  String calculerMinimum(List<Accelerometre> listeAccelerometre) {

        String minimum = "";

        double minimumX = 0.0;
        double minimumY = 0.0;
        double minimumZ = 0.0;

        if (listeAccelerometre.size() == 0) {
            return minimum = "x=0.0 y=0.0 z=0.0";
        }

        minimumX = listeAccelerometre.get(0).getX();
        minimumY = listeAccelerometre.get(0).getY();
        minimumZ = listeAccelerometre.get(0).getZ();

        for (Accelerometre accelerometre : listeAccelerometre) {

            if (accelerometre.getX() < minimumX) {
                minimumX = accelerometre.getX();
            }
            if (accelerometre.getY() < minimumY) {
                minimumY = accelerometre.getY();
            }
            if (accelerometre.getZ() < minimumZ) {
                minimumZ = accelerometre.getZ();
            }
        }

        return minimum = "x= " + minimumX + " y= " + minimumY + " z= " + minimumZ;
    }
}
