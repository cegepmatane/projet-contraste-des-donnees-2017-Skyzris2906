package accesseur;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modele.Temperature;

public class TemperatureDAO {
	
	public Temperature chercherTemperature(int id)
	{

		String xml = null;
			try
			{
				// récupere le XML
				URL urlServiceTemperature = new URL ("http://localhost/principale/temperature");
				URLConnection serviceTemperature = urlServiceTemperature.openConnection();
				InputStream fluxTemperature = serviceTemperature.getInputStream();
				
				Scanner lecteur = new Scanner(fluxTemperature).useDelimiter("\\A");
				xml = lecteur.hasNext() ? lecteur.next() : "";
				System.out.println(xml);
			}
			
			catch (MalformedURLException e) 
			{
				e.printStackTrace();
			}
		
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		
		
		if(xml != null)
		{
			try 
			{
				// interpretation du xml
				DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				@SuppressWarnings("deprecation")
				Document document = parseur.parse(new StringBufferInputStream(xml));
				
				//  a définir selon la table dans postgresql
				 
				String numero = document.getElementsByTagName("id").item(0).getTextContent();
				String pixel = document.getElementsByTagName("pixel ").item(0).getTextContent();
				String date = document.getElementsByTagName("date").item(0).getTextContent();
				String heure = document.getElementsByTagName("heure").item(0).getTextContent();
				
				System.out.println("Variables trouvees " + id + " " + pixel + " " + date + " "  + heure);
				
				Temperature temperature = new Temperature();
						// temperature.setId(Integer.parseInt(id));
				return temperature;
				
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	public List<Temperature> listerTemperatures()
	 	{
	 		// Récupérer le xml
	 		String xml = null;
	 		try {
	 			URL urlServiceTemperature = new URL ("http://localhost/principale/temperature");
				URLConnection serviceTemperature = urlServiceTemperature.openConnection();
				InputStream fluxTemperature = serviceTemperature.getInputStream();
	 			
				Scanner lecteur = new Scanner(fluxTemperature).useDelimiter("\\A");
				xml = lecteur.hasNext() ? lecteur.next() : "";
				System.out.println(xml);
	 			
	 		} catch (MalformedURLException e) {
				e.printStackTrace();
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}		
	 	
	 		// Interprétation du xml - construire les modeles
			if(xml != null)
	 		{
	 			try 
	 			{
	 				DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	 				@SuppressWarnings("deprecation")
	 				Document document = parseur.parse(new StringBufferInputStream(xml));
	 				
	 
	 				ArrayList<Temperature> listeTemperatures = new ArrayList<Temperature>();
	 				NodeList listeNoeudsTemperatures = document.getElementsByTagName("temperature");
	 				for(int position = 0; position < listeNoeudsTemperatures.getLength(); position++)
	 				{
	 					Element elementTemperature = (Element)listeNoeudsTemperatures.item(position);
	 					//System.out.println("Tagname=" + elementTemperature.getTagName());
	 					
	 					String numero = document.getElementsByTagName("id").item(0).getTextContent();
	 					String pixel = document.getElementsByTagName("pixel ").item(0).getTextContent();
	 					String date = document.getElementsByTagName("date").item(0).getTextContent();
	 					String heure = document.getElementsByTagName("heure").item(0).getTextContent();
	 					
	 					Temperature temperature = new Temperature();
	 				//	temperature.setId(Integer.parseInt(id)); // TODO : robustesse 
						listeTemperatures.add(temperature);
	 				}
	 				return listeTemperatures;
	 				
	 				
	 			} catch (ParserConfigurationException e) {
	 				e.printStackTrace();
	 			} catch (SAXException e) {
	 				e.printStackTrace();
	 			} catch (IOException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	 		}		
	 		
			
			return null;
	 	}

}
