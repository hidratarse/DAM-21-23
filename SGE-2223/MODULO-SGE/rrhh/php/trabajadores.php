<?php
require('conexionBBDD.php');
$stmt = $conn->prepare("SELECT * FROM Trabajador");
$stmt->execute();
$resultTrab = $stmt->fetchAll(PDO::FETCH_ASSOC);
