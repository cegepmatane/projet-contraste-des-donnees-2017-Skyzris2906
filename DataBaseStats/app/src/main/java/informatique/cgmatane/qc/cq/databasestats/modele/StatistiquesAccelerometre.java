package informatique.cgmatane.qc.cq.databasestats.modele;

import android.util.Log;

import java.util.List;

/**
 * Created by 1732986 on 2017-11-19.
 */

public class StatistiquesAccelerometre {

    public static String calculerMoyenne(List<Accelerometre> listeAccelerometre){

        String moyenne;
        double moyenneX = 0.0;
        double moyenneY = 0.0;
        double moyenneZ = 0.0;

        if (listeAccelerometre.size()==0)
        {
            return moyenne = "\nx=0.0\ny=0.0\nz=0.0";
        }

        for(Accelerometre accelerometre : listeAccelerometre){
            moyenneX += accelerometre.getX();
            moyenneY += accelerometre.getY();
            moyenneZ += accelerometre.getZ();

        }

        moyenneX = moyenneX/listeAccelerometre.size();
        moyenneY = moyenneY/listeAccelerometre.size();
        moyenneZ = moyenneZ/listeAccelerometre.size();

        return moyenne = "\nx= " + moyenneX + "\ny= " + moyenneY + "\nz= " + moyenneZ;
    }

    public static String calculerMaximum(List<Accelerometre> listeAccelerometre){

        String maximum;

        double maximumX;
        double maximumY;
        double maximumZ;

        if (listeAccelerometre.size()==0)
        {
            return maximum = "\nx=0.0\ny=0.0\nz=0.0";
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

        return maximum = "\nx= " + maximumX + "\ny= " + maximumY + "\nz= " + maximumZ;
    }

    public static  String calculerMinimum(List<Accelerometre> listeAccelerometre) {

        String minimum;

        double minimumX;
        double minimumY;
        double minimumZ;

        if (listeAccelerometre.size() == 0) {
            return minimum = "\nx=0.0\ny=0.0\nz=0.0";
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

        return minimum = "\nx= " + minimumX + "\ny= " + minimumY + "\nz= " + minimumZ;
    }
}
