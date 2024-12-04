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

		<%
			if (request.getAttribute("mensajeCarga") != "Cargadas") {
		%>

		<script type="text/javascript">
			document.getElementById('formCargarListas').submit();
		</script>

		<%
			}
		%>

		<h2>Transferencias</h2>
		<form action="SITransferir" method="POST">
			<!-- Seleccion de la cuenta -->
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

			<!-- Mostrar saldo actual -->
			<label for="saldo">Saldo:</label> <input class="balance-display"
				id="saldo" name="saldo" readonly>
			<script defer>
					let selectCuenta = document.querySelector("#cuenta");
					let contadorSaldo = document.querySelector("#saldo");

					let saldo = selectCuenta.selectedOptions[0].getAttribute("data-saldo");

					if (saldo != "") {
						contadorSaldo.value = Number(saldo).toFixed(2);

					} else {
						contadorSaldo.value = null;
					}

					selectCuenta.addEventListener("change", function() {
						let saldo = selectCuenta.selectedOptions[0].getAttribute("data-saldo");
						
						if (saldo != "") {
							contadorSaldo.value =  Number(saldo).toFixed(2);
						} else {
							contadorSaldo.value = null;
							
						}
					});
				</script>

			<!-- CBU del destinatario -->
			<label for="cbu_destinatario">CBU del destinatario</label> <input
				type="text" id="cbu_destinatario" name="cbu_destinatario"
				placeholder="Ingrese el CBU del destinatario" maxlength="22" minlength="22" required pattern= "^[0-9]*$"/>

			<!-- Cantidad a transferir -->
			<label for="monto">Monto a transferir</label> <input type="text"
				id="monto" name="monto" placeholder="Ingrese el monto a transferir" required pattern="^[1-9][0-9]*(\.[0-9]{1,2})?$"/>

			<!-- Boton de transferencia -->
			<button type="submit" id="btnRealizarTransferencia">Realizar
				Transferencia</button>
			<input type="hidden" name="action" value="realizarTransferencia">
		</form>
	</div>

</body>
</html>
