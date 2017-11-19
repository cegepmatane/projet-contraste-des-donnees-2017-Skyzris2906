<?php

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';

	include "../../../basededonnees.php";
	$SQL_COMPTER_ACCELEROMETRE = "SELECT * FROM accelerometre";
	$requeteListeAccelerometre = $basededonnees->prepare($SQL_COMPTER_ACCELEROMETRE);
	$requeteListeAccelerometre->execute();
	$listeAccelerometre = $requeteListeAccelerometre->fetchAll(PDO::FETCH_OBJ);
?>
<Accelerometre>
	<nombre><?php echo count($listeAccelerometre); ?></nombre>
</Accelerometre>
