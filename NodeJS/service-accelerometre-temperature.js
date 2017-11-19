require('await');
var http = require('http');
var adresse = "127.0.0.1";
var port = 8080;
var postgresql = require('pg');
var chaineDeConnection = 'postgres://maxime:naruto77@localhost:5432/principal';

var SQL_AJOUTER_ACCELEROMETRE = "INSERT INTO accelerometre(x,y,z,date,heure) VALUES(':x', ':y', ':z', ':date', ':heure')";
var SQL_AJOUTER_TEMPERATURE = "INSERT INTO temperature(temperature_celsius,temperature_date,temperature_heure) VALUES(':temperature_celsius', ':temperature_date', ':temperature_heure')";

var repondre = async function(requete,reponse)
{
	if(requete.method === 'POST')
	{
		if(requete.url === '/ajouter-accelerometre' || requete.url === '/ajouter-accelerometre/' )
		{	
			var message = '';
			requete.on('data', function (data) { message += data;});
			requete.on('end', async function()
				{
					var accelerometre = JSON.parse(message); 
					
					for (var i = 0; i < accelerometre.length; i++) {
						var object = accelerometre[i];
						await ajouterAccelerometre(object.x, object.y, object.z, object.date, object.heure);
					}
					
					console.log(accelerometre);
					console.log("Valeur reçu");
					
					reponse.statusCode = 200;
					reponse.setHeader('Content-type', 'application/json');
					reponse.end("Valeur ajoutée !");			
				});
		}
		else if(requete.url === '/ajouter-temperature' || requete.url === '/ajouter-temperature/' )
		{	
			var message = '';
			requete.on('data', function (data) { message += data;});
			requete.on('end', async function()
				{
					//console.log(message);
					var temperature = JSON.parse(message); 
					
					for (var i = 0; i < temperature.length; i++) {
						var object = temperature[i];
						await ajouterTemperature(object.temperature_celsius, object.temperature_date, object.temperature_heure);
					}
					
					console.log("Valeur reçu");
					
					reponse.statusCode = 200;
					reponse.setHeader('Content-type', 'application/json');
					reponse.end("Valeur ajoutée !");			
				});
		}			
	}
	
};

ajouterAccelerometre = async function(x, y, z, date, heure)
{
	basededonnees = new postgresql.Client(chaineDeConnection);
	await basededonnees.connect();
	var requeteAjouterAccelerometre = SQL_AJOUTER_ACCELEROMETRE.replace(':x', x).replace(':y', y).replace(':z', z).replace(':date', date).replace(':heure', heure);
	console.log(requeteAjouterAccelerometre);
	await basededonnees.query(requeteAjouterAccelerometre);
	return true;
}
ajouterTemperature = async function(temp_cel, temp_date, temp_heure)
{
	basededonnees = new postgresql.Client(chaineDeConnection);
	await basededonnees.connect();
	var requeteAjouterTemperature = SQL_AJOUTER_TEMPERATURE.replace(':temperature_celsius', temp_cel).replace(':temperature_date', temp_date).replace(':temperature_heure', temp_heure);
	console.log(requeteAjouterTemperature);
	await basededonnees.query(requeteAjouterTemperature);
	return true;
}

var serveur = http.createServer( repondre );

var executer = function()
{
	console.log(`Serveur: http://${adresse}:${port}/`);	
};

serveur.listen(port, adresse, executer);
