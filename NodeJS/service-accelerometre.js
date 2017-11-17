var http = require('http');
var adresse = "127.0.0.1";
var port = 8080;

var repondre = async function(requete,reponse)
{
	if(requete.method === 'POST')
	{
		console.log("J'ai un POST");
	}
};

var serveur = http.createServer( repondre );

var executer = function()
{
	console.log(`Serveur: http://${adresse}:${port}/`);	
};

serveur.listen(port, adresse, executer);
