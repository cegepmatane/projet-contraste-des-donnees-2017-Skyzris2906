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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Accelerometre;
import modele.StatistiquesA;
import modele.StatistiquesT;
import modele.Temperature;
import accesseur.AccelerometreDAO;
import accesseur.TemperatureDAO;

public class ApplicationService extends Application
{
	Stage window;
	Scene sceneBDD, sceneTemp, sceneAcce;
    Button btnSceneBDD,btnSceneBDD2, btnSceneTemp, btnSceneAcce, btnSceneStatTemp;
    Label lblSceneBDD, lblSceneTemp,lblMax ,lblMoy ,lblMin, lblSceneAcce, lblx, lbly, lblz, lblDate, lblHeure;
    FlowPane paneBDD, paneTemp, paneAcce;
    
    protected TemperatureDAO temperatureDAO;
    protected AccelerometreDAO accelerometreDAO;
    
    protected List<Temperature> listerLesTemperature;
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
        listerLesTemperature = temperatureDAO.listerLesTemperature();
        
        List<Temperature> listeTemperatures;
		lblMoy = new Label("Moyenne Temperatures : " + StatistiquesT.calculerMoyenne(listerLesTemperature)+" °C");
        lblMin = new Label("Minimum Temperatures : " + StatistiquesT.calculerMinimum(listerLesTemperature)+" °C");
        lblMax = new Label("Maximum Temperatures : " + StatistiquesT.calculerMaximum(listerLesTemperature)+" °C");
        
        // Graph Temperature
        final NumberAxis xAxisT = new NumberAxis(1, 31, 1);
        final NumberAxis yAxisT = new NumberAxis();
        final AreaChart<Number,Number> AreaChartTemp = new AreaChart<Number,Number>(xAxisT,yAxisT);
        AreaChartTemp.setTitle("Temperature moniteur");
 
        XYChart.Series serieYears= new XYChart.Series();
        serieYears.setName("Année");
       // System.out.println(listerLesTemperature);
        
        for (Temperature temp : listerLesTemperature)
        {
        	serieYears.getData().add(new XYChart.Data(temp.getJour(),temp.getTemperature()));
        }

        
        
        
        
        
        
        
        // Scene accelerometre
        btnSceneBDD2=new Button ("Accelerometre");
        btnSceneAcce=new Button("Retour");
        
        btnSceneBDD2.setOnAction(e-> ButtonClicked(e));
        btnSceneAcce.setOnAction(e-> ButtonClicked(e));
        lblSceneBDD=new Label("Base de donées : ");
        
        lblSceneAcce=new Label("Accelerometre");
        accelerometreDAO = new AccelerometreDAO();
        listerAccelerometre = accelerometreDAO.listerAccelerometre();
        
        lblx = new Label("Moyenne : " + StatistiquesA.calculerMoyenne(listerAccelerometre));
        
        // Graph Accelerometre
        final NumberAxis xAxis = new NumberAxis(1,31,1);
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        
        System.out.println(listerAccelerometre);       
        
        lineChart.setTitle("Accelerometre");
        XYChart.Series seriesX = new XYChart.Series();
        seriesX.setName("X");
      
        for(Accelerometre acce : listerAccelerometre)
        {
        	seriesX.getData().add(new XYChart.Data(acce.getJour(), acce.getX()));
        }
        
        XYChart.Series seriesY = new XYChart.Series();
        seriesY.setName("Y");
        
        for(Accelerometre acce : listerAccelerometre)
        {
        	seriesY.getData().add(new XYChart.Data(acce.getJour(), acce.getY()));
        }
        
        
        XYChart.Series seriesZ = new XYChart.Series();
        seriesZ.setName("Z");
        
        for(Accelerometre acce : listerAccelerometre)
        {
        	seriesZ.getData().add(new XYChart.Data(acce.getJour(), acce.getZ()));
        }
        
   
        
        //Pane
        paneBDD=new FlowPane();
        paneBDD.setVgap(30);
        paneBDD.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        paneBDD.getChildren().addAll(lblSceneBDD, btnSceneBDD, btnSceneBDD2);
        
        paneTemp=new FlowPane();
        paneTemp.setVgap(30);
        paneTemp.setHgap(30);
        paneTemp.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        paneTemp.getChildren().addAll(lblSceneTemp,lblMax,lblMoy,lblMin,AreaChartTemp,btnSceneTemp);
        
        paneAcce=new FlowPane();
        paneAcce.setVgap(30);
        paneAcce.setHgap(30);
        paneAcce.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        paneAcce.getChildren().addAll(lblSceneAcce,lblx,lineChart,btnSceneAcce);
        
        sceneBDD= new Scene(paneBDD, 200,200);
        
        sceneTemp = new Scene(paneTemp, 600,600);
        AreaChartTemp.getData().addAll(serieYears);
        
        sceneAcce = new Scene(paneAcce, 600, 600);
        lineChart.getData().addAll(seriesX,seriesY,seriesZ);
        
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