<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="Inicio.jsp"%>

<!-- Incluimos el inicio -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<link rel="stylesheet" href="/css/EstiloTrasferir.css" />
<link rel="stylesheet" href="/css/normalize.css" />
<link rel="icon" type="image/x-icon" href="/images/favicon.ico">
<title>Transferencia entre cuentas</title>
</head>
<body>
	<div class="transfer-container">
		<h2>Transferencias</h2>

		<!-- Seleccion de la cuenta -->
		<label for="cuenta">Selecciona una cuenta</label> <select id="cuenta"
			name="cuenta">
			<option value="caja_ahorro">Caja de Ahorro - CBU:
				1234567890123456789012</option>
			<option value="cuenta_corriente">Cuenta Corriente - CBU:
				2345678901234567890123</option>
			<option value="caja_ahorro2">Caja de Ahorro - CBU:
				3456789012345678901234</option>
		</select>

		<!-- Mostrar saldo actual -->
		<div class="balance-display" id="saldo">
			Saldo: $0.00
			<!-- Este valor cambiara dependiendo de la cuenta seleccionada -->
		</div>

		<!-- CBU del destinatario -->
		<label for="cbu_destinatario">CBU del destinatario</label> <input
			type="text" id="cbu_destinatario" name="cbu_destinatario"
			placeholder="Ingrese el CBU del destinatario" />

		<!-- Cantidad a transferir -->
		<label for="monto">Monto a transferir</label> <input type="number"
			id="monto" name="monto" placeholder="Ingrese el monto a transferir" />

		<!-- Boton de transferencia -->
		<button type="button">Realizar Transferencia</button>
	</div>
	<script defer src="./js/mostrarSaldo"></script>
</body>
</html>
