<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Cliente"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="Clientes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/EstiloClientes.css">
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Gestión de Clientes | Banco Liberación</title>
</head>
<body class="bodyInicio">
	<div class="formulario-listar-cliente" id="formListarCliente"
		style="display: block;">
		<h2>Listar Cliente</h2>
		<div class="form-container">
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="hidden" name="action" value="FiltrarXdniClientes" />
						<input type="text" name="dniAfiltrar"> <input
							type="submit" value="Filtrar por DNI" name="btnFiltrarXdni">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="text"> <input type="submit"
							value="Filtrar por CUIL" name="btnFiltrarXcuil">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="text"> <input type="submit"
							value="Filtrar por NOMBRE" name="btnFiltrarXnombre">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="text"> <input type="submit"
							value="Filtrar por APELLIDO" name="btnFiltrarXapellido">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="text"> <input type="submit"
							value="Filtrar por GENERO" name="btnFiltrarXgenero">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="text"> <input type="submit"
							value="Filtrar por NACIONALIDAD" name="btnFiltrarXnacionalidad">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="date"> <input type="date"> <input
							type="submit" value="Filtrar por RANGO DE FECHAS"
							name="btnFiltrarXfechas">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="hidden" name="action" value="mostrarClientes" /> <input
							type="submit" id="mostrarTodosClientes"
							value="Mostrar todos los clientes" name="btnMostrarCliente">
					</div>
				</div>
			</form>
			<hr>
		</div>
		<table class="tabla-clientes" id="tablaCliente2"
			style="display: block;">
			<thead>
				<tr>
					<th>DNI</th>
					<th>CUIL</th>
					<th>NOMBRE</th>
					<th>APELLIDO</th>
					<th>SEXO</th>
					<th>NACIONALIDAD</th>
					<th>FECHA DE NACIMIENTO</th>
					<th>DIRECCION</th>
					<th>CORREO ELECTRONICO</th>
					<th>TELEFONO</th>
				</tr>
			</thead>
			<tbody>
				<%
					String tablaHTML = (String) request.getAttribute("tablaHTML");
					out.println(tablaHTML);
				%>
			</tbody>
		</table>
	</div>
	<script defer src="js/clientes.js"></script>
</body>
</html>