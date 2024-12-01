<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Cliente"%>
<%@ page import="entidades.Sexo"%>
<%@ page import="entidades.Nacionalidad"%>
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
						<input type="hidden" name="action" value="FiltrarXcuilClientes" />
						<input type="text" name="cuilAfiltrar"> <input
							type="submit" value="Filtrar por CUIL" name="btnFiltrarXcuil">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<input type="hidden" name="action" value="FiltrarXNombreClientes" />
						<input type="text" name="NombreAfiltrar"> <input
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
							name="ApellidoAfiltrar"> <input type="submit"
							value="Filtrar por APELLIDO" name="btnFiltrarXapellido">
					</div>
				</div>
			</form>
			<form action="SvFlitrosCliente" method="POST">
				<div class="fila">
					<div class="form-element">
						<select id="SexoCliente" name="SexoCliente" required>
							<option value="" disabled selected>Seleccione el sexo</option>
							<% 
                    // Obtener el valor de generoCliente como String
                    String generoCliente = (String) request.getAttribute("generoCliente");
                        
                    // Convertir generoCliente a int (si es posible)
                    int generoClienteId = (generoCliente != null && !generoCliente.isEmpty()) ? Integer.parseInt(generoCliente) : -1;
                        
                    ArrayList<Sexo> sexos = (ArrayList<Sexo>) request.getAttribute("sexos");
                    if (sexos != null) {
                      for (Sexo sexo : sexos) {
                        %>

							<option value="<%= sexo.getId() %>"
								<%= (sexo.getId() == generoClienteId) ? "selected" : "" %>><%= sexo.getDescripcion() %></option>

							<% 
                      }
                    } else { 
                      %>
							<option value="" disabled>No hay datos disponibles</option>

							<% } 
                %>
						</select> <input type="submit" value="Filtrar por GENERO"
							name="btnFiltrarXgenero">
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