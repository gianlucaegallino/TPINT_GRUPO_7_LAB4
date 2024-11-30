<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>

<%
    String usuario = (String) session.getAttribute("usuarioLogeado");
    if (usuario == null) {
        usuario = "Invitado";
    }

    // Variables de ejemplo para cuotas y estado de pago
    int totalCuotas = 12;
    double cuotaMensual = 500.00; // Monto de ejemplo por cuota
    Map<Integer, String> cuotasPagadas = new HashMap<>(); // Almacena cuotas pagadas con fecha
    cuotasPagadas.put(1, "2024-01-15");
    cuotasPagadas.put(3, "2024-03-15");
    cuotasPagadas.put(6, "2024-06-15");

    // Generar listado de cuotas
    ArrayList<Integer> cuotasPendientes = new ArrayList<>();
    for (int i = 1; i <= totalCuotas; i++) {
        if (!cuotasPagadas.containsKey(i)) {
            cuotasPendientes.add(i);
        }
    }
%>
<%@ include file="Inicio.jsp"%>
<!-- Incluimos el inicio -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap"
	rel="stylesheet" />
<!-- Normalize.css -->
<link rel="stylesheet" href="normalize.css" />
<link rel="stylesheet" href="css/estiloPrestamo.css" />
<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
<title>Pagar Prestamo | Banco Liberacion</title>
</head>
<body class="bodyPrestamo">
	<div class="containerPrestamo">
		<p>
			Usuario: <strong><%= usuario %></strong>
		</p>

		<h3>Cuotas a Pagar</h3>
		<form action="PagarPrestamo.jsp" method="post">
			<label for="cuotaSeleccionada">Seleccionar Cuota:</label> <select
				id="cuotaSeleccionada" name="cuotaSeleccionada" required>
				<% for (Integer cuota : cuotasPendientes) { %>
				<option value="<%= cuota %>">Cuota
					<%= cuota %> - $<%= new
                DecimalFormat("#.##").format(cuotaMensual) %>
				</option>
				<% } %>
			</select> <label for="cuenta">Cuenta para Descuento:</label> <select
				id="cuenta" name="cuenta" required>
				<option value="caja_ahorro">Caja de Ahorro</option>
				<option value="cuenta_corriente">Cuenta Corriente</option>
			</select>

			<button type="submit">Pagar Cuota</button>
		</form>

		<h3>Historial de Cuotas</h3>
		<table class="historialCuotas" border="1">
			<thead>
				<tr>
					<th>Cuota</th>
					<th>Estado</th>
					<th>Fecha de Pago</th>
				</tr>
			</thead>
			<tbody>
				<% for (int i = 1; i <= totalCuotas; i++) { %>
				<tr>
					<td>Cuota <%= i %></td>
					<td><%= cuotasPagadas.containsKey(i) ? "Pagada" : "Pendiente" %>
					</td>
					<td><%= cuotasPagadas.getOrDefault(i, "N/A") %></td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
</body>
</html>
