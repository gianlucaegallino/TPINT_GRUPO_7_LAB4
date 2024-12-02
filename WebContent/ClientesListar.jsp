<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Cliente"%>
<%@ page import="entidades.Sexo"%>
<%@ page import="entidades.Nacionalidad"%>
<%@ page import="dao.CargarDescolgablesDao" %>
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
						<input type="text" name="dniAfiltrar" required> <input
							type="submit" value="Filtrar por DNI" name="btnFiltrarXdni">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="hidden" name="action" value="FiltrarXcuilClientes" />
						<input type="text" name="cuilAfiltrar" required> <input
							type="submit" value="Filtrar por CUIL" name="btnFiltrarXcuil">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="hidden" name="action" value="FiltrarXNombreClientes" />
						<input type="text" name="NombreAfiltrar" required> <input
							type="submit" value="Filtrar por NOMBRE" name="btnFiltrarXnombre">
						<input type="hidden" name="nombreFiltro" value="">
						<!-- Campo oculto para guardar nombreFiltro -->
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="hidden" name="action"
							value="FiltrarXApellidoClientes" /> <input type="text"
							name="ApellidoAfiltrar" required> <input type="submit"
							value="Filtrar por APELLIDO" name="btnFiltrarXapellido">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="hidden" name="action" value="FiltrarXgeneroClientes" />
						<select id="SexoCliente" name="SexoCliente" required>
							<option value="" disabled selected>Seleccione el sexo</option>
							<option value="1">Masculino</option>
							<option value="2">Femenino</option>
						</select>
						<input type="submit" value="Filtrar por GENERO" name="btnFiltrarXgenero">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="hidden" name="action" value="FiltrarXNacionalidadClientes" />
						<%
						    // Instancia el DAO
						    CargarDescolgablesDao dao = new CargarDescolgablesDao();
						    // Obtiene la lista de items del desplegable
						    ArrayList<Nacionalidad> items = dao.obtenerNacionalidades();
						%>
						<select id="NacioCliente" name="NacioCliente" required>
							<option value="" disabled selected>Seleccione la Nacionalidad</option>
							<%
						        // Itera sobre la lista de items
						        for (Nacionalidad nacio : items) {
						    %>
						        <option value="<%= nacio.getId() %>"><%= nacio.getNombre() %></option>
						    <%
						        }
						    %>
						</select>
						<input type="submit" value="Filtrar por NACIONALIDAD" name="btnFiltrarXnacionalidad">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
					<input type="hidden" name="action" value="FiltrarXFechasClientes" />
					<label for="PrimerFecha">Desde: </label>
						<input type="date" id="fechaInicial" name="fechaInicial" required> 
					<label for="SegundaFecha">Hasta: </label>
						<input type="date" id="fechaFinal" name="fechaFinal" required> 
					<input type="submit" value="Filtrar por RANGO DE FECHAS" name="btnFiltrarXfechas">
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
				%>
				<% if (tablaHTML != null && !tablaHTML.isEmpty()) { %>
			        <%= tablaHTML %>
			    <% } %>
			</tbody>
		</table>
	</div>
	<script defer src="js/clientes.js"></script>
</body>
</html>