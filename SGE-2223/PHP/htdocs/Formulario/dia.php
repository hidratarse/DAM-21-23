<?php 
$day = $_GET['dia'];

switch ($day) {
    case "lunes":
        echo "es lunes";
        break;
    
    default:
        echo "no es lunes";
        break;
}
?>