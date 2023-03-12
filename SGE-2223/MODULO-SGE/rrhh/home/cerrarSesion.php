<?php
session_start(); // Iniciar sesión

// Si la variable de sesión 'login' está establecida, destrúyela
if (isset($_SESSION['login'])) {
    session_destroy();
}

// Redirigir al usuario a la página de inicio de sesión
header("Location: http://pharmadam.es/login/");
exit();
