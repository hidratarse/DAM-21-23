<?php
// Incluimos el archivo de conexión a la base de datos
require('../php/conexionBBDD.php');

// Comprobamos que se haya enviado el formulario
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Validamos los datos ingresados en el formulario
    $nombre = trim($_POST['Nombre']); // Obtenemos el nombre y eliminamos los espacios en blanco
    $salario = filter_var($_POST['Salario'], FILTER_VALIDATE_INT); // Validamos el salario como un número entero
    $idDept = filter_var($_POST['idDepartamento'], FILTER_VALIDATE_INT); // Validamos el id de departamento como un número entero
    $fechaAlta = DateTime::createFromFormat('Y-m-d', $_POST['FechaAlta']); // Obtenemos la fecha de alta del formulario
    $puesto = trim($_POST['Puesto']); // Obtenemos el puesto y eliminamos los espacios en blanco

    if (empty($nombre) || empty($salario) || empty($idDept) || empty($puesto)) {
        header('Location: index.php?mensaje=Por favor complete todos los campos.'); // Si falta algún campo, redireccionamos a la página de inicio con un mensaje de error
        exit;
    }

    if (!is_string($nombre)) {
        header('Location: index.php?mensaje=El Nombre debe ser una cadena de texto.'); // Si el nombre no es una cadena de texto, redireccionamos a la página de inicio con un mensaje de error
        exit;
    }

    if (!$salario || !$idDept) {
        header('Location: index.php?mensaje=El Salario y el ID de Departamento deben ser números enteros.'); // Si el salario o el id de departamento no son números enteros, redireccionamos a la página de inicio con un mensaje de error
        exit;
    }

    if (!$fechaAlta) {
        $fechaAlta = date('Y-m-d'); // Si no se proporciona una fecha de alta válida, utilizamos la fecha actual
    } else {
        $fechaAlta = $fechaAlta->format('Y-m-d'); // Si se proporciona una fecha de alta válida, la formateamos correctamente
    }

    // Preparamos la consulta SQL para insertar los datos en la base de datos
    $stmt = $conn->prepare("INSERT INTO Trabajador (Nombre, Salario, idDepartamento, FechaAlta, Puesto) 
VALUES (:nombre, :salario, :dep, :fech, :puesto)");

    // Asignamos los parámetros de la consulta SQL
    $stmt->bindParam(':nombre', $nombre, PDO::PARAM_STR);
    $stmt->bindParam(':salario', $salario, PDO::PARAM_INT);
    $stmt->bindParam(':dep', $idDept, PDO::PARAM_INT);
    $stmt->bindParam(':fech', $fechaAlta, PDO::PARAM_STR);
    $stmt->bindParam(':puesto', $puesto, PDO::PARAM_STR);

    // Ejecutamos la consulta SQL
    $stmt->execute();

    // Redirigimos a la página de éxito
    header('Location: index.php?mensaje=El trabajador se ha insertado correctamente.');
    exit;
}
