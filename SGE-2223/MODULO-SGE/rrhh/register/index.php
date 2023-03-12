<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Registrar usuario</title>
    <link rel="stylesheet" href="../login/login.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
</head>

<body>
    <div class="contenedor">
        <div class="text-center mt-4 name">Registrar usuario</div>
        <?php
        // Mira si hay algún error en la url
        if (isset($_GET['error'])) {
            $error = $_GET['error'];
            if ($error == 'userExists') {
                echo '<p style="color:red;">Error: El usuario ya existe.</p>';
            }
        }
        ?>
        <form class="p-3 mt-3" onsubmit="return validarFormulario()" action="registrar.php" method="post">
            <div class="form-field d-flex align-items-center">
                <span class="far fa-user"></span>
                <input type="text" name="userName" id="userName" placeholder="Usuario" />
            </div>
            <div class="form-field d-flex align-items-center">
                <span class="far fa-user"></span>
                <input type="text" name="email" id="email" placeholder="Email" />
            </div>
            <div class="form-field d-flex align-items-center">
                <span class="fas fa-key"></span>
                <input type="password" name="password" id="pwd" placeholder="Contraseña" />
            </div>
            <div class="form-field d-flex align-items-center">
                <span class="fas fa-key"></span>
                <input type="password" name="repeatPassword" id="repeatPwd" placeholder="Repetir Contraseña" />
            </div>
            <button class="btn mt-3">Registrar</button>
        </form>
        <div class="text-center fs-6">
            <a href="../login/">Volver</a>
        </div>
    </div>

    <script>
        // Script para validar el formulario, retornará true o false
        function validarFormulario() {
            var userName = document.getElementById("userName").value;
            var password = document.getElementById("pwd").value;
            var repeatPassword = document.getElementById("repeatPwd").value;
            var email = document.getElementById("email").value;

            if (userName == "") {
                alert("Por favor, introduzca un nombre de usuario");
                return false;
            }

            if (email == "") {
                alert("Por favor, introduzca un email");
                return false;
            }

            if (password == "") {
                alert("Por favor, introduzca una contraseña");
                return false;
            }

            if (repeatPassword == "") {
                alert("Por favor, repita la contraseña");
                return false;
            }

            if (password != repeatPassword) {
                alert("Las contraseñas no coinciden");
                return false;
            }

            return true;
        }
    </script>
</body>

</html>