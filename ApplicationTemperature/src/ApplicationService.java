import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.io.StringBufferInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.*;
import javafx.scene.layout.*;

import accesseur.AccelerometreDAO;
import accesseur.TemperatureDAO;

public class ApplicationService 
{
	public static void main (String[] args)
	{
		System.out.println("Lencement de l'application");
		launch.(args);
		
		//AccelerometreDAO accelerometreDAO = new AccelerometreDAO();
		//accelerometreDAO.listerAccelerometre();
		
		//TemperatureDAO temperatureDAO = new TemperatureDAO();
		//temperatureDAO.listerTemperature();
	}
	
	public void init() {
		System.out.println(" dans la methode init() ");
	}
	
	public void start(Stage myScene) 
	{
		System.out.println("dans la methode start()");

		myStage.setTitle("java fx test ");
		FlowPane RootNode = new FlowPane();
		Scene myScene = new Scene(RootNode,300,200);
		myScene.setScene(myScene);
		myScene.show();
	}
	
	public void stop()
	{
		System.out.println("");
	}
}