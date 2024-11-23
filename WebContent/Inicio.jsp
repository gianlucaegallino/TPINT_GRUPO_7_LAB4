<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!-- HTML -->

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap"
      rel="stylesheet"
    />

    <!-- Normalize.css -->
    <link rel="stylesheet" href="normalize.css" />
    <!-- Estilo Local -->
    <link rel="stylesheet" href="./css/estiloInicioUs.css" />
    <link rel="icon" type="image/x-icon" href="images/favicon.ico">
    <title>Inicio | Banco Liberacion</title>
  </head>
  
  <%

  Cookie[] cookies = null;
  
  cookies = request.getCookies();
  
  String name = null;
  String surname = null;
  
  //Asigna valores a las cookies
  
  if( cookies != null ) {
	  for (int i = 0; i < cookies.length; i++) {
	  	if (cookies[i].getName().equals("NombrePersona")){
	  		name = cookies[i].getValue();
	  	}
	  	if (cookies[i].getName().equals("ApellidoPersona")){
	  		surname = cookies[i].getValue();
	  	}
	  }
  }
   
  
  %>
  
  <body class="bodyInicio">
    <header class="headerInicio">
      <div class="logo">
        <a href="Inicio.jsp">
          <img src="images/logo.png" alt="Logo" class="icon" />
        </a>
      </div>
        <div class="namecard">
            <img src="images/fotoUsuario.jpg" class="pfp">
            <p class="line1"><%= name + " " + surname %></p>
            <p class="line2"><a href="Login.jsp" id="cierre">Cerrar Sesion</a></p>
        </div>
      
    </header>
    <article class="menus">

        <input
          class="botonInicio"
          id="botonHistorial"
          type="submit"
          value="Historial de Movimientos"
          onClick="window.location.href='HistorialdeMovimientos.jsp'"
        />


        <input class="botonInicio" type="submit" value="Transferir" />


        <input
          class="botonInicio"
          id="botonPedirPrestamo"
          type="submit"
          value="Pedir Prestamo"
          onClick="window.location.href='PedirPrestamo.jsp'"
        />


        <input
          class="botonInicio"
          id="botonPagarPrestamo"
          type="submit"
          value="Pago de Prestamo"
          onClick="window.location.href='PagarPrestamo.jsp'"
        />


        <input class="botonInicio" type="submit" value="Informacion Personal" />

    </article>
    <hr />
    <script defer src="./js/goToPage.js"></script>
  </body>
</html>
