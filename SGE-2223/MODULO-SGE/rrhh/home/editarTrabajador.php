<?php
require('../php/conexionBBDD.php');

if (
    isset($_POST["Nombre"], $_POST["Salario"], $_POST["idDepartamento"], $_POST["FechaAlta"], $_POST["Puesto"], $_POST["idTrabajador"]) &&
    !empty($_POST["Nombre"]) && !empty($_POST["Salario"]) && !empty($_POST["idDepartamento"]) && !empty($_POST["FechaAlta"]) && !empty($_POST["Puesto"]) && !empty($_POST["idTrabajador"])
) {

    // Obtener los datos enviados por el formulario
    $idTrabajador = $_POST["idTrabajador"];
    $nombre = $_POST["Nombre"];
    $salario = $_POST["Salario"];
    $dep = $_POST["idDepartamento"];
    $fech = $_POST["FechaAlta"];
    $puesto = $_POST["Puesto"];

    // Verificar que el departamento existe en la base de datos
    $stmt_dep = $conn->prepare("SELECT idDepartamento FROM Departamento WHERE idDepartamento=:dep");
    $stmt_dep->bindParam(':dep', $dep, PDO::PARAM_INT);
    $stmt_dep->execute();

    if (!$stmt_dep->fetch(PDO::FETCH_ASSOC)) {
        // Si el departamento no existe, redirigir a la página anterior con un mensaje de error
        header('Location: index.php');
        exit;
    }


    // Preparar la consulta para actualizar los datos en la base de datos
    $stmt = $conn->prepare("UPDATE Trabajador SET 
            Nombre=:nombre, 
            Salario=:salario, 
            idDepartamento=:dep, 
            FechaAlta=:fech, 
            Puesto=:puesto WHERE idTrabajador=:id");

    $stmt->bindParam(':id', $idTrabajador, PDO::PARAM_INT);
    $stmt->bindParam(':nombre', $nombre, PDO::PARAM_STR);
    $stmt->bindParam(':salario', $salario, PDO::PARAM_STR);
    $stmt->bindParam(':dep', $dep, PDO::PARAM_INT);
    $stmt->bindParam(':fech', $fech, PDO::PARAM_STR);
    $stmt->bindParam(':puesto', $puesto, PDO::PARAM_STR);

    // Verificar que la consulta se ejecuta correctamente
    if ($stmt->execute()) {
        header('Location: index.php');
        exit;
    }
} else {
    header('Location: index.php');
    exit;
}
