package accesseur;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modele.Accelerometre;
import modele.ModeleDate;
import modele.Temperature;

public class TemperatureDAO 
{	
	private List<Temperature> listeOrdonnee = new ArrayList<>();
	
	private List<Temperature> listerTemperatures = new ArrayList<>();
	
	private Document parserXML(String xml)
	{
		Document doc = null;
		DocumentBuilder parseur;
		try {
			parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = parseur.parse(new StringBufferInputStream(xml));		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	private String consommerService(String url)
	{
		String xml = null;
			try
			{
				// récupere le XML
				URL urlServiceTemperature = new URL (url);
				URLConnection serviceTemperature = urlServiceTemperature.openConnection();
				InputStream fluxTemperature = serviceTemperature.getInputStream();
		
				Scanner lecteur = new Scanner(fluxTemperature).useDelimiter("\\A");
				xml = lecteur.hasNext() ? lecteur.next() : "";
				//System.out.println(xml);
				return xml;
			}
			catch (MalformedURLException e){
				e.printStackTrace();
			}

			catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public List<Temperature> listerLesTemperature()
 	{
 		// Récupérer le xml
		//String xml = consommerService("http://************/service_temp/temperature/liste/index.php");
		String xml = consommerService("file:///C:/AndroidStudioProjects/projet-contraste-des-donnees-2017-Skyzris2906/ApplicationTemperature/src/vue/temperatures.xml");
		
 		// Interprétation du xml - construire les modeles
		try {
			if (xml != null) {
				
	            DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	            Document document = parseur.parse(new StringBufferInputStream(xml));

	            NodeList nodeListe = document.getElementsByTagName("Temperature");

	            for (int i =0; i<nodeListe.getLength(); i++) {

	                Element elementTemperature = (Element) nodeListe.item(i);

	                int id = Integer.parseInt(elementTemperature.getElementsByTagName("id").item(0).getTextContent());
	                double degre = Double.parseDouble(elementTemperature.getElementsByTagName("degre").item(0).getTextContent());
	                String date = elementTemperature.getElementsByTagName("date").item(0).getTextContent();
	                String heure = elementTemperature.getElementsByTagName("heure").item(0).getTextContent();

	                Calendar calendrier = ModeleDate.getDate(date,heure);

	                Temperature temperature = new Temperature(id,degre,calendrier);
	                System.out.println(temperature);
	                listerTemperatures.add(temperature);
	                
	            }
	    		return listerTemperatures;
	        }	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
 	}

    public List<Temperature> listerTemperaturesMois(){

        this.listeOrdonnee.clear();

        listerLesTemperature();

        Calendar dateActuelle = Calendar.getInstance();

        for (Temperature temperature : this.listerTemperatures){

            if(temperature.getDate().get(Calendar.MONTH) == dateActuelle.get(Calendar.MONTH) && temperature.getDate().get(Calendar.YEAR) == dateActuelle.get(Calendar.YEAR)){

                this.listeOrdonnee.add(temperature);
            }
        }

        return listeOrdonnee;
    }
	
    public List<Temperature> listerTemperaturesJour(){

        this.listeOrdonnee.clear();

        listerLesTemperature();

        Calendar dateActuelle = Calendar.getInstance();

        for (Temperature temperature : this.listerTemperatures){

            if(temperature.getDate().get(Calendar.DATE) == dateActuelle.get(Calendar.DATE) && temperature.getDate().get(Calendar.MONTH) == dateActuelle.get(Calendar.MONTH) && temperature.getDate().get(Calendar.YEAR) == dateActuelle.get(Calendar.YEAR)){

                this.listeOrdonnee.add(temperature);
            }
        }


        return this.listeOrdonnee;
    }
	
	private NodeList getElementsByTagName(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}

