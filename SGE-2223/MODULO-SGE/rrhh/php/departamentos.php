<?php
require('conexionBBDD.php');
$stmt = $conn->prepare("SELECT * FROM Departamento");
$stmt->execute();
$resultDept = $stmt->fetchAll(PDO::FETCH_ASSOC);
