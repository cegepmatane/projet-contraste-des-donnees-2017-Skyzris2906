<?php

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';

	include "../../basededonnees.php";
	$SQL_LISTE_ACCELEROMETRE = "SELECT * FROM accelerometre";
	$requeteListeAccelerometre = $basededonnees->prepare($SQL_LISTE_ACCELEROMETRE);
	$requeteListeAccelerometre->execute();
	$listeAccelerometre = $requeteListeAccelerometre->fetchAll(PDO::FETCH_OBJ);
?>
<ListeAccelerometre>
<?php
foreach($listeAccelerometre as $accelerometre)
{
?>
	<Accelerometre>
		<id><?=$accelerometre->id?></id>
		<x><?=$accelerometre->x?></x>
		<y><?=$accelerometre->y?></y>
		<z><?=$accelerometre->z?></z>
		<date><?=$accelerometre->date?></date>
		<heure><?=$accelerometre->heure?></heure>
	</Accelerometre>
<?php
}
?>
</ListeAccelerometre>
