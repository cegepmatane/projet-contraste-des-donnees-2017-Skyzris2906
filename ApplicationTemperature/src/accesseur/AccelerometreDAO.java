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

import modele.Accelerometre;

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
				System.out.println(xml);
				return xml;
			}
			
			catch (MalformedURLException e) 
			{
				e.printStackTrace();
			}
		
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return null;
	}
	
			
	public Accelerometre chercherAccelerometre(int numero)
	{
		String xml = consommerService("http://localhost/" + numero);


		if(xml != null)
		{
					// interpretation du xml
					Document document = parserXML(xml);
					if (document == null) return null;
					
					//  a définir selon la table dans postgresql
					 
					String id = document.getElementsByTagName("id").item(0).getTextContent();
					String pixel = document.getElementsByTagName("pixel ").item(0).getTextContent();
					String date = document.getElementsByTagName("date").item(0).getTextContent();
					String heure = document.getElementsByTagName("heure").item(0).getTextContent();
					
					System.out.println("Variables trouvees " + numero + " " + pixel + " " + date + " "  + heure);
					
					Accelerometre accelerometre = new Accelerometre();
							// accelerometre.setId(Integer.parseInt(id));
					return accelerometre;
			}	
	
			return null;
		}
	
	
	public List<Accelerometre> listerAccelerometres()
	 	{
	 		// Récupérer le xml
			String xml = consommerService("http://localhost/");		
	 	
	 		// Interprétation du xml - construire les modeles
			
			if(xml != null)
	 		{
	 				Document document = parserXML(xml);
	 				if (document == null) return null;
	 				
	 
	 				ArrayList<Accelerometre> listeAccelerometres = new ArrayList<Accelerometre>();
	 				NodeList listeNoeudsAccelerometres = document.getElementsByTagName("accelerometre");
	 				for(int position = 0; position < listeNoeudsAccelerometres.getLength(); position++)
	 				{
	 					Element elementAccelerometre = (Element)listeNoeudsAccelerometres.item(position);
	 					//System.out.println("Tagname=" + elementAccelerometre.getTagName());
	 					
	 					String numero = document.getElementsByTagName("id").item(0).getTextContent();
	 					String pixel = document.getElementsByTagName("pixel ").item(0).getTextContent();
	 					String date = document.getElementsByTagName("date").item(0).getTextContent();
	 					String heure = document.getElementsByTagName("heure").item(0).getTextContent();
	 					
	 					Accelerometre accelerometre = new Accelerometre();
	 				//	accelerometre.setId(Integer.parseInt(id)); // TODO : robustesse 
						listeAccelerometres.add(accelerometre);
	 				}
	 				return listeAccelerometres;
	 		}		
			return null;
	 	}

}
