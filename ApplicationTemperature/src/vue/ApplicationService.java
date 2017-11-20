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
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Statistiques;
import modele.Temperature;
import accesseur.AccelerometreDAO;
import accesseur.TemperatureDAO;

public class ApplicationService extends Application
{
	Stage window;
	Scene sceneBDD, sceneTemp, sceneAcce;
    Button btnSceneBDD,btnSceneBDD2, btnSceneTemp, btnSceneAcce;
    Label lblSceneBDD, lblSceneTemp,lblMax ,lblMoy ,lblMin, lblSceneAcce;
    FlowPane paneBDD, paneTemp, paneAcce;
    
    protected TemperatureDAO temperatureDAO;
    protected List<Temperature> listerToutesLesTemperatures;
	
	public static void main(String[] args)
	{
		launch(args);  // lancement de l'application javaFX
	}

	public void start(Stage primaryStage)
	{
		window = primaryStage;
		//can now use the stage in other methods
	       
        //make things to put on panes
        btnSceneBDD=new Button("Temperatures");
        btnSceneBDD2=new Button ("Accelerometre");
        
        btnSceneTemp=new Button("Retour");
        btnSceneAcce=new Button("Retour");
        
        btnSceneBDD.setOnAction(e-> ButtonClicked(e));
        btnSceneBDD2.setOnAction(e-> ButtonClicked(e));
        
        btnSceneTemp.setOnAction(e-> ButtonClicked(e));
        btnSceneAcce.setOnAction(e-> ButtonClicked(e));
        
        lblSceneBDD=new Label("Scene BDD");
        
        lblSceneTemp=new Label("Scene Temperature");
        temperatureDAO = new TemperatureDAO();
        
        listerToutesLesTemperatures = temperatureDAO.listerToutesLesTemperature();
        
        lblMoy = new Label("Moyenne Temperatures : ");
        //System.out.println("Valeur moyenne : " + Statistiques.moyenne(listerToutesLesTemperatures)+" °C");
        lblMin = new Label("Minimum Temperatures : ");
        lblMax = new Label("Maximum Temperatures : ");
        
 
        lblSceneAcce=new Label("Scene Accelerometre");
        
        //make 3 Panes
        paneBDD=new FlowPane();
        paneTemp=new FlowPane();
        paneAcce=new FlowPane();
        
        paneBDD.setVgap(10);
        paneTemp.setVgap(10);
        paneAcce.setVgap(10);
        
        //set background color of each Pane
        paneBDD.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        paneTemp.setStyle("-fx-background-color: red;-fx-padding: 10px;");
        paneAcce.setStyle("-fx-background-color: yellow;-fx-padding: 10px;");
           
        //add everything to panes
        paneBDD.getChildren().addAll(lblSceneBDD, btnSceneBDD, btnSceneBDD2);
        paneTemp.getChildren().addAll(lblSceneTemp,lblMax,lblMoy,lblMin, btnSceneTemp);
        paneAcce.getChildren().addAll(lblSceneAcce, btnSceneAcce);
        
        //make 3 scenes from 3 panes
        sceneBDD= new Scene(paneBDD, 400, 500);
        sceneTemp = new Scene(paneTemp, 400, 500);
        sceneAcce = new Scene(paneAcce, 400, 500);
        
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