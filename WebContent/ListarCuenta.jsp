<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="InicioUsuarioBanco.jsp"%>
<%@ page import="entidades.Cuenta"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="./css/EstiloCuentas.css" />
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Gestión de Clientes | Banco Liberación</title>
</head>
<body class="bodyInicio">
	<article class="menu-gestiones">
		<a href="Cuentas.jsp" class="botonGestion">Agregar</a> <a
			href="EditarEliminarCuenta.jsp" class="botonGestion">
			Editar/Eliminar </a> <a href="ListarCuenta.jsp" class="botonGestion">Listar</a>
	</article>

	<!-- Formulario para listar cuentas -->
	<div class="formulario-listar" id="formularioListar">
		<h2>Listar Cuenta</h2>
		<form action="SlCuentas" method="post">
			<div class="formulario-listar-input">
				<label for="cbu">CBU: </label> <input type="text" id="cbuBuscar"
					name="cbuBuscar" placeholder="Ingrese el CBU" />
			</div>

			<div class="formulario-listar-input">
				<label for="tipoCuenta">Tipo de Cuenta:</label> <select
					id="tipoCuenta" name="tipoCuenta">
					<option value="">Todos</option>
					<option value="1">Caja de Ahorro</option>
					<option value="2">Cuenta Corriente</option>
				</select>
			</div>

			<div class="formulario-listar-buttons">
				<input type="submit" value="Buscar Cuenta" name="btnBuscar1" />

			</div>
		</form>
		<form action="SlCuentas" method="post">
			<input type="submit" value="Mostrar todos" name="btnBuscar" />
		</form>

		<% ArrayList<Cuenta> listaC = null; 
        if (request.getAttribute("listaCuenta") != null) { 
            listaC = (ArrayList<Cuenta>) request.getAttribute("listaCuenta"); 
        } 
     %>

		<!-- Tabla para mostrar los datos de las cuentas -->
		<table class="tabla-cuentas" id="tablaCuentas">
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
				<% if (listaC != null) {
                for (Cuenta cuenta : listaC) { %>
				<tr>
					<td><%= cuenta.getIDcliente().getIdCliente() %></td>
					<td><%= cuenta.getFecha_creacion() %></td>
					<td><%= cuenta.getCuenta().getNombre() %></td>
					<td><%= cuenta.getNumero_cuenta() %></td>
					<td><%= cuenta.getCbu() %></td>
					<td><%= cuenta.getSaldo() %></td>
				</tr>
				<% } } %>
			</tbody>
		</table>
	</div>

	<script defer src="./js/goToPage.js"></script>
</body>
</html>
