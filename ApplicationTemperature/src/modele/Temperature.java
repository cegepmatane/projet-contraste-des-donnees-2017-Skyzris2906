package modele;

import java.util.Calendar;

public class Temperature {
	
	protected int id;
	protected double temperature;
	protected Calendar date;

	public Temperature(int id, double temperature, Calendar date)
	{
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

    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", date=" + ModeleDate.dateFrancaise(this.getDate()) +
                '}';
    }
	
}
