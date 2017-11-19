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
	
	private String lireBalise(Element element, String balise)
	{
 		return element.getElementsByTagName("id").item(0).getTextContent();
 	}
	
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
			catch (MalformedURLException e){
				e.printStackTrace();
			}

			catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}
			
	public Accelerometre TrouverAccelerometre(int numero)
	{
		String xml = consommerService("http://localhost/temperature/liste" + numero);
		if(xml != null)
		{
					// interpretation du xml
					Document document = parserXML(xml);
					if (document == null) return null;
					
					//  a définir selon la table dans postgresql
					 
					Element element = document.getDocumentElement();
					
					String id = lireBalise(element,"id");
					String accelerometre = lireBalise(element,"accelerometre");
					String date = lireBalise(element,"date");
					String heure = lireBalise(element,"heure");
					
					System.out.println("Resultats trouves " + id + " " + accelerometre + " " + date + " "  + heure);
					
					Accelerometre accelerometre = new Accelerometre();
					// accelerometre.setId(Integer.parseInt(id));
					return accelerometre;
			}	
	
			return null;
		}
	
	public List<Accelerometre> listerAccelerometre()
	 	{
	 		// Récupérer le xml
			String xml = consommerService("http://localhost/temperature/liste/");		
	 	
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
	 					
	 					String id = lireBalise(elementAccelerometre,"id");
						String accelerometre = lireBalise(elementAccelerometre,"accelerometre");
						String date = lireBalise(elementAccelerometre,"date");
						String heure = lireBalise(elementAccelerometre,"heure");
	 					
						Accelerometre accelerometre = new Accelerometre();
		 				//	accelerometre.setId(Integer.parseInt(id)); // TODO : robustesse 
							listeAccelerometres.add(accelerometre);
		 				}
		 				return listeAccelerometres;
	 		}		
			return null;
	 	}
}
