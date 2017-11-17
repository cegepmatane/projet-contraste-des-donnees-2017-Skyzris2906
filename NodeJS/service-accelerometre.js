var http = require('http');
var adresse = "127.0.0.1";
var port = 8080;
var postgresql = require('pg');
var chaineDeConnection = 'postgres://maxime:naruto77@localhost:5432/database_principal';

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
					
					reponse.statusCode = 200;
					reponse.setHeader('Content-type', 'application/json');
					reponse.end("Valeur ajoutée !");					
				});
		}
	}
};

var serveur = http.createServer( repondre );

var executer = function()
{
	console.log(`Serveur: http://${adresse}:${port}/`);	
};

serveur.listen(port, adresse, executer);
