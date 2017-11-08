package modele;

public class Temperature {
	
	protected int id;
	protected int temperatureCelcius;
	protected String temperatureHeure;
	protected String temperatureDate;
	
	public Temperature(int id, int temperatureCelcius) {
		super();
		this.id = id;
		this.temperatureCelcius = temperatureCelcius;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTemperatureCelcius() {
		return temperatureCelcius;
	}
	
	public void setTemperatureCelcius(int temperatureCelcius) {
		this.temperatureCelcius = temperatureCelcius;
	}
	
	public String getTemperatureHeure() {
		return temperatureHeure;
	}
	
	public void setTemperatureHeure(String temperatureHeure) {
		this.temperatureHeure = temperatureHeure;
	}
	
	public String getTemperatureDate() {
		return temperatureDate;
	}
	
	public void setTemperatureDate(String temperatureDate) {
		this.temperatureDate = temperatureDate;
	}
	
}
