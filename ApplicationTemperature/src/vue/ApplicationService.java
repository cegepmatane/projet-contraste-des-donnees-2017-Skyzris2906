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
    
    protected List<Temperature> listerLesTemperatureMois;
    protected List<Temperature> listerLesTemperatureJour;
    protected List<Accelerometre> listerAccelerometreMois;
    protected List<Accelerometre> listerAccelerometreJour;
	
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

        lblSceneTemp=new Label("");
        temperatureDAO = new TemperatureDAO();
        listerLesTemperatureMois = temperatureDAO.listerTemperaturesMois();
        
        
        List<Temperature> listeTemperatures;
		lblMoy = new Label("Moyenne Temperatures : " + StatistiquesT.calculerMoyenne(listerLesTemperatureMois)+" °C");
        lblMin = new Label("Minimum Temperatures : " + StatistiquesT.calculerMinimum(listerLesTemperatureMois)+" °C");
        lblMax = new Label("Maximum Temperatures : " + StatistiquesT.calculerMaximum(listerLesTemperatureMois)+" °C");
        
        // Graph Temperature Mois
        final NumberAxis xAxisTempM = new NumberAxis(1, 31, 2);
        final NumberAxis yAxisTempM = new NumberAxis();
        final AreaChart<Number,Number> AreaChartTempMois = new AreaChart<Number,Number>(xAxisTempM,yAxisTempM);
        AreaChartTempMois.setTitle("Temperature moniteur mois");
        xAxisTempM.setLabel("Jour");
        yAxisTempM.setLabel("Degrès Celcius");
 
        XYChart.Series serieMois= new XYChart.Series();
        serieMois.setName("Mois");
        
        for (Temperature temp : listerLesTemperatureMois)
        {
        	serieMois.getData().add(new XYChart.Data(temp.getJour(),temp.getTemperature()));
        }
        
        
        // Graph Temperature Jour
        listerLesTemperatureJour = temperatureDAO.listerTemperaturesJour();
        final NumberAxis xAxisTempJ = new NumberAxis(1, 24, 2);
        final NumberAxis yAxisTempJ = new NumberAxis();
        final AreaChart<Number,Number> AreaChartTempJour = new AreaChart<Number,Number>(xAxisTempJ,yAxisTempJ);
        
        AreaChartTempJour.setTitle("Temperature moniteur jour");
        xAxisTempJ.setLabel("Heure");
        yAxisTempJ.setLabel("Degrès Celcius");
 
        XYChart.Series serieJour= new XYChart.Series();
        serieJour.setName("Jour");
        
        for (Temperature temp : listerLesTemperatureJour)
        {
        	serieJour.getData().add(new XYChart.Data(temp.getHeure(),temp.getTemperature()));
        }

        
        
        
        
        
        
        
        // Scene accelerometre
        btnSceneBDD2=new Button ("Accelerometre");
        btnSceneAcce=new Button("Retour");
        
        btnSceneBDD2.setOnAction(e-> ButtonClicked(e));
        btnSceneAcce.setOnAction(e-> ButtonClicked(e));
        lblSceneBDD=new Label("Base de donées : ");
        
        lblSceneAcce=new Label("");
        accelerometreDAO = new AccelerometreDAO();
        listerAccelerometreMois = accelerometreDAO.listerAccelerometreMois();
        
        lblx = new Label("Moyenne : " + StatistiquesA.calculerMoyenne(listerAccelerometreMois));
     //  listerAccelerometreMois
        
        // Graph Accelerometre Mois
        final NumberAxis xAxis = new NumberAxis(1,31,1);
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
           
        
        lineChart.setTitle("Accelerometre Mois");
        xAxis.setLabel("Jour");
        XYChart.Series seriesX = new XYChart.Series();
        seriesX.setName("X");
        
        for(Accelerometre acce : listerAccelerometreMois)
        {
        	seriesX.getData().add(new XYChart.Data(acce.getJour(), acce.getX()));
        }
        
        XYChart.Series seriesY = new XYChart.Series();
        seriesY.setName("Y");
        for(Accelerometre acce : listerAccelerometreMois)
        {
        	seriesY.getData().add(new XYChart.Data(acce.getJour(), acce.getY()));
        }
        
        XYChart.Series seriesZ = new XYChart.Series();
        seriesZ.setName("Z");
        for(Accelerometre acce : listerAccelerometreMois)
        {
        	seriesZ.getData().add(new XYChart.Data(acce.getJour(), acce.getZ()));
        }
        
        
        // Graph Accelerometre Jour
        listerAccelerometreJour = accelerometreDAO.listerAccelerometreJour();
        final NumberAxis xAxisJ = new NumberAxis(1,24,1);
        final NumberAxis yAxisJ = new NumberAxis();
        final LineChart<Number,Number> lineChartJ = new LineChart<Number,Number>(xAxisJ,yAxisJ);
        
        lineChartJ.setTitle("Accelerometre jour");
        xAxisJ.setLabel("H");
        XYChart.Series seriesXJ = new XYChart.Series();
        seriesXJ.setName("X");
        
        for(Accelerometre acce : listerAccelerometreJour)
        {
        	seriesXJ.getData().add(new XYChart.Data(acce.getHeure(), acce.getX()));
        }
        
        XYChart.Series seriesYJ = new XYChart.Series();
        seriesYJ.setName("Y");
        for(Accelerometre acce : listerAccelerometreJour)
        {
        	seriesYJ.getData().add(new XYChart.Data(acce.getHeure(), acce.getY()));
        }
        
        XYChart.Series seriesZJ = new XYChart.Series();
        seriesZJ.setName("Z");
        for(Accelerometre acce : listerAccelerometreJour)
        {
        	seriesZJ.getData().add(new XYChart.Data(acce.getHeure(), acce.getZ()));
        }
        
   
        
        //Pane
        paneBDD=new FlowPane();
        paneBDD.setHgap(500);
        paneBDD.setVgap(30);
        paneBDD.setHgap(500);
        paneBDD.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        paneBDD.getChildren().addAll(lblSceneBDD, btnSceneBDD, btnSceneBDD2);
        paneBDD.setAlignment(Pos.CENTER);
        
        paneTemp=new FlowPane();
        paneTemp.setVgap(10);
        paneTemp.setHgap(30);
        paneTemp.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        paneTemp.getChildren().addAll(lblSceneTemp,lblMax,lblMoy,lblMin,AreaChartTempMois,AreaChartTempJour,btnSceneTemp);
        
        paneAcce=new FlowPane();
        paneAcce.setVgap(10);
        paneAcce.setHgap(30);
        paneAcce.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        paneAcce.getChildren().addAll(lblSceneAcce,lblx,lineChart,lineChartJ,btnSceneAcce);
        
        sceneBDD= new Scene(paneBDD, 400,400);
        
        sceneTemp = new Scene(paneTemp, 600,910);
        AreaChartTempMois.getData().addAll(serieMois);
        AreaChartTempJour.getData().addAll(serieJour);
        
        sceneAcce = new Scene(paneAcce, 600, 910);
        lineChart.getData().addAll(seriesX,seriesY,seriesZ);
        lineChartJ.getData().addAll(seriesXJ,seriesYJ,seriesZJ);
        
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