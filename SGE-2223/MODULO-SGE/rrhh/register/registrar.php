<?php
require("../php/conexionBBDD.php");

// Código para registrar usuarios en la base de datos.
// Requiere el archivo "conexionBBDD.php" que contiene la conexión a la base de datos.
// Los datos del usuario se recogen del formulario de registro mediante el método POST.

// Se recogen los valores de los campos del formulario
$user = $_POST['userName'];
$pw = $_POST['password'];
$em = $_POST['email'];
$r = $rol; // Valor RRHH predeterminado para recursos humanos

// Se valida si el usuario ya existe en la base de datos
$stmt = $conn->prepare("SELECT COUNT(*) FROM Usuarios WHERE Nombre = ?");
$stmt->execute([$user]);
$userExists = $stmt->fetchColumn();

if ($userExists > 0) {
  // Si el usuario ya existe, se redirige a la página de inicio con un mensaje de error
  header('Location: ./index.php?error=userExists');
  exit;
}

// Se genera una contraseña cifrada utilizando el algoritmo SHA256 y una sal almacenada en la variable $salt
$saltpass = $salt . $pw;
$pcifrado = hash("sha256", $saltpass);

// Se insertan los datos del usuario en la tabla Usuarios de la base de datos
$stmt = $conn->prepare("INSERT INTO Usuarios (Nombre, Contrasena, Email, Rol)
VALUES (?,?,?,?)");
$stmt->execute([$user, $pcifrado, $em, $r]);

// Se redirige al usuario a la página de inicio de sesión
header('Location: ../login');
exit;
