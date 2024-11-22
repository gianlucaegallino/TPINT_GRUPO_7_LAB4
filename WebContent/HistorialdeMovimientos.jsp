<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="Inicio.jsp" %>

<!-- HTML -->

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <!-- Normalize.css -->
    <link rel="stylesheet" href="normalize.css" />
    <!-- Estilo Local -->
    <link rel="stylesheet" href="css/estiloHistorial.css" />
    <link rel="icon" type="image/x-icon" href="/images/favicon.ico">
    <title>Historial de Movimientos</title>
  </head>

  <body>
    <div class="history-container" id="account-list">
      <h1>Historial de Movimientos</h1>
      <label for="cuenta">Selecciona una cuenta:</label>

      <select id="account-select">
        <option value="CBU123456789012">
          Caja de Ahorro - CBU: 1234567890123456789012 - Saldo: $1000.00
        </option>
        <option value="CBU234567890123">
          Cuenta Corriente - CBU: 2345678901234567890123 - Saldo: $2500.00
        </option>
        <option value="CBU345678901234">
          Caja de Ahorro - CBU: 3456789012345678901234 - Saldo: $1500.00
        </option>
      </select>
    </div>

    <!-- Contenedor del historial de movimientos -->
    <div class="history-container" id="movement-history" style="display: none">
      <table>
        <thead>
          <tr>
            <th>Fecha</th>
            <th>Descripcion</th>
            <th>Monto</th>
          </tr>
        </thead>
        <tbody id="movements-table-body">
          <!-- Movimientos se agregaran aqui dinamicamente -->
        </tbody>
      </table>
    </div>

    <script defer src="./js/showMovements.js"> </script>
  </body>
</html>
