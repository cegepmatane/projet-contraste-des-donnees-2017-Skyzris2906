<?php

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	
	include "../../../basededonnees.php";
	$SQL_LISTE_TEMPERATURES = "SELECT * FROM todo";
	$requeteListeTemperatures = $basededonnees->prepare($SQL_LISTE_TEMPERATURES);
	$requeteListeTemperatures->execute();
	$listeTemperatures = $requeteListeTemperatures->fetchAll(PDO::FETCH_OBJ);
?>
<ListeTodos>
	<nombre><?php echo count($listeTemperatures); ?></nombre>
</ListeTodos>