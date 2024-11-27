<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Cliente" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="Clientes.jsp" %>
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
	<div class="formulario-listar-cliente" id="formListarCliente" style="display: block;">
		<h2>Listar Cliente</h2>
		<form method="post" action="SIClientes">
			<input type="hidden" name="action" value="mostrarClientes">
			<input type="text" id="numDNICliente" name="numDNIBuscar" placeholder="Ingrese un DNI">
			<input type="submit" id="filtrarClientes"value="Filtrar" name="btnFiltrar">
			<input type="submit" id="mostrarTodosClientes" value="Mostrar todos los clientes" name="btnMostrarCliente">
		</form>
		
		<% 
			ArrayList<Cliente> listaClientes = null;
			if(request.getAttribute("listaC")!=null)
				{	
					listaClientes = (ArrayList<Cliente>) request.getAttribute("listaC");
					
				}
		%>
		<table class="tabla-clientes" id="tableCliente" style="display: block;">
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
				<% if(listaClientes != null)
				for(Cliente cli : listaClientes)
					{
				%>
				<tr>
					<td><%=cli.getDni()%></td>
					<td><%=cli.getCuil() %></td>
					<td><%=cli.getNombre() %></td>
					<td><%=cli.getApellido() %></td>
					<td><%=cli.getSexo().getDescripcion() %></td>
					<td><%=cli.getNacionalidad().getNombre() %></td>
					<td><%=cli.getFecha_nacimiento() %></td>
					<td><%=cli.getDireccion().getDireccion() %></td>
					<td><%=cli.getCorreo_electronico() %></td>
					<td><%=cli.getTelefono() %></td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<script defer src="js/clientes.js"></script>
</body>
</html>