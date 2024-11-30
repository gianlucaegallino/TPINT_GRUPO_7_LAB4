<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="InicioUsuarioBanco.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/EstiloReportes.css">
<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
<title>Formulario de Filtro</title>
</head>
<body>
	<h2>Reportes de Cuentas y Préstamos</h2>

	<!-- Formulario para el filtro de fechas de alta y tipo de cuenta -->
	<form action="#" method="post">
		<fieldset>
			<legend>Reportes de Cuentas</legend>
			<label for="fechaAltaDesde">Fecha de Alta Desde:</label> <input
				type="date" id="fechaAltaDesde" name="fechaAltaDesde" required><br>
			<br> <label for="fechaAltaHasta">Fecha de Alta Hasta:</label> <input
				type="date" id="fechaAltaHasta" name="fechaAltaHasta" required><br>
			<br> <label for="tipoCuenta">Tipo de Cuenta:</label> <select
				id="tipoCuenta" name="tipoCuenta" required>
				<option value="corriente">Cuenta Corriente</option>
				<option value="ahorro">Caja de Ahorro</option>
			</select> <br>

			<button type="submit">Aplicar Filtro</button>
		</fieldset>

		<br>


		<!-- Formulario para el filtro de fechas y rango de préstamos aceptados -->
		<fieldset>
			<legend>Reportes de Préstamos</legend>
			<label for="fechaPrestamoDesde">Fecha de Préstamo Desde:</label> <input
				type="date" id="fechaPrestamoDesde" name="fechaPrestamoDesde"
				required><br> <br> <label for="fechaPrestamoHasta">Fecha
				de Préstamo Hasta:</label> <input type="date" id="fechaPrestamoHasta"
				name="fechaPrestamoHasta" required><br> <br> <label
				for="montoMinimo">Monto Mayor a:</label> <input type="number"
				id="montoMinimo" name="montoMinimo" min="0" step="0.01" required><br>
			<br> <label for="montoMaximo">Monto Menor a:</label> <input
				type="number" id="montoMaximo" name="montoMaximo" min="0"
				step="0.01" required> <br>

			<button type="submit">Aplicar Filtro</button>
		</fieldset>


	</form>
</body>
</html>
