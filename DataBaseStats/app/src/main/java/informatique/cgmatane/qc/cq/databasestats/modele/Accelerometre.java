package informatique.cgmatane.qc.cq.databasestats.modele;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by 1743002 on 2017-11-16.
 */

public class Accelerometre {

    private  int id;
    private Double x;
    private Double y;
    private Double z;
    private Calendar date;

    public Accelerometre(int id, Double x, Double y, Double z, Calendar date) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.date = date;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
    public int getJour(){
        return date.get(Calendar.DATE);
    }

    public int getMois(){
        return date.get(Calendar.MONTH)+1;
    }

    public int getAnnnee(){
        return date.get(Calendar.YEAR);
    }

    public int getHeure(){
        return date.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinutes(){
        return date.get(Calendar.MINUTE);
    }


    @Override
    public String toString() {
        return "Accelerometre{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", date='" + ModeleDate.dateFrancaise(this.getDate()) + '\'' +
                '}';
    }

    public HashMap<String,String> exporteEnHashMap(){
        HashMap<String,String> listeAccelerometre = new HashMap<>();

        listeAccelerometre.put("id_accelerometre",String.valueOf(this.id));
        listeAccelerometre.put("accelerometre","x : " + String.valueOf(this.x) + " - y : " + String.valueOf(this.y) + " - z : " + String.valueOf(this.z));
        listeAccelerometre.put("date",ModeleDate.dateFrancaise(this.getDate()));

        return listeAccelerometre;
    }
}
