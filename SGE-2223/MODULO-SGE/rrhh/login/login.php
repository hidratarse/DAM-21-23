<?php
// Se incluye un archivo que contiene la conexión a la base de datos
require("../php/conexionBBDD.php");
// Se inicia una sesión para manejar la autenticación del usuario
session_start();
// Si se ha recibido el campo de contraseña por POST
if (isset($_POST['password'])) {
    $pcheck = $salt . $_POST['password'];
    $pcifrado = hash("sha256", $pcheck);
    // Se prepara una consulta para obtener el id del usuario
    $stmt = $conn->prepare("SELECT id FROM Usuarios WHERE Nombre=:us AND Contrasena=:pw AND Rol=:rol");
    $stmt->bindParam(':us', $_POST['userName']);
    $stmt->bindParam(':pw', $pcifrado);
    $stmt->bindParam(':rol', $rol);
    $stmt->execute(); // 
    $result = $stmt->setFetchMode(PDO::FETCH_ASSOC);
    // Si se ha encontrado un usuario que coincide con los datos proporcionados
    if (count($stmt->fetchAll()) == 1) {
        $_SESSION['autorizado'] = true; // Se establece la variable COOKIE de sesión 'autorizado' a true
        //$expire_time = 30 * 60; // 30 minutos en segundos
        //session_set_cookie_params($expire_time);
        $conn = null;
        header("Location:../home"); // Se redirige al usuario a la página de inicio
        exit;
    } else { // Si no se ha encontrado un usuario que coincida con los datos proporcionados
        header('Location: ./index.php?error=loginIncorrecto'); // Se redirige al usuario a la página de inicio con un mensaje de error
        exit;
    }
} else { // Si no se ha recibido el campo de contraseña por POST
    header('Location: ./index.php?error=loginIncorrecto'); // Se redirige al usuario a la página de inicio con un mensaje de error
    exit;
}
