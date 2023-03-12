<?php
include('../checkLogin.php');
require('../php/conexionBBDD.php');
$ID = $_GET["id"];

$stmt = $conn->prepare("SELECT * FROM Trabajador WHERE idTrabajador=:id");
$stmt->bindParam(':id', $ID);
$stmt->execute();
$result = $stmt->fetch(PDO::FETCH_ASSOC);
?>

<!DOCTYPE html>
<html>

<head>
	<title>Editar empleado</title>
	<link rel="stylesheet" href="formulario.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
</head>

<body>
	<div class="contenedor">
		<h2 class="text-center mt-4 name">Editar </h2>

		<form action="editarTrabajador.php" method="POST">

			<input type="hidden" id="idTrabajador" name="idTrabajador" value="<?php echo $ID ?>">

			<label for="nombre">Nombre:</label>
			<input type="text" id="nombre" name="Nombre" value="<?php echo $result['Nombre']; ?>" required pattern="[A-Za-z\s]+" title="Ingrese un nombre válido">
			<span class="error">Por favor ingrese el nombre</span>
			<br><br>

			<label for="salario">Salario:</label>
			<input type="number" id="salario" name="Salario" value="<?php echo $result['Salario']; ?>" required min="0" title="Ingrese un salario válido">
			<span class="error">Por favor ingrese el salario</span>
			<br><br>


			<label for="idDepartamento">ID de Departamento:</label>
			<input type="number" id="idDepartamento" name="idDepartamento" value="<?php echo $result['idDepartamento']; ?>" required min="0">
			<span class="error">Por favor ingrese el ID de del departamento</span>
			<br><br>

			<label for="fechaAlta">Fecha de Alta:</label>
			<input type="date" id="fechaAlta" name="FechaAlta" value="<?php echo date('Y-m-d', strtotime($result['FechaAlta'])); ?>" required>
			<span class="error">Por favor ingrese la fecha de alta</span>
			<br><br>

			<label for="puesto">Puesto:</label>
			<input type="text" id="puesto" name="Puesto" value="<?php echo $result['Puesto']; ?>" required pattern="[A-Za-z\s]+">
			<span class="error">Por favor ingrese el puesto</span>
			<br><br>

			<input type="submit" value="Guardar" class="btn mt-3">
			<a href="index.php"><button type="button" class="btn mt-3">Volver</button></a>
		</form>
	</div>
</body>

</html>