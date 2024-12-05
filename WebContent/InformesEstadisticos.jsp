<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="InicioUsuarioBanco.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/EstiloReportes.css">
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Formulario de Filtro</title>
</head>
<body class="bodyInicio">
    <div class="formulario-agregar" id="formularioInformesEstadisticos" style="display: block;">
    <h2>Informes Estadisticos</h2>
        <div class="form-container">
            <form action="SvInformesEstadisticos" method="POST">
                <input type="hidden" name="action" value="PorcentajeMovsAlta">
                <div class="fila">
                    <div class="form-element">
                        <label for="PorcentajeAlta">Porcentaje de movimientos que fueron dados altas de cuenta</label>
                    </div>
                    <div class="form-element">
                        <label for="FechaDesde" >Desde: </label>
                        <input type="date" id="fechadesdeInput" name="fechadesdeInput" required>
                    </div>
                    <div class="form-element">
                        <label for="FechaHasta">Hasta:</label>
                        <input type="date" id="fechahastaInput" name="fechahastaInput" required>
                    </div>
                    <div class="form-element">
                        <% String fechainicio = (String) request.getAttribute("fechainicio"); %>
                        <% String fechafinal = (String) request.getAttribute("fechafinal"); %>
                        <% Double porcentaje = (Double) request.getAttribute("porcentaje"); %>
                        <label for="MostrarPorcentajes"> 
                            <% if (fechainicio != null && fechafinal != null && porcentaje != null) { %>
                                Entre <span style="color: red;"><%= fechainicio %></span> y <span style="color: red;"><%= fechafinal %></span> hubo un <span style="color: green;"><%= String.format("%.2f", porcentaje) %> %</span> de movimientos de tipo: <span style="font-weight: bold;">Alta de Cuenta</span>  
                            <% } %>
                        </label>
                    </div>
                    <div class="form-element">
                        <input type="submit" value="Calcular Porcentaje">
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>	
</html>