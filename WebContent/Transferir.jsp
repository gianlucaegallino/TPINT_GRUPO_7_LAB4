<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="Inicio.jsp"%>
<%@ page import="entidades.Cuenta"%>
<%@ page import="java.util.ArrayList"%>

<!-- Incluimos el inicio -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<link rel="stylesheet" href="./css/EstiloTrasferir.css" />
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Transferencia entre cuentas</title>
</head>
<body>
	<div class="transfer-container">
		<form action="SITransferir" method="GET" style="display: none"
			id="formCargarListas"></form>

		<% if (request.getAttribute("mensajeCarga")!="Cargadas"){ %>

		<script type="text/javascript">
	            document.getElementById('formCargarListas').submit();
	          	</script>

		<% } %>

		<h2>Transferencias</h2>

		<!-- Seleccion de la cuenta -->
		<label for="cuenta">Selecciona una cuenta</label> <select id="cuenta"
			name="cuenta" required>
			<% 
			
              ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");
			
              if (cuentas != null) {
              	for (Cuenta cuenta : cuentas) { %>

			<option value="<%= cuenta.getSaldo() %>"
				data-cbu="<%= cuenta.getCbu() %>"><%= cuenta.toString() %></option>

			<% }
              } else { %>

			<option value="" disabled selected>No hay cuentas
				disponibles</option>
			<% } %>
		</select>

		<!-- Mostrar saldo actual -->
		<div class="balance-display" id="saldo">
			<script defer>

			let selectCuenta = document.querySelector("#cuenta");
			let contadorSaldo = document.querySelector("#saldo");		

			if (selectCuenta.value != "") {
				contadorSaldo.textContent = "Saldo: " + selectCuenta.value;
			} else {
				contadorSaldo.textCsontent = "Saldo: $0.00";
			}
			
			selectCuenta.addEventListener("change", function() {
				if (selectCuenta.value != ""){
					contadorSaldo.textContent = "Saldo: " + selectCuenta.value;
				} else {
					contadorSaldo.textContent = "Saldo: $0.00";
				}
			});
		</script>
		</div>

		<!-- CBU del destinatario -->
		<label for="cbu_destinatario">CBU del destinatario</label> <input
			type="text" id="cbu_destinatario" name="cbu_destinatario"
			placeholder="Ingrese el CBU del destinatario" />

		<!-- Cantidad a transferir -->
		<label for="monto">Monto a transferir</label> <input type="number"
			id="monto" name="monto" placeholder="Ingrese el monto a transferir" />

		<!-- Boton de transferencia -->
		<button type="button" id="btnRealizarTransferencia">Realizar
			Transferencia</button>
	</div>

</body>
</html>
