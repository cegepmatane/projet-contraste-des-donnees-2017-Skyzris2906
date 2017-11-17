package informatique.cgmatane.qc.cq.databasestats.donnees;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
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
    private Context context;

    public AccelerometreDAO (Context context){

        this.context = context;

        listeAccelerometre = new ArrayList<>();
        listeAccelerometreEnHashMap = new ArrayList<>();
    }

    public List<Accelerometre> listerToutesLesValeursAccelerometre(){

        listeAccelerometre.clear();

        try{
            InputStream flux = context.getAssets().open("accelerometre.xml");
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

        List<Accelerometre> listeAccelerometre = listerToutesLesValeursAccelerometre();

        for(Accelerometre accelerometre : listeAccelerometre){
            listeAccelerometreEnHashMap.add(accelerometre.exporteEnHashMap());
        }

        return listeAccelerometreEnHashMap;
    }
}
