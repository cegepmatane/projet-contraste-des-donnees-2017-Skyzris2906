package vue;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import accesseur.AccelerometreDAO;
import accesseur.TemperatureDAO;

public class ApplicationService extends Application
{

	Stage window;
	Scene scene1, scene2, scene3;
	public static void main(String[] args)
	{
		launch(args);  // lancement de l'application javaFX
	}

	public void start(Stage primaryStage)
	{
		System.out.println("dans la methode start()");
		primaryStage.setTitle("Application BDD Temperature/Accelerometre"); // titre  de la scene
        FlowPane RootNode = new FlowPane(); //Utilisation du FlowPanel
        RootNode.setAlignment(Pos.CENTER); // centrer le controle de la zone
        Scene scene = new Scene(RootNode, 400, 300); //Creation de la scene
        primaryStage.setScene(scene);
        
        // creation de boutons push
        Button btnTemp = new Button ("Temperatures");
        btnTemp.setOnAction(e -> primaryStage.setScene(scene2));
        Button btnAcce = new Button ("Accelerometre");
        // action du bouton Temp
        btnTemp.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent ae) {
        		//response.setText("temperatures");
        	}
		});
        // action du bouton Acce
        btnAcce.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent ae) {
        		//response.setText("Accelerometre");
        	}
		});
        // ajouter le label et les boutons sur la scene
        RootNode.getChildren().addAll(btnTemp ,btnAcce);
   	 
        // on affiche la scene
        primaryStage.show();

	}

	
	public void startTemp(Stage stage)
	{
        stage.setTitle("Application BDD Temperature"); // titre  de la scene
        FlowPane RootNode = new FlowPane(); //Utilisation du FlowPanel
        RootNode.setAlignment(Pos.CENTER); // centrer le controle de la zone
        Scene sceneTemp = new Scene(RootNode, 400, 300); //Creation de la scene
        stage.setScene(sceneTemp);
   
        
        Button btnRetour = new Button ("Retour");
        // action du bouton Temp
        btnRetour.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent ae) {
        		////
        	}
		});
        
     // ajouter le label et les boutons sur la scene
        RootNode.getChildren().addAll(btnRetour);
        // on affiche la scene
        stage.show();
	}
}