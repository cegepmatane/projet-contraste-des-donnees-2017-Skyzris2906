package vue;

import accesseur.AccelerometreDAO;
import accesseur.TemperatureDAO;
import javafx.application.Application;
import modele.Accelerometre;
import modele.Temperature;

public class VueAccelerometre extends Application {
	
	public VueAccelerometre (AccelerometreDAO accelerometre, Accelerometre accel)
	{
	
		protected void afficherValeursAccelerometre()
		{
	        listerAccelerometre = accelerometreDAO.listerAccelerometre();

	                new  String[]{"accelerometre","date"}
	        );

	        VueAccelerometre.setAdapter(VueAccelerometre);
	    }
	}
}
