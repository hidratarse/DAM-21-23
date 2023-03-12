<?php
        session_start();
        if(!isset($_POST['passw'])){
                $_SESSION['intentos']=5;
                var_dump($_SESSION);
        }
        if(isset($_POST['passw'])){
                $test=$_POST['passw'];
                $_SESSION['intentos']--;
                if ($test=='miau') {
                        header("Location: home.php");
                }
        }
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
       <form action="" method="post">
                Contraseña <input type="text" name="passw" id="passw">
                <input type="submit" value="enviar">
       </form>  
</body>
</html>
