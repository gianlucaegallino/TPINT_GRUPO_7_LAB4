<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ include file="Inicio.jsp"%>
<%


    // Variables para el calculo
    double monto = request.getParameter("monto") != null ? Double.parseDouble(request.getParameter("monto")) : 0;
    int cuotas = request.getParameter("cuotas") != null ? Integer.parseInt(request.getParameter("cuotas")) : 0;
    double tasaInteres = 0.50; // Tasa de interes anual, constante

    // Calculo del interes total en porcentaje
    double interesTotal = (tasaInteres * (cuotas / 12.0)) * 100; // Interes total acumulado en porcentaje

    double totalConInteres = monto * (1 + (interesTotal / 100)); // Calculo del monto total a pagar
    double cuotaMensual = (cuotas > 0) ? totalConInteres / cuotas : 0; // Evitar division por cero
%>

<!-- MAIN -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap"
	rel="stylesheet">


<link rel="stylesheet" href="./css/estiloPrestamo.css">
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Pedir Prestamo | Banco Liberacion</title>
</head>
<body class="bodyPrestamo">
	<div class="containerPrestamo">
	
			
		
		<h2>Pedir Prestamo</h2>


		<form action="PedirPrestamo.jsp" method="post">
			<label for="monto">Monto:</label> 
			<input type="number" id="monto" name="monto" required> 
			<label for="cuotas">Cantidad de Cuotas:</label> 
			<input type="number" id="cuotas" name="cuotas" required>
			<label for="cuenta">Cuenta de Deposito:</label> 
			<select id="cuenta" name="cuenta" required>
				<option value="caja_ahorro">Caja de Ahorro</option>
				<option value="cuenta_corriente">Cuenta Corriente</option>
			</select>

			<button type="submit">Calcular Cuota</button>
		</form>

		<h3>Intereses para $<%= monto %> en <%= cuotas %> cuotas:</h3>
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
