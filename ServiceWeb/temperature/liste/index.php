<?php

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';

	include "../../basededonnees.php";
	$SQL_LISTE_TEMPERATURES = "SELECT * FROM temperature";
	$requeteListeTemperatures = $basededonnees->prepare($SQL_LISTE_TEMPERATURES);
	$requeteListeTemperatures->execute();
	$listeTemperatures = $requeteListeTemperatures->fetchAll(PDO::FETCH_OBJ);
?>
<listeTemperatures>
<?php
foreach($listeTemperatures as $temp)
{
?>
	<Temperature>
		<temperature><?=$temperature->temperature_celsius?></temperature>
		<date><?=$temperature->temperature_date?></date>
		<heure><?=$temperature->temperature_heure?></heure>
	</Temperature>
<?php
}
?>
</listeTemperatures>
