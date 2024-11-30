<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Prestamo"%>
<%@ page import="entidades.Cliente"%>
<%@ include file="InicioUsuarioBanco.jsp"%>
<%
    List<Prestamo> prestamosPendientes = (List<Prestamo>) request.getAttribute("prestamosPendientes");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autorizar Préstamos | Banco Liberación</title>
<link rel="stylesheet" href="css/estiloAutorizarPrestamos.css">
<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
</head>
<body class="bodyAutorizar">
	<div class="containerAutorizar">
		<h2>Autorizar Solicitudes de Préstamos</h2>

		<% if (prestamosPendientes != null && !prestamosPendientes.isEmpty()) { %>
		<table class="tablaPrestamos">
			<thead>
				<tr>
					<th>Número de Préstamo</th>
					<th>DNI del Cliente</th>
					<th>Cliente</th>
					<th>Monto Solicitado</th>
					<th>Cuotas</th>
					<th>Interés Anual (%)</th>
					<th>Valor de Cada Cuota</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<% for (Prestamo prestamo : prestamosPendientes) { 
                    	Cliente cliente = prestamo.getCliente();
                    %>
				<tr>
					<td><%= prestamo.getId() %></td>
					<td><%= cliente.getDni() %></td>
					<!-- Mostrar DNI del cliente -->
					<td><%= cliente.getApellido() %></td>
					<!-- Mostrar Apellido del cliente -->
					<td>$<%= String.format("%.2f", prestamo.getImportePedido()) %></td>
					<td><%= prestamo.getPlazoMeses() %> meses</td>
					<td><%= String.format("%.2f", prestamo.getInteresAnual()) %></td>
					<td>$<%= String.format("%.2f", prestamo.getImportePedido() / prestamo.getPlazoMeses()) %></td>
					<td>
						<form action="SlAutorizarPrestamo" method="post"
							style="display: inline;">
							<input type="hidden" name="numeroPrestamo"
								value="<%= prestamo.getId() %>"> <input type="hidden"
								name="accion" value="aprobar">
							<button type="submit" class="aprobarBtn">Aprobar</button>
						</form>
						<form action="SlAutorizarPrestamo" method="post"
							style="display: inline;">
							<input type="hidden" name="numeroPrestamo"
								value="<%= prestamo.getId() %>"> <input type="hidden"
								name="accion" value="rechazar">
							<button type="submit" class="rechazarBtn">Rechazar</button>
						</form>
					</td>
				</tr>
				<% } %>
			</tbody>
		</table>
		<% } else { %>
		<p>No hay préstamos pendientes en este momento.</p>
		<% } %>

	</div>
</body>
</html>
