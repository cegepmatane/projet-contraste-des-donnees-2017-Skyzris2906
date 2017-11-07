package ApplicationTemperature;

public class TemperatureService 
{
	public static void main (String[] args)
	{
		try
		{
			// récupere le XML
			URL urlServiceTemperature = new URL ("http://localhost/principale/temperature");
			URLConnection serviceTemperature = urlServiceTemperature.openConnection();
			InputStream fluxTemperature = serviceTemperature.getInputStream();
			
			Scanner lecteur = new Scanner(fluxTemperature).useDelimiter("\\A");
			String xml = lecteur.hasNext() ? lecteur.next() : "";
			System.out.println(xml);
		}
		
		catch (MalFormedURLException e) 
		{
			e.printStackTrace();
		}
	
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
