<?php
include("../checkLogin.php");
include 'cookieVisitas.php';
?>

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Home</title>
  <link rel="stylesheet" href="home.css">
</head>

<body>
  <?php require("../php/conexionBBDD.php"); ?>
  <!-- Logo -->
  <div class="contenedor">
    <img src="http://pharmadam.es/login/source/images/logo-teal-oscuro.png" alt="Logo pharmadam">
  </div>
  <!-- Pestañas -->
  <div class="tab">
    <button class="tablinks" onclick="openTab(event, 'departamentos')" id="defaultOpen">Departamentos</button>
    <button class="tablinks" onclick="openTab(event, 'trabajadores')">Trabajadores</button>
    <a href="cerrarSesion.php"><button class="logout" style="float: right;">Cerrar sesión</button></a>
  </div>
  <div class="inputs">
    <h3>Visitas: <?php echo $cookie_valor ?></h3>
  </div>
  <!-- Buscador -->
  <div class="inputs">
    <input type="text" placeholder="Buscar..." id="buscar" style="width: 50%;">
  </div>
  <!-- Tabla de departamentos -->
  <div id="departamentos" class="tabcontent">
    <table id="tablaDepartamentos">
      <thead>
        <tr>
          <th>ID</th>
          <th>NOMBRE</th>
          <th>#</th>
          <th></th>
        </tr>
        <tr>
          <td></td>
          <td></td>
        </tr>
      </thead>
      <tbody>
        <?php
        require("../php/departamentos.php");
        foreach ($resultDept as $row) {
          echo "<tr>";
          echo "<td>" . $row["idDept"] . "</td>";
          echo "<td>" . $row["Nombre"] . "</td>";
          echo "<td><input type='button' value='BORRAR' id='prueba'></a></td>";
          echo "</tr>";
        }
        ?>
      </tbody>
    </table>
    <div class="inputs">
      <?php
      // Verificamos si hay un mensaje para mostrar
      if (isset($_GET['mensaje'])) {
        $mensaje = $_GET['mensaje'];
        // Mostramos el mensaje en la página
        echo '<div>' . $mensaje . '</div>';
      }
      ?>
      <!-- Añadir departamento -->
      <form action="addDepartamento.php" method="post">
        <input type="text" placeholder="DEPARTAMENTO" name="NombreDepartamento">
        <input type="submit" value="Insertar">
      </form>
    </div>
  </div>

  <!-- Tabla de trabajadores -->
  <div id="trabajadores" class="tabcontent">
    <table id="tablaTrabajadores">
      <thead>
        <tr>
          <th>ID</th>
          <th>NOMBRE</th>
          <th>SALARIO</th>
          <th>DEPARTAMENTO</th>
          <th>ALTA</th>
          <th>PUESTO</th>
          <th>#</th>
        </tr>
      </thead>
      <tbody>
        <?php
        require("../php/trabajadores.php");
        foreach ($resultTrab as $row) {
          echo "<tr>";
          echo "<td>" . $row["idTrabajador"] . "</td>";
          echo "<td>" . $row["Nombre"] . "</td>";
          echo "<td>" . $row["Salario"] . "</td>";
          echo "<td>" . $row["idDepartamento"] . "</td>";
          echo "<td>" . $row["FechaAlta"] . "</td>";
          echo "<td>" . $row["Puesto"] . "</td>";
          echo "<td>
            <a href='formulario.php?id=" . $row["idTrabajador"] . "'><input type='button' value='EDITAR'></a>
           <input type='button' value='BORRAR'></a></td>";
          echo "</tr>";
          //. $row["Nombre"] . $row["Salario"] . $row["idDepartamento"] . $row["FechaAlta"] . $row["Puesto"] .
        }
        ?>
      </tbody>
    </table>
    <!--Añadir trabajador -->
    <div class="inputs">
      <?php
      // Verificamos si hay un mensaje para mostrar
      if (isset($_GET['mensaje'])) {
        $mensaje = $_GET['mensaje'];
        // Mostramos el mensaje en la página
        echo '<div>' . $mensaje . '</div>';
      }
      ?>
      <!--Contador de visitas -->
      <form action="addTrabajador.php" method="post">
        <input type="text" placeholder="NOMBRE" name="Nombre">
        <input type="text" placeholder="SALARIO" name="Salario">
        <input type="text" placeholder="ID_DEP" name="idDepartamento">
        <input type="text" placeholder="FECHA_ALTA" name="FechaAlta">
        <input type="text" placeholder="PUESTO" name="Puesto">
        <input type="submit" value="Insertar">
      </form>
    </div>
  </div>

  <script>
    // Manejador de pestañas
    function openTab(evt, tabName) {
      var i, tabcontent, tablinks;
      tabcontent = document.getElementsByClassName("tabcontent");
      for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
      }
      tablinks = document.getElementsByClassName("tablinks");
      for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
      }
      document.getElementById(tabName).style.display = "block";
      evt.currentTarget.className += " active";
    }

    // Abre la pestaña por defecto al cargar la página
    document.getElementById("defaultOpen").click();
    document.getElementById("prueba").addEventListener("click", editar, false)

    function editar(params) {
      var boton = document.getElementById("boton").value
      console.log("funciona");
    }

    // Función del buscador
    var busqueda = document.getElementById('buscar');

    buscaTabla = function(tablaId) {
      var table = document.getElementById(tablaId);
      var texto = busqueda.value.toLowerCase();
      var r = 0;

      // Verificar si la tabla tiene un cuerpo, usar la etiqueta <thead> en su lugar
      var tableBody = table.tBodies[0];
      if (!tableBody) {
        tableBody = table.tHead;
      }

      while (row = tableBody.rows[r++]) {
        if (row.innerText.toLowerCase().indexOf(texto) !== -1) {
          row.style.display = null;
        } else {
          row.style.display = 'none';
        }
      }
    }

    busqueda.addEventListener('keyup', function() {
      // Llamar a la función buscaTabla con el ID de la tabla correspondiente
      buscaTabla("tablaDepartamentos");
      buscaTabla("tablaTrabajadores");
    });

    // FUNCION DE PAGINACIÓN DE TABLAS

    function paginarTabla(tabla) {
      const filasPorPagina = 10; // Número de filas que se mostrarán por página
      let paginaActual = 1; // Inicialmente se muestra la página 1

      // Se obtienen todas las filas de la tabla
      const filas = tabla.getElementsByTagName('tbody')[0].getElementsByTagName('tr');

      // Se calcula el número de páginas necesarias para mostrar todas las filas
      const numPaginas = Math.ceil(filas.length / filasPorPagina);

      // Se crea un elemento "div" que contendrá los botones de paginación
      const paginador = document.createElement('div');
      paginador.classList.add('paginador');

      // Agregar propiedades CSS para centrar el elemento 'paginador' en la página
      paginador.style.display = 'flex';
      paginador.style.justifyContent = 'center';

      // Se crean los botones de paginación y se añaden al elemento "paginador"
      for (let i = 1; i <= numPaginas; i++) {
        const boton = document.createElement('button');
        boton.innerText = i;
        boton.addEventListener('click', () => cambiarPagina(i)); // Se asigna la función "cambiarPagina" al hacer clic en el botón
        paginador.appendChild(boton);
      }

      // Se inserta el elemento "paginador" justo después de la tabla
      tabla.parentNode.insertBefore(paginador, tabla.nextSibling);

      // Se muestra la página actual (inicialmente la 1)
      mostrarPagina(paginaActual);

      // Función que muestra la página "numeroPagina"
      function mostrarPagina(numeroPagina) {
        paginaActual = numeroPagina;

        // Se calculan los índices de la primera y última fila que se mostrarán en la página actual
        const primeraFila = (paginaActual - 1) * filasPorPagina;
        const ultimaFila = primeraFila + filasPorPagina;

        // Se recorren todas las filas y se muestra u oculta cada una según corresponda
        for (let i = 0; i < filas.length; i++) {
          const fila = filas[i];
          if (i >= primeraFila && i < ultimaFila) {
            fila.style.display = 'table-row';
          } else {
            fila.style.display = 'none';
          }
        }

        // Se actualizan los botones de paginación
        actualizarBotones();
      }

      // Función que cambia a la página "numeroPagina"
      function cambiarPagina(numeroPagina) {
        mostrarPagina(numeroPagina);
      }

      // Función que actualiza el estado de los botones de paginación
      function actualizarBotones() {
        const botones = paginador.getElementsByTagName('button');

        // Se recorren todos los botones y se añade o quita la clase "activo" según corresponda
        for (let i = 0; i < botones.length; i++) {
          const boton = botones[i];
          if (boton.innerText == paginaActual) {
            boton.classList.add('activo');
          } else {
            boton.classList.remove('activo');
          }
        }
      }
    }

    // Se obtienen las tablas y se les aplica la función "paginarTabla"
    const tablaDepartamentos = document.getElementById('tablaDepartamentos');
    paginarTabla(tablaDepartamentos);

    const tablaTrabajadores = document.getElementById('tablaTrabajadores');
    paginarTabla(tablaTrabajadores);

    // Función para agregar los botones de "BORRAR" a una tabla
    function agregarBotonesBorrar(selectorTabla, nombreTabla) {
      // Obtener todos los botones de "BORRAR" en la tabla
      const botonesBorrar = document.querySelectorAll(selectorTabla + " tbody tr td:last-of-type input[type='button'][value='BORRAR']");

      // Crear un formulario oculto para enviar la solicitud de eliminación
      const form = document.createElement("form");
      form.method = "POST";
      form.style.display = "none";
      document.body.appendChild(form);

      // Agregar un controlador de eventos onclick a cada botón de "BORRAR"
      botonesBorrar.forEach(boton => {
        boton.onclick = function() {
          // Obtener el ID del registro a eliminar
          const id = this.parentElement.parentElement.firstElementChild.textContent;
          // Solicitar confirmación del usuario
          if (confirm("¿Estás seguro que deseas eliminar este registro?")) {
            // Agregar un campo oculto al formulario con el ID del registro a eliminar
            const inputId = document.createElement("input");
            inputId.type = "hidden";
            inputId.name = "id";
            inputId.value = id;
            form.appendChild(inputId);

            // Agregar un campo oculto al formulario con el nombre de la tabla
            const inputTabla = document.createElement("input");
            inputTabla.type = "hidden";
            inputTabla.name = "tabla";
            inputTabla.value = nombreTabla;
            form.appendChild(inputTabla);

            // Enviar la solicitud de eliminación utilizando el formulario
            form.action = "eliminarRegistro.php";
            form.submit();
          }
        }
      });
    }

    // Llamamos a la función dinamicamente
    agregarBotonesBorrar("#tablaDepartamentos", "Departamento");
    agregarBotonesBorrar("#tablaTrabajadores", "Trabajador");
  </script>
</body>

</html>