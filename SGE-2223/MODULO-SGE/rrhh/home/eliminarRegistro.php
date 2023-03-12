<?php
require('../php/conexionBBDD.php');
if (isset($_POST['id']) && isset($_POST['tabla'])) {
  // Obtener el parámetro "id" enviado desde el formulario
  $id = isset($_POST["id"]) ? $_POST["id"] : "";

  // Obtener el parámetro "tabla" enviado desde el formulario
  $tabla = isset($_POST["tabla"]) ? $_POST["tabla"] : "";

  // Eliminar el registro de la tabla adecuada según el valor de "tabla"
  switch ($tabla) {
    case "Departamento":
      $sql = "DELETE FROM Departamento WHERE idDept = $id";
      break;
    case "Trabajador":
      $sql = "DELETE FROM Trabajador WHERE idTrabajador = $id";
      break;
      // Agregar más casos según las tablas de la base de datos que se deseen eliminar
    default:
      die("Tabla inválida");
  }

  $stmt = $conn->prepare($sql);
  $stmt->execute();

  header('Location: index.php?mensaje=Borrado correctamente.');
  exit;
} 