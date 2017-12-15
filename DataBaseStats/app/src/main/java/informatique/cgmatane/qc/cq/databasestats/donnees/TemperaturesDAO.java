package informatique.cgmatane.qc.cq.databasestats.donnees;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import informatique.cgmatane.qc.cq.databasestats.modele.ModeleDate;
import informatique.cgmatane.qc.cq.databasestats.modele.Temperature;

/**
 * Created by 1743002 on 2017-11-16.
 */

public class TemperaturesDAO {

    private List<Temperature> listeTemperatures;
    private List<HashMap<String,String>> listeTemperaturesEnHashMap;
    private List<Temperature> listeTriee;
    private Context context;
    private static final String URL_CONNECTION = "http://192.168.1.146/service_temp/temperature/liste/index.php";
	private Datastore datastore;
	private KeyFactory keyFactory;

	public DatastoreDao() {
	  datastore = DatastoreOptions.getDefaultInstance().getService();
	  keyFactory = datastore.newKeyFactory().setKind("Temperature");
	}

	public Result<Temperature> listTemperatures(String startCursorString) 
	{
	  Cursor startCursor = null;

	  if (startCursorString != null && !startCursorString.equals("")) 
	  {
	    startCursor = Cursor.fromUrlSafe(startCursorString);
	  }
	  Query<Entity> query = Query.newEntityQueryBuilder()
	      .setKind("Temperature")
	      .setLimit(10)
	      .setStartCursor(startCursor)
	      .setOrderBy(OrderBy.asc(Temperature.DATE))
	      .build();
	  QueryResults<Entity> resultList = datastore.run(query);
	  List<Temperature> resultTemps = entitiesToTemps(resultList);
	  Cursor cursor = resultList.getCursorAfter();
		  if (cursor != null && resultTemps.size() == 10) 
		  {
		    String cursorString = cursor.toUrlSafe();
		    return new Result<>(resultTemps, cursorString);
		  }
		  else 
		  {
		    return new Result<>(resultTemps);
		  }
	}

   
//    public List<Temperature> listerLesTemperatures(){
//
//        listeTemperatures.clear();
//
//        try{
//            URL url = new URL(URL_CONNECTION);
//            HttpURLConnection service = (HttpURLConnection) url.openConnection();
//            InputStream flux = service.getInputStream();
////            InputStream flux = context.getAssets().open("temperatures.xml");
//            Scanner lecteur = new Scanner(flux).useDelimiter("\\A");
//            String xml = lecteur.hasNext() ? lecteur.next() : "";
//
//            if (xml != null) {
//
//                DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//                Document document = parseur.parse(new StringBufferInputStream(xml));
//
//                NodeList nodeListe = document.getElementsByTagName("Temperature");
//
//                for (int i =0; i<nodeListe.getLength(); i++) {
//                    Element elementTemperature = (Element) nodeListe.item(i);
//
//                    int id = Integer.parseInt(elementTemperature.getElementsByTagName("id").item(0).getTextContent());
//                    double degre = Double.parseDouble(elementTemperature.getElementsByTagName("temperature").item(0).getTextContent());
//                    String date = elementTemperature.getElementsByTagName("date").item(0).getTextContent();
//                    String heure = elementTemperature.getElementsByTagName("heure").item(0).getTextContent();
//
//                    Calendar calendrier = ModeleDate.getDateTemperature(date,heure);
//
//                    Temperature temperature = new Temperature(id,degre,calendrier);
//
//                    listeTemperatures.add(temperature);
//                }
//
//            }
//        }catch (Exception ex){
//            Log.d("APPERROR", ex.getMessage());
//        }
//
//        return  listeTemperatures;
//    }
//
//    public List<HashMap<String,String>> listerLesTemperaturesEnHashMap(){
//
//        listeTemperaturesEnHashMap.clear();
//
//        for(Temperature temperature : this.listeTriee){
//            listeTemperaturesEnHashMap.add(temperature.exporteEnHashMap());
//        }
//
//        return listeTemperaturesEnHashMap;
//    }
//
//    public List<Temperature> listerTemperaturesAnnee(){
//
//        this.listeTriee.clear();
//
//        listerLesTemperatures();
//
//        Calendar dateActuelle = Calendar.getInstance();
//
//        for (Temperature temperature : this.listeTemperatures){
//
//            if(temperature.getDate().get(Calendar.YEAR) == dateActuelle.get(Calendar.YEAR)){
//
//                this.listeTriee.add(temperature);
//            }
//        }
//
//        return listeTriee;
//    }
//
//    public List<Temperature> listerTemperaturesMois(){
//
//        this.listeTriee.clear();
//
//        listerLesTemperatures();
//
//        Calendar dateActuelle = Calendar.getInstance();
//
//        for (Temperature temperature : this.listeTemperatures){
//
//            if(temperature.getDate().get(Calendar.MONTH) == dateActuelle.get(Calendar.MONTH) && temperature.getDate().get(Calendar.YEAR) == dateActuelle.get(Calendar.YEAR)){
//
//                this.listeTriee.add(temperature);
//            }
//        }
//
//        return listeTriee;
//    }
//
//    public List<Temperature> listerTemperaturesSemaine(){
//
//        this.listeTriee.clear();
//
//        listerLesTemperatures();
//
//        Calendar dateActuelle = Calendar.getInstance();
//
//        for (Temperature temperature : this.listeTemperatures){
//
//            if(temperature.getDate().get(Calendar.WEEK_OF_YEAR) == dateActuelle.get(Calendar.WEEK_OF_YEAR) && temperature.getDate().get(Calendar.YEAR) == dateActuelle.get(Calendar.YEAR)){
//
//                this.listeTriee.add(temperature);
//            }
//        }
//
//        return listeTriee;
//    }
//
//    public List<Temperature> listerTemperaturesJour(){
//
//        this.listeTriee.clear();
//
//        listerLesTemperatures();
//
//        Calendar dateActuelle = Calendar.getInstance();
////        Calendar dateDebut = Calendar.getInstance();
////        Calendar dateFin = Calendar.getInstance();
////
////        dateDebut.set(dateActuelle.get(Calendar.YEAR),dateActuelle.get(Calendar.MONTH),dateActuelle.get(Calendar.DATE),0,0,0);
////        dateFin.set(dateActuelle.get(Calendar.YEAR),dateActuelle.get(Calendar.MONTH),dateActuelle.get(Calendar.DATE),23,59,59);
////
////        long dateDebutMilli = dateDebut.getTimeInMillis();
////        long dateFinMilli = dateFin.getTimeInMillis();
//
//        for (Temperature temperature : this.listeTemperatures){
//
//            if(temperature.getDate().get(Calendar.DATE) == dateActuelle.get(Calendar.DATE) && temperature.getDate().get(Calendar.MONTH) == dateActuelle.get(Calendar.MONTH) && temperature.getDate().get(Calendar.YEAR) == dateActuelle.get(Calendar.YEAR)){
//
//                this.listeTriee.add(temperature);
//            }
//        }
//
//
//        return this.listeTriee;
//    }
//
//    public List<Temperature> listerToutesLesTemperatures(){
//
//        this.listeTriee.clear();
//
//        listerLesTemperatures();
//
//        for (Temperature temperature : this.listeTemperatures){
//            listeTriee.add(temperature);
//        }
//
//        return this.listeTriee;
//    }

}