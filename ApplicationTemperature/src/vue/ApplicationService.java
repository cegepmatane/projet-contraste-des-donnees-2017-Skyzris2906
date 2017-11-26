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
import modele.Statistiques;
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
        btnSceneStatTemp = new Button ("Statistiques");
        
        btnSceneBDD.setOnAction(e-> ButtonClicked(e));
        btnSceneTemp.setOnAction(e-> ButtonClicked(e));
        btnSceneStatTemp.setOnAction(e-> ButtonClicked(e));

        lblSceneTemp=new Label("Temperature");
        temperatureDAO = new TemperatureDAO();
        listerToutesLesTemperatures = temperatureDAO.listerToutesLesTemperature();
        
        lblMoy = new Label("Moyenne Temperatures : ");
        //System.out.println(Statistiques.moyenne(listerToutesLesTemperatures)+" °C");
        lblMin = new Label("Minimum Temperatures : ");
        //System.out.println(Statistiques.minimum(listerToutesLesTemperatures)+" °C");
        lblMax = new Label("Maximum Temperatures : ");
        //System.out.println(Statistiques.maximum(listerToutesLesTemperatures)+" °C");
        
        // Graph Temperature
        final NumberAxis xAxisT = new NumberAxis(1, 31, 1);
        final NumberAxis yAxisT = new NumberAxis();
        final AreaChart<Number,Number> AreaChartTemp = 
            new AreaChart<Number,Number>(xAxisT,yAxisT);
        AreaChartTemp.setTitle("Temperature moniteur");
 
        XYChart.Series serieWeek= new XYChart.Series();
        serieWeek.setName("Semaine");
        serieWeek.getData().add(new XYChart.Data(1, 4));
        serieWeek.getData().add(new XYChart.Data(3, 10));
        serieWeek.getData().add(new XYChart.Data(5, 2 ));
        serieWeek.getData().add(new XYChart.Data(7, 7));
        
        XYChart.Series serieMonth = new XYChart.Series();
        serieMonth.setName("Mois");
        serieMonth.getData().add(new XYChart.Data(1, 4));
        serieMonth.getData().add(new XYChart.Data(3, 10));
        serieMonth.getData().add(new XYChart.Data(5, 2 ));
        serieMonth.getData().add(new XYChart.Data(7, 7));
        serieMonth.getData().add(new XYChart.Data(9, 3));
        serieMonth.getData().add(new XYChart.Data(11, 9));
        serieMonth.getData().add(new XYChart.Data(13, 2));
        serieMonth.getData().add(new XYChart.Data(15, 8));
        serieMonth.getData().add(new XYChart.Data(18, 7.5));
        serieMonth.getData().add(new XYChart.Data(21, 11));
        serieMonth.getData().add(new XYChart.Data(24, 9));
        
       
        
        
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
        //System.out.println(AcelerometreDAO.x(listerAccelerometre)+" ");
        lbly = new Label("y : ");
        //System.out.println(AcelerometreDAO.y(listerAccelerometre)+" ");
        lblz = new Label("z : ");
        //System.out.println(AcelerometreDAO.y(listerAccelerometre)+" ");
        lblDate = new Label("Date : ");
        lblHeure = new Label("Heure : ");
        
        
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Accelerometre");
        XYChart.Series series = new XYChart.Series();
        series.setName("X");
      
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Y");
        series2.getData().add(new XYChart.Data(1, 33));
        series2.getData().add(new XYChart.Data(2, 34));
        series2.getData().add(new XYChart.Data(3, 25));
        series2.getData().add(new XYChart.Data(4, 44));
        series2.getData().add(new XYChart.Data(5, 39));
        series2.getData().add(new XYChart.Data(6, 16));
        series2.getData().add(new XYChart.Data(7, 55));
        series2.getData().add(new XYChart.Data(8, 54));
        series2.getData().add(new XYChart.Data(9, 48));
        series2.getData().add(new XYChart.Data(10, 27));
        series2.getData().add(new XYChart.Data(11, 37));
        series2.getData().add(new XYChart.Data(12, 29));
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Z");
        series3.getData().add(new XYChart.Data(1, 30));
        series3.getData().add(new XYChart.Data(2, 24));
        series3.getData().add(new XYChart.Data(3, 35));
        series3.getData().add(new XYChart.Data(4, 34));
        series3.getData().add(new XYChart.Data(5, 29));
        series3.getData().add(new XYChart.Data(6, 26));
        series3.getData().add(new XYChart.Data(7, 45));
        series3.getData().add(new XYChart.Data(8, 44));
        series3.getData().add(new XYChart.Data(9, 38));
        series3.getData().add(new XYChart.Data(10, 37));
        series3.getData().add(new XYChart.Data(11, 57));
        series3.getData().add(new XYChart.Data(12, 19));
        
    
   
        
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
        paneAcce.getChildren().addAll(lblSceneAcce,lblx, lbly, lblz, lblDate, lblHeure, lineChart,btnSceneAcce);
        
        
        sceneBDD= new Scene(paneBDD, 200,200);
        sceneTemp = new Scene(paneTemp, 600,600);
        AreaChartTemp.getData().addAll(serieWeek, serieMonth);
        sceneAcce = new Scene(paneAcce, 600, 600);
        lineChart.getData().addAll(series,series2,series3);
        
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