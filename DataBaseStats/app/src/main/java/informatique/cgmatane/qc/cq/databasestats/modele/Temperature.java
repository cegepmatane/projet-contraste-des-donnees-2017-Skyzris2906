package informatique.cgmatane.qc.cq.databasestats.modele;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by 1732986 on 2017-11-08.
 */

public class Temperature {

    private int id;
    private String accelerometre;
    private Date date;

    public Temperature(int id, String accelerometre, Date date) {
        this.id = id;
        this.accelerometre = accelerometre;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccelerometre() {
        return accelerometre;
    }

    public void setAccelerometre(String accelerometre) {
        this.accelerometre = accelerometre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", accelerometre='" + accelerometre + '\'' +
                ", date=" + date +
                '}';
    }

    public HashMap<String,String> exporteEnHashMap(){
        HashMap<String,String> listeDonnees = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        listeDonnees.put("id_donnees",String.valueOf(this.id));
        listeDonnees.put("accelerometre",this.accelerometre);
        listeDonnees.put("date",dateFormat.format(date));

        return listeDonnees;
    }
}
