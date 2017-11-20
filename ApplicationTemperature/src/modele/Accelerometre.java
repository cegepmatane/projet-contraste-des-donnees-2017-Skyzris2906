package modele;

public class Accelerometre {
	
    private  int id;
    private String x;
    private String y;
    private String z;
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
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getZ() {
		return z;
	}
	public void setZ(String z) {
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
