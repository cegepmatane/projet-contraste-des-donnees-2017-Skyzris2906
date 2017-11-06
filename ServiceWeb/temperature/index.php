<?php

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';

	include "../basededonnees.php";

	//print_r($_GET);
	$idTemperature = filter_input(INPUT_GET, 'todo', FILTER_VALIDATE_INT);

	$SQL_TROUVER_TEMPERATURE = "SELECT * FROM temperature WHERE id = :id";
	$requeteTrouverTemperature = $basededonnees->prepare($SQL_TROUVER_TEMPERATURE);
	$requeteTrouverTemperature->bindParam(':id', $idTemperature, PDO::PARAM_INT);
	$requeteTrouverTemperature->execute();
	$temperature = $requeteTrouverTemperature->fetch(PDO::FETCH_OBJ);
?>
<Temperature>
	<temperature><?=$temperature->temperature_celsius?></temperature>
	<date><?=$temperature->temperature_date?></date>
	<heure><?=$temperature->temperature_heure?></heure>
</Temperature>
