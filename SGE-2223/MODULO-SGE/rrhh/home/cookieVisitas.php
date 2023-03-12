<?php
// Nombre de la cookie
$cookie_nombre = "visitas";

// Tiempo de expiración de la cookie (1 día)
$cookie_tiempo = time() + (86400 * 1);

// Comprueba si la cookie ya existe
if (!isset($_COOKIE[$cookie_nombre])) {
    // Si no existe, crea la cookie con valor 1
    $cookie_valor = 1;
    setcookie($cookie_nombre, $cookie_valor, $cookie_tiempo, "/");
} else {
    // Si existe, incrementa el valor en 1
    $cookie_valor = $_COOKIE[$cookie_nombre] + 1;
    setcookie($cookie_nombre, $cookie_valor, $cookie_tiempo, "/");
}
