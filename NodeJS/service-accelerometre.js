require('await');
var http = require('http');
var adresse = "127.0.0.1";
var port = 8080;
var postgresql = require('pg');
var chaineDeConnection = 'postgres://maxime:naruto77@localhost:5432/database_principal';

var SQL_AJOUTER_ACCELEROMETRE = "INSERT INTO accelerometre(x,y,z,date,heure) VALUES(':x', ':y', ':z', ':date', ':heure')";

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
					console.log(accelerometre);
					await ajouterAccelerometre(accelerometre);
					
					reponse.statusCode = 200;
					reponse.setHeader('Content-type', 'application/json');
					reponse.end("Valeur ajoutée !");			
				});
		}		
	}
	
};

ajouterAccelerometre = async function(accelerometre)
{
	basededonnees = new postgresql.Client(chaineDeConnection);
	await basededonnees.connect();
	var requeteAjouterAccelerometre = SQL_AJOUTER_ACCELEROMETRE.replace(':x', accelerometre.x).replace(':y', accelerometre.y).replace(':z', accelerometre.z).replace(':date', accelerometre.date).replace(':heure', accelerometre.heure);
	console.log(requeteAjouterAccelerometre);
	await basededonnees.query(requeteAjouterAccelerometre);
	return true;
}

var serveur = http.createServer( repondre );

var executer = function()
{
	console.log(`Serveur: http://${adresse}:${port}/`);	
};

serveur.listen(port, adresse, executer);
