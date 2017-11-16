package informatique.cgmatane.qc.cq.databasestats.modele;

import java.util.HashMap;

/**
 * Created by 1732986 on 2017-11-08.
 */

public class Temperature {

    private int id;
    private Double temperature;
    private String date;
    private String heure;

    public Temperature(int id, Double temperature, String date, String heure) {
        this.id = id;
        this.temperature = temperature;
        this.date = date;
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
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
        return "Temperature{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                '}';
    }

    public HashMap<String,String> exporteEnHashMap(){
        HashMap<String,String> listeTemperatures = new HashMap<>();

        listeTemperatures.put("id_temperature",String.valueOf(this.id));
        listeTemperatures.put("temperature",String.valueOf(this.temperature));
        listeTemperatures.put("date",this.date + " " + this.heure);

        return listeTemperatures;
    }
}
