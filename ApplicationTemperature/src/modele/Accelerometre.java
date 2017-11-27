package modele;

import java.util.Calendar;

public class Accelerometre {
	
    private  int id;
    private double x;
    private double y;
    private double z;
    private Calendar date;
    
    
	public Accelerometre(int id, double  x, double  y, double  z, Calendar date) 
	{
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
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
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
	
	
}
