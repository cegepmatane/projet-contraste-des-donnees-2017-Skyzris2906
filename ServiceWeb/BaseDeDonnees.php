<?php
	$usager = 'root';
	$motdepasse = 'toor';
	$hote = 'localhost';
	$base = 'devoir_integrite';
	$dsn = 'mysql:dbname='.$base.';host=' . $hote;
	$basededonnees = new PDO($dsn, $usager, $motdepasse);
?>