package informatique.cgmatane.qc.cq.databasestats.donnees;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import informatique.cgmatane.qc.cq.databasestats.modele.Accelerometre;
import informatique.cgmatane.qc.cq.databasestats.modele.Temperature;

/**
 * Created by 1743002 on 2017-11-16.
 */

public class AccelerometreDAO {

    private List<Accelerometre> listeAccelerometre;
    private List<HashMap<String,String>> listeAccelerometreEnHashMap;
    private List<Accelerometre> listeTriee;
    private Context context;
    private static final String URL_CONNECTION = "http://192.168.1.146/service_accelerometre/accelerometre/liste/index.php";

    public AccelerometreDAO (Context context){

        this.context = context;

        listeAccelerometre = new ArrayList<>();
        listeAccelerometreEnHashMap = new ArrayList<>();
        listeTriee = new ArrayList<>();

    }

    public List<Accelerometre> listerLesValeursAccelerometre(){

        listeAccelerometre.clear();

        try{
            URL url = new URL(URL_CONNECTION);
            HttpURLConnection service = (HttpURLConnection) url.openConnection();
            InputStream flux = service.getInputStream();

            Scanner lecteur = new Scanner(flux).useDelimiter("\\A");
            String xml = lecteur.hasNext() ? lecteur.next() : "";
            if (xml != null) {

                DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = parseur.parse(new StringBufferInputStream(xml));

                NodeList nodeListe = document.getElementsByTagName("Accelerometre");

                for (int i =0; i<nodeListe.getLength(); i++) {

                    Element elementTemperature = (Element) nodeListe.item(i);

                    int id = Integer.parseInt(elementTemperature.getElementsByTagName("id").item(0).getTextContent());
                    double x = Double.parseDouble(elementTemperature.getElementsByTagName("x").item(0).getTextContent());
                    double y = Double.parseDouble(elementTemperature.getElementsByTagName("y").item(0).getTextContent());
                    double z = Double.parseDouble(elementTemperature.getElementsByTagName("z").item(0).getTextContent());
                    String date = elementTemperature.getElementsByTagName("date").item(0).getTextContent();
                    String heure = elementTemperature.getElementsByTagName("heure").item(0).getTextContent();

                    Accelerometre accelerometre = new Accelerometre(id,x,y,z,date,heure);

                    listeAccelerometre.add(accelerometre);
                }

            }
        }catch (Exception ex){
            Log.d("APPERROR", ex.getMessage());
        }

        return  listeAccelerometre;
    }

    public List<HashMap<String,String>> listerLesValeursAccelerometreEnHashMap(){

        listeAccelerometreEnHashMap.clear();

        List<Accelerometre> listeAccelerometre = listerLesValeursAccelerometre();

        for(Accelerometre accelerometre : listeAccelerometre){
            listeAccelerometreEnHashMap.add(accelerometre.exporteEnHashMap());
        }

        return listeAccelerometreEnHashMap;
    }

    public List<Temperature> listerTemperaturesAnnee(){

        this.listeTriee.clear();

        listerLesValeursAccelerometre();

        Calendar dateActuelle = Calendar.getInstance();

        for (Accelerometre accelerometre : this.listeAccelerometre){

            if(accelerometre.getDate().get(Calendar.YEAR) == dateActuelle.get(Calendar.YEAR)){

                this.listeTriee.add(accelerometre);
            }
        }

        return listeTriee;
    }
}
