<?php
	$usager = 'postgres';
	$motdepasse = 'Sql1995';
	$hote = 'localhost';
	$base = 'principale';
	$dsn = 'mysql:dbname='.$base.';host=' . $hote;
	$basededonnees = new PDO($dsn, $usager, $motdepasse);
?>
