<?php

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';

	include "../basededonnees.php";

	//print_r($_GET);
	$idAccelerometre = filter_input(INPUT_GET, 'accelerometre', FILTER_VALIDATE_INT);

	$SQL_TROUVER_ACCELEROMETRE = "SELECT * FROM accelerometre WHERE id = :id";
	$requeteTrouverAccelerometre = $basededonnees->prepare($SQL_TROUVER_ACCELEROMETRE);
	$requeteTrouverAccelerometre->bindParam(':id', $idAccelerometre, PDO::PARAM_INT);
	$requeteTrouverAccelerometre->execute();
	$accelerometre = $requeteTrouverAccelerometre->fetch(PDO::FETCH_OBJ);
?>
<Accelerometre>
	<id><?=$accelerometre->id?></id>
	<x><?=$accelerometre->x?></x>
	<y><?=$accelerometre->y?></y>
	<z><?=$accelerometre->z?></z>
	<date><?=$accelerometre->date?></date>
	<heure><?=$accelerometre->heure?></heure>
</Accelerometre>
