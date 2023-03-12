<?php
session_start();
// Verificamos si la variable de sesión 'login' existe y su valor es igual a false
if(!isset($_SESSION['login']) || !$_SESSION['login']) {
    // Si el usuario no está autorizado, redirigimos a la página de inicio de sesión
    header("Location: http://pharmadam.es/login/"); // Utilizamos una ruta absoluta para la redirección
    exit(); // Terminamos la ejecución del script para prevenir cualquier otra acción
}
// Si el usuario está autorizado, el código continúa ejecutándose normalmente
