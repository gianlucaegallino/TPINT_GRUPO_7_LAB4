<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="InicioUsuarioBanco.jsp"%>
<%@ page import="entidades.Cuenta"%>
<%@ page import="java.util.ArrayList"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="./css/EstiloCuentas.css">
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Gestión de Cuenta | Banco Liberación</title>
</head>
<body class="bodyInicio">
	<article class="menu-gestiones">
		<a href="Cuentas.jsp" class="botonGestion">Agregar</a> <a
			href="EditarEliminarCuenta.jsp" class="botonGestion">Editar/Eliminar</a>
		<a href="ListarCuenta.jsp" class="botonGestion">Listar</a>
	</article>

	<!-- Formulario para editar/eliminar cuenta -->
	<div class="formulario-editar-eliminar" id="formularioEditarEliminar"
		style="display: none">
		<h2>Editar/Eliminar Cuenta</h2>
		<form action="SlCuentas" method="POST">
			<input type="hidden" name="action" value="BuscarCuentas" />
			<label for="cbuBuscar">CBU de Cuenta:</label> 
			<input type="text" id="cbuBuscar" name="cbuBuscar"
				placeholder="Ingrese N° de CBU para buscar" required> 
			<input type="submit" value="Buscar Cuenta" name="btnBuscar2" />
		</form>

		<%
        ArrayList<Cuenta> listaC = null;
        if (request.getAttribute("listaCuenta") != null) {
            listaC = (ArrayList<Cuenta>) request.getAttribute("listaCuenta");
        }
        %>

		<%
          if (listaC != null) {
              for (Cuenta cuenta : listaC) {
        %>
        <form action="SICuentas" method="POST">
        	<input type="hidden" name="action" value="modificarEliminarCuenta">
			<!-- Tabla para mostrar los datos de la cuenta -->
			<table class="tabla-cuentas" id="tablaCuentas" style="display: block;">
				<thead>
					<tr>
						<th>ID Cliente</th>
						<th>Fecha de Creación</th>
						<th>Tipo de Cuenta</th>
						<th>N° Cuenta</th>
						<th>CBU</th>
						<th>Saldo</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=cuenta.getIDcliente().getIdCliente()%></td>
						<td><%=cuenta.getFecha_creacion()%></td>
						<td><%=cuenta.getCuenta().getNombre()%></td>
						<td><%=cuenta.getNumero_cuenta()%></td>
						<td class="editable"><span class="campo-valor"><%= cuenta.getCbu() %></span>
							<input type="text" name="cbu" value="<%= cuenta.getCbu() %>" placeholder="Nuevo CBU" style="display: none;"></td>
						<td class="editable"><span class="campo-valor"><%= cuenta.getSaldo() %></span>
							<input type="text" name="saldo" value="<%= cuenta.getSaldo() %>" placeholder="Nuevo Saldo" style="display: none;"></td>
					</tr>
				</tbody>
			</table>
			<br>
			<div class="botones-modificar-eliminar">
				<button class="botonModificar" id="btnModificar" name="btnModificar" type="button">Modificar</button>
				<button class="botonEliminar" id="btnEliminar" name="btnEliminar" type="button">Eliminar</button>
					<input type="submit" value="Guardar" name="btnGuardar" onClick="confirmar()" style="display: none;"/>
					<!-- Botón Guardar oculto -->
					<input type="submit" value="Cancelar" id="btnCancelar" name="btnCancelar" style="display: none;"/>
					<!-- Botón Cancelar oculto -->
					<input type="submit" value="Eliminar" onClick="confirmar()" name="Eliminar" style="display: none;"/>
			</div>
		</form>
		<%
            }
        }
        %>

		<!-- Mostrar mensaje de éxito o error -->
		<%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
        %>
		<p class="mensaje"><%=mensaje%></p>
		<%
        }
        %>

	</div>
	<script defer src="js/cuentas.js"></script>
	<script type="text/javascript">
        function confirmar() {
            let response = confirm("Esta seguro que quiere continuar?");
            if (response) {
                document.formCuentas.submit();
            }
        }
    </script>
</body>
</html>