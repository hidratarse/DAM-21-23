<?php
include('../checkLogin.php');
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Iniciar sesisón</title>
  <link rel="stylesheet" href="login.css" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
</head>

<body>
  <div class="contenedor">
    <div class="logo">
      <img src="http://pharmadam.es/login/source/images/logo-teal-oscuro.png" alt="" />
    </div>
    <div class="text-center mt-4 name">Recursos Humanos</div>
    <form class="p-3 mt-3" action="login.php" method="post">
      <div class="form-field d-flex align-items-center">
        <span class="far fa-user"></span>
        <input type="text" name="userName" id="userName" placeholder="Usuario" />
      </div>
      <div class="form-field d-flex align-items-center">
        <span class="fas fa-key"></span>
        <input type="password" name="password" id="pwd" placeholder="Contraseña" />
      </div>
      <button class="btn mt-3">Iniciar Sesión</button>
    </form>
    <div class="text-center fs-6">
      <a href="../register/">Registrarse</a>
    </div>
  </div>
</body>

</html>