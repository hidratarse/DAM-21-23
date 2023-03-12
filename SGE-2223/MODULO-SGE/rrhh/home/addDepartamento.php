<?php
require('../php/conexionBBDD.php');

// Comprobamos que se haya enviado el formulario
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Validamos los datos ingresados en el formulario
    $nombre = $_POST['NombreDepartamento'];

    if (empty($nombre)) {
        header('Location: index.php?mensaje=Por favor complete todos los campos.');
        exit;
    }

    // Validamos que el Nombre sea una cadena de texto
    if (!is_string($nombre)) {
        header('Location: index.php?mensaje=El Nombre debe ser una cadena de texto.');
        exit;
    }

    // Comprobamos si ya existe un departamento con el mismo nombre
    $stmt = $conn->prepare("SELECT COUNT(*) FROM Departamento WHERE Nombre = :nombre");
    $stmt->bindParam(':nombre', $nombre, PDO::PARAM_STR);
    $stmt->execute();
    $count = $stmt->fetchColumn();

    if ($count > 0) {
        // Ya existe un departamento con el mismo nombre, no se puede insertar
        header('Location: index.php?mensaje=Ya existe un departamento con el mismo nombre.');
        exit;
    }

    // Inserción en la base de datos
    $stmt = $conn->prepare("INSERT INTO Departamento (Nombre) 
    VALUES (:nombre)");
    $stmt->bindParam(':nombre', $nombre, PDO::PARAM_STR);
    $stmt->execute();

    header('Location: index.php?mensaje=Insertado correctamente.');
    exit;
}
