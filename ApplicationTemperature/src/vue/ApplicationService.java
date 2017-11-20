package vue;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Accelerometre;
import modele.Statistiques;
import modele.Temperature;
import accesseur.AccelerometreDAO;
import accesseur.TemperatureDAO;

public class ApplicationService extends Application
{
	Stage window;
	Scene sceneBDD, sceneTemp, sceneAcce;
    Button btnSceneBDD,btnSceneBDD2, btnSceneTemp, btnSceneAcce;
    Label lblSceneBDD, lblSceneTemp,lblMax ,lblMoy ,lblMin, lblSceneAcce, lblx, lbly, lblz, lblDate, lblHeure;
    FlowPane paneBDD, paneTemp, paneAcce;
    
    protected TemperatureDAO temperatureDAO;
    protected AccelerometreDAO accelerometreDAO;
    protected List<Temperature> listerToutesLesTemperatures;
    protected List<Accelerometre> listerAccelerometre;
	
	public static void main(String[] args)
	{
		launch(args);  // lancement de l'application javaFX
	}

	public void start(Stage primaryStage)
	{
		window = primaryStage;
	       
        // scene temperature
        btnSceneBDD=new Button("Temperatures");
        btnSceneTemp=new Button("Retour");
        
        btnSceneBDD.setOnAction(e-> ButtonClicked(e));
        btnSceneTemp.setOnAction(e-> ButtonClicked(e));

        lblSceneTemp=new Label("Temperature");
        temperatureDAO = new TemperatureDAO();
        listerToutesLesTemperatures = temperatureDAO.listerToutesLesTemperature();
        
        lblMoy = new Label("Moyenne Temperatures : ");
        //System.out.println(Statistiques.moyenne(listerToutesLesTemperatures)+" °C");
        lblMin = new Label("Minimum Temperatures : ");
        //System.out.println(Statistiques.minimum(listerToutesLesTemperatures)+" °C");
        lblMax = new Label("Maximum Temperatures : ");
        //System.out.println(Statistiques.maximum(listerToutesLesTemperatures)+" °C");
        
        // Scene accelerometre
        btnSceneBDD2=new Button ("Accelerometre");
        btnSceneAcce=new Button("Retour");
        
        btnSceneBDD2.setOnAction(e-> ButtonClicked(e));
        btnSceneAcce.setOnAction(e-> ButtonClicked(e));
        lblSceneBDD=new Label("Base de donées : ");
        
        lblSceneAcce=new Label("Accelerometre");
        accelerometreDAO = new AccelerometreDAO();
        listerAccelerometre = accelerometreDAO.listerAccelerometre();
        
        lblx = new Label("x : ");
        lbly = new Label("y : ");
        lblz = new Label("z : ");
        lblDate = new Label("Date : ");
        lblHeure = new Label("Heure : ");
   
        
        //Pane
        paneBDD=new FlowPane();
        paneTemp=new FlowPane();
        paneAcce=new FlowPane();
        
        paneBDD.setVgap(30);
        paneTemp.setVgap(30);
        paneTemp.setHgap(30);
        paneAcce.setVgap(30);
        paneAcce.setHgap(30);
        
        //set background color of each Pane
        paneBDD.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        paneTemp.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        paneAcce.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
           
        //add everything to panes
        paneBDD.getChildren().addAll(lblSceneBDD, btnSceneBDD, btnSceneBDD2);
        paneTemp.getChildren().addAll(lblSceneTemp,lblMax,lblMoy,lblMin, btnSceneTemp);
        paneAcce.getChildren().addAll(lblSceneAcce, btnSceneAcce,lblx, lbly, lblz, lblDate, lblHeure);
        
        //make 3 scenes from 3 panes
        sceneBDD= new Scene(paneBDD, 600, 200);
        sceneTemp = new Scene(paneTemp, 600, 200);
        sceneAcce = new Scene(paneAcce, 600, 200);
        
        primaryStage.setTitle("Application BDD");
        primaryStage.setScene(sceneBDD);
        primaryStage.show();
    }
	
	public void ButtonClicked(ActionEvent e)
    {
        if (e.getSource()==btnSceneBDD)
            window.setScene(sceneTemp);
        
        else if(e.getSource()==btnSceneBDD2)
            window.setScene(sceneAcce);
        	
        else
            window.setScene(sceneBDD);
    }
}