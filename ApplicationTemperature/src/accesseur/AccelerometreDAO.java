package accesseur;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
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

public class AccelerometreDAO {
	
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
				URL urlServiceAccelerometre = new URL (url);
				URLConnection serviceAccelerometre = urlServiceAccelerometre.openConnection();
				InputStream fluxAccelerometre = serviceAccelerometre.getInputStream();
				
				Scanner lecteur = new Scanner(fluxAccelerometre).useDelimiter("\\A");
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
			
	public List<Accelerometre> listerAccelerometre()
	 	{
		 		// Récupérer le xml
				//String xml = consommerService("http://********************/service_accelerometre/accelerometre/liste/index.php");		
			String xml = consommerService("file:///C:/AndroidStudioProjects/projet-contraste-des-donnees-2017-Skyzris2906/ApplicationTemperature/src/vue/accelerometre.xml");
			
			try {
			 		// Interprétation du xml - construire les modeles
				if (xml != null) 
				{
					 List<Accelerometre> listeAccelerometre =  new ArrayList<>();
		            DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		            Document document = parseur.parse(new StringBufferInputStream(xml));
		
		            NodeList nodeListe = document.getElementsByTagName("Accelerometre");
		
		            for (int i =0; i<nodeListe.getLength(); i++) {
		
		                Element elementAccelerometre = (Element) nodeListe.item(i);
		
		                int id = Integer.parseInt(elementAccelerometre.getElementsByTagName("id").item(0).getTextContent());
		                double x = Double.parseDouble(elementAccelerometre.getElementsByTagName("x").item(0).getTextContent());
		                double y = Double.parseDouble(elementAccelerometre.getElementsByTagName("y").item(0).getTextContent());
		                double z = Double.parseDouble(elementAccelerometre.getElementsByTagName("z").item(0).getTextContent());
		                String date = elementAccelerometre.getElementsByTagName("date").item(0).getTextContent();
		                String heure = elementAccelerometre.getElementsByTagName("heure").item(0).getTextContent();
		
		                Calendar calendrier = ModeleDate.getDate(date,heure);
		                Accelerometre accelerometre = new Accelerometre(id,x,y,z,calendrier);
		                listeAccelerometre.add(accelerometre);
		            }
		            return listeAccelerometre;
		        }
		    }catch (Exception ex){
		    	ex.printStackTrace();
		    }	
				return null;
	 	}

	private NodeList getElementsByTagName(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}