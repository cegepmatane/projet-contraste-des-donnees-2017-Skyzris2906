package modele;

public class Accelerometre {
	
    private  int id;
    private double x;
    private double y;
    private double z;
    private String date;
    private String heure;
    
    
	public Accelerometre(int id, double  x, double  y, double  z, String date, String heure) 
	{
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
	public double  getX() {
		return x;
	}
	public void setX(double  x) {
		this.x = x;
	}
	public double  getY() {
		return y;
	}
	public void setY(double  y) {
		this.y = y;
	}
	public double  getZ() {
		return z;
	}
	public void setZ(double  z) {
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
		return "Accelerometre [id=" + id + ", x=" + x + ", y=" + y + ", z=" + z + ", date=" + date + ", heure=" + heure
				+ "]";
	}
	
	
}
