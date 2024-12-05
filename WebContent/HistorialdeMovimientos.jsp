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

<!--  estilo tabla -->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script type="text/javascript">
	$(document).ready( function( ){
		$('#table_id').DataTable();
	});
</script>


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
		<form action="SIHistorial" method="POST" id="formGrilla" name="formGrilla">
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
				<div class="button-container">
				<input type="submit" value="buscarMovs"
					name="buscarMovs" />
			</div>
		</form>
		
	</div>

	<!-- Contenedor del historial de movimientos -->

	<div class="history-container" id="movement-history"
		style="display: block">
		<div class="formulario-mensaje"
			style="<%= request.getAttribute("titulomovs") != null && !request.getAttribute("titulomovs").toString().isEmpty() ? "display: block;" : "display: none;" %>">
				<h3 style="color: black;">Historial de movimientos del CBU <%= request.getAttribute("titulomovs") %> :</h3>
		</div>
		
		<table id="table_id" class="display">
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
					<td><%= String.format("%.2f", mov.getImporte()) %></td>
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
