<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat"%>
<%
    String usuario = (String) session.getAttribute("usuarioLogeado");
    if (usuario == null) {
        usuario = "Invitado";
    }

    // Variables para el calculo
    double monto = request.getParameter("monto") != null ? Double.parseDouble(request.getParameter("monto")) : 0;
    int cuotas = request.getParameter("cuotas") != null ? Integer.parseInt(request.getParameter("cuotas")) : 6; // Asignar valor por defecto
    double tasaInteres = 0.50; // Tasa de inter�s anual

    // Calculo del interes total en porcentaje
    double interesTotal = (tasaInteres * (cuotas / 12.0)) * 100; // Interes total acumulado en porcentaje

    double totalConInteres = monto * (1 + (interesTotal / 100)); // Calculo del monto total a pagar
    double cuotaMensual = (cuotas > 0) ? totalConInteres / cuotas : 0; // Evitar division por cero
%>
<%@ include file="Inicio.jsp"%>

<!-- MAIN -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap"
	rel="stylesheet">
<!-- Normalize.css -->

<link rel="stylesheet" href="./css/estiloPrestamo.css">
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Pedir Prestamo | Banco Liberacion</title>
</head>
<body class="bodyPrestamo">
	<div class="containerPrestamo">
		<h2>Pedir Prestamo</h2>
		<p>
			Usuario: <strong><%= usuario %></strong>
		</p>

		<form action="PedirPrestamo.jsp" method="post">
			<label for="monto">Monto:</label> <input type="number" id="monto"
				name="monto" required value="<%= monto > 0 ? monto : "" %>">

			<label for="cuotas">Cantidad de Cuotas:</label> <select id="cuotas"
				name="cuotas">
				<option value="6" <%= cuotas == 6 ? "selected" : "" %>>6
					meses</option>
				<option value="12" <%= cuotas == 12 ? "selected" : "" %>>12
					meses</option>
				<option value="24" <%= cuotas == 24 ? "selected" : "" %>>24
					meses</option>
			</select> <label for="cuenta">Cuenta de Dep�sito:</label> <select
				id="cuenta" name="cuenta" required>
				<option value="caja_ahorro">Caja de Ahorro</option>
				<option value="cuenta_corriente">Cuenta Corriente</option>
			</select>

			<button type="submit">Calcular Cuota</button>
		</form>

		<h3>Resultados del Calculo</h3>
		<p>
			Interes Anual:
			<%= (tasaInteres * 100) + "%" %></p>
		<p>
			Interes Total:
			<%= new DecimalFormat("#.##").format(interesTotal) + "%" %></p>
		<p>
			Cuota Mensual:
			<%= new DecimalFormat("#.##").format(cuotaMensual) %></p>

		<button type="submit">Solicitar Prestamo</button>
	</div>
</body>
</html>
