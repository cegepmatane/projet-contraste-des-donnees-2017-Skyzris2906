package modele;

public class Accelerometre {
	
	protected int id;
	protected int accelerometre;
	protected String date;
	protected String heure;
	
	//public void setTemperatureDate(String temperatureDate) {
	//	this.temperatureDate = temperatureDate;
//	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getAccelerometre() {
		return accelerometre;
	}

	public void setAccelerometre(int accelerometre) {
		this.accelerometre = accelerometre;
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
	
}
