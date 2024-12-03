<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="Inicio.jsp"%>
<%@ page import="entidades.Cuenta"%>
<%@ page import="entidades.Movimiento"%>
<%@ page import="java.util.ArrayList"%>

<!-- HTML -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<link rel="stylesheet" href="./css/estiloHistorial.css" />
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Historial de Movimientos</title>
</head>

<body>
	<div class="history-container" id="account-list">

		<form action="SIHistorial" method="GET" style="display: none"
			id="formCargarListas"></form>

		<%
			if (request.getAttribute("mensajeCarga") != "Cargadas") {
		%>

		<script type="text/javascript">
			document.getElementById('formCargarListas').submit();
		</script>

		<%
			}
		%>

		<h1>Historial de Movimientos</h1>
		<label for="cuenta">Selecciona una cuenta</label> <select id="cuenta"
			name="cuenta" required>
			<%
				ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");

				if (cuentas != null) {
					for (Cuenta cuenta : cuentas) {

						String tipo = (cuenta.getCuenta().getId() == 1) ? "Caja de Ahorro" : "Cuenta Corriente";
			%>

			<option value="<%=cuenta.getCbu()%>"
				data-saldo="<%=cuenta.getSaldo()%>"><%=tipo + " - CBU: " + cuenta.getCbu()%></option>

			<%
				}
				} else {
			%>

			<option value="" disabled selected>No hay cuentas
				disponibles</option>
			<%
				}
			%>
		</select>
	</div>

	<!-- Contenedor del historial de movimientos -->

	<div class="history-container" id="movement-history"
		style="display: block">
		<table>
			<thead>
				<tr>
					<th>Fecha</th>
					<th>Detalle</th>
					<th>Importe</th>
					<th>Tipo de Movimiento</th>
				</tr>
			</thead>
			<tbody id="movements-table-body">
				<%
					ArrayList<Movimiento> movs = (ArrayList<Movimiento>) request.getAttribute("movimientos");
					if (movs != null) {
						for (Movimiento mov : movs) {
				%>
				<tr>
					<td><%=mov.getFecha()%></td>
					<td><%=mov.getDetalle()%></td>
					<td><%=mov.getImporte()%></td>
					<td><%=mov.getTipo_movimiento()%></td>
				</tr>
				<%
					}
					} else {
				%>
				<tr>
					<td>-</td>
					<td>-</td>
					<td>-</td>
					<td>-</td>
				</tr>
				<%
					}
				%>


			</tbody>
		</table>
	</div>

</body>
</html>
