package informatique.cgmatane.qc.cq.databasestats.modele;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by 1732986 on 2017-11-08.
 */

public class Temperature {

    private int id;
    private double temperature;
    private Calendar date;

    public Temperature(int id, double temperature, Calendar date) {
        this.id = id;
        this.temperature = temperature;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(int jour, int mois, int annee, int heure, int minute) {
        this.date.set(annee,mois-1,jour,heure,minute,00);
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
        return "Temperature{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", date=" + ModeleDate.dateFrancaise(this.getDate()) +
                '}';
    }

    public HashMap<String,String> exporteEnHashMap(){
        HashMap<String,String> listeTemperatures = new HashMap<>();

        listeTemperatures.put("id_temperature",String.valueOf(this.id));
        listeTemperatures.put("temperature",String.valueOf(this.temperature) + " °C");
        listeTemperatures.put("date",ModeleDate.dateFrancaise(this.getDate()));

        return listeTemperatures;
    }
}
