<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="Clientes.jsp"%>
<%@ page import="Negocio.NegCliente"%>
<%@ page import="entidades.Cliente"%>
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
	<!-- Form de modificar o eliminar cliente -->
	<div class="formulario-modificar-eliminar" id="formularioMoECliente"
		style="display: block;">
		<h2>Editar o Eliminar Cliente</h2>
		<%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
        %>
		<p style="color: red;"><%= mensaje %></p>
		<%
        }
        %>
		<form action="SIClientes" method="POST">
			<input type="hidden" name="action" value="buscarPorDNI"> <label
				for="numeroDNIaBuscar">Numero de DNI:</label> <input type="text"
				id="numeroDNIaBuscarEditar" name="numeroDNIaBuscar"
				placeholder="Ingrese un DNI a buscar" required> <input
				type="submit" value="Buscar DNI" name="btnBuscarDNI">
		</form>
		<hr>

		<%
            Cliente cliente = (Cliente) request.getAttribute("cliente");
            if (cliente != null) {
        %>
		<form action="SIClientes" method="POST">
			<input type="hidden" name="action" value="modificarEliminarCliente">
			<input type="hidden" name="idCliente"
				value="<%= cliente.getIdCliente() %>">
			<!-- Agrega el idCliente oculto -->
			<input type="hidden" name="dniCliente"
				value="<%= cliente.getDni() %>">
			<!-- Tabla para mostrar los datos de la cuenta -->
			<!-- Tabla para mostrar los datos de la cuenta -->
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
					<tr>
						<td><%= cliente.getDni() %></td>
						<td><%= cliente.getCuil() %></td>
						<td><%= cliente.getNombre() %></td>
						<td><%= cliente.getApellido() %></td>
						<td><%= cliente.getSexo().getDescripcion() %></td>
						<td><%= cliente.getNacionalidad().getNombre() %></td>
						<td><%= cliente.getFecha_nacimiento() %></td>
						<td class="editable"><span class="campo-valor"><%= cliente.getDireccion().getDireccion() %></span>
							<input type="text" name="direccion"
							value="<%= cliente.getDireccion().getDireccion() %>"
							style="display: none;"></td>
						<td class="editable"><span class="campo-valor"><%= cliente.getCorreo_electronico() %></span>
							<input type="text" name="correo"
							value="<%= cliente.getCorreo_electronico() %>"
							style="display: none;"></td>
						<td class="editable"><span class="campo-valor"><%= cliente.getTelefono() %></span>
							<input type="text" name="telefono"
							value="<%= cliente.getTelefono() %>" style="display: none;">
						</td>
					</tr>
				</tbody>
			</table>

			<!-- Botones para modificar o eliminar cuenta -->
			<div class="botones-modificar-eliminar">
				<button class="botonModificar" id="btnModificar" name="btnModificar"
					type="button">Modificar</button>
				<button class="botonEliminar" id="btnEliminar" name="btnEliminar"
					type="button">Eliminar</button>
				<input type="submit" value="Guardar" name="btnGuardar"
					style="display: none;">
				<!-- Botón Guardar oculto -->
				<input type="submit" value="Cancelar" id="btnCancelar"
					name="btnCancelar" style="display: none;">
				<!-- Botón Cancelar oculto -->
				<input type="submit" value="Eliminar" id="btnEliminarSubmit"
					name="btnEliminarSubmit" style="display: none;">
				<!-- Botón Guardar oculto -->

			</div>
		</form>
		<%
            }
        %>
	</div>
	<script defer src="js/clientes.js"></script>
</body>
</html>