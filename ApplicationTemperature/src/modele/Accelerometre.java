package modele;

public class Accelerometre {
	
    private  int id;
    private Double x;
    private Double y;
    private Double z;
    private String date;
    private String heure;
    
//	public void setAccelerometreDate(String accelerometreDate) {
//	this.accelerometreDate = accelerometreDate;
//	}
    
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
}
