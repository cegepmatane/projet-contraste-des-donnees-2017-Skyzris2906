package ApplicationTemperature;

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

public class TemperatureService 
{
	public static void main (String[] args)
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
				 
				String id = document.getElementsByTagName("id").item(0).getTextContent();
				String temperatureCelcius = document.getElementsByTagName("temperature Celcius").item(0).getTextContent();
				String temperatureHeure = document.getElementsByTagName("temperature heure").item(0).getTextContent();
				String temperatureDate = document.getElementsByTagName("temperature date").item(0).getTextContent();
				
				System.out.println("Variables trouvees " + id + " " + temperatureCelcius + " " + temperatureHeure + " "  + temperatureDate);
				
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
