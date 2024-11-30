<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Cliente"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="Clientes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" href="./css/EstiloClientes.css">
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
<title>Gestión de Clientes | Banco Liberación</title>
</head>
<body class="bodyInicio">
	<div class="formulario-listar-cliente" id="formListarCliente"
		style="display: block;">
		<h2>Listar Cliente</h2>
		<div class="form-container">
			<div class="fila">
				<div class="form-element">
					<input type="text"> <input type="submit"
						value="Filtrar por DNI" name="btnFiltrarXdni">
				</div>
			</div>
			<div class="fila">
				<div class="form-element">
					<input type="text"> <input type="submit"
						value="Filtrar por CUIL" name="btnFiltrarXcuil">
				</div>
			</div>
			<div class="fila">
				<div class="form-element">
					<input type="text"> <input type="submit"
						value="Filtrar por NOMBRE" name="btnFiltrarXnombre">
				</div>
			</div>
			<div class="fila">
				<div class="form-element">
					<input type="text"> <input type="submit"
						value="Filtrar por APELLIDO" name="btnFiltrarXapellido">
				</div>
			</div>
			<div class="fila">
				<div class="form-element">
					<input type="text"> <input type="submit"
						value="Filtrar por GENERO" name="btnFiltrarXgenero">
				</div>
			</div>
			<div class="fila">
				<div class="form-element">
					<input type="text"> <input type="submit"
						value="Filtrar por NACIONALIDAD" name="btnFiltrarXnacionalidad">
				</div>
			</div>
			<div class="fila">
				<div class="form-element">
					<input type="date"> <input type="date"> <input
						type="submit" value="Filtrar por RANGO DE FECHAS"
						name="btnFiltrarXfechas">
				</div>
			</div>
			<form action="SvFiltrosCliente" method="POST">
				<input type="hidden" name="filtro" value="mostrarClientes" />
				<div class="fila">
					<div class="form-element">
						<input type="submit" id="mostrarTodosClientes"
							value="Mostrar todos los clientes" name="btnMostrarCliente">
					</div>
				</div>
			</form>
		</div>
		<div>
			<form action="SvFiltrosCliente" method="POST">
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
			</form>
		</div>
	</div>
	<script defer src="js/clientes.js"></script>
</body>
</html>