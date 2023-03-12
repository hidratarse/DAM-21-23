<?php
$servername = "192.168.1.127";
$username = "root";
$password = "yes";

try {
  $conn = new PDO("mysql:host=$servername;port=63033; dbname=mysql", $username, $password);
  // set the PDO error mode to exception
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  echo "Connected successfully";
} catch(PDOException $e) {
  echo "Connection failed: " . $e->getMessage();
}
?> 