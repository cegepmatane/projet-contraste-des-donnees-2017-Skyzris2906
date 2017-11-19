<?php
 
$host='localhost';
$db = 'principal';
$username = 'maxime';
$password = 'naruto77';
 
$dsn = "pgsql:host=$host;port=5432;dbname=$db;user=$username;password=$password";
 
try{
	// create a PostgreSQL database connection
	$basededonnees = new PDO($dsn);
 
}catch (PDOException $e){
	// report error message
	echo $e->getMessage();
}