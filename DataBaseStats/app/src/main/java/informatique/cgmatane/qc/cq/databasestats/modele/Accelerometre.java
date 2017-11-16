package informatique.cgmatane.qc.cq.databasestats.modele;

import java.util.HashMap;

/**
 * Created by 1743002 on 2017-11-16.
 */

public class Accelerometre {

    private  int id;
    private Double x;
    private Double y;
    private Double z;
    private String date;
    private String heure;

    public Accelerometre(int id, Double x, Double y, Double z, String date, String heure) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.date = date;
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Accelerometre{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                '}';
    }

    public HashMap<String,String> exporteEnHashMap(){
        HashMap<String,String> listeAccelerometre = new HashMap<>();

        listeAccelerometre.put("id_accelerometre",String.valueOf(this.id));
        listeAccelerometre.put("accelerometre","x : " + String.valueOf(this.x) + "y : " + String.valueOf(this.y) + "z : " + String.valueOf(this.y));
        listeAccelerometre.put("date",this.date + " " + this.heure);

        return listeAccelerometre;
    }
}
