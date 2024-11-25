<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
  <%

  Cookie[] cookies = null;
  
  cookies = request.getCookies();
  
  String name = null;
  String surname = null;
  
  //Agarra los valores de las cookies

	  if(cookies != null && cookies.length > 1) { // si hay mas cookies que la JSESSIONID, que es seteada automaticamente
		  for (int i = 0; i < cookies.length; i++) {
		  	if (cookies[i].getName().equals("NombrePersona")){
		  		name = cookies[i].getValue();
		  	}
		  	if (cookies[i].getName().equals("ApellidoPersona")){
		  		surname = cookies[i].getValue();
		  	}
		  }
	  } else {
		  //Agarra los valores de la request, como un backup (las cookies tardan en cargar)
		  name = (String) request.getAttribute("NombrePersona");
		  surname = (String) request.getAttribute("ApellidoPersona");
	  }
   
  
  %>
  
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
    <link rel="stylesheet" href="css/estiloInicioBanco.css" />
    <link rel="icon" type="image/x-icon" href="/images/favicon.ico">
    <title>Inicio | Banco Liberacion</title>
  </head>
  

  
  <body class="bodyInicio">
    <header class="headerInicio">
      <div class="logo">
        <a href="InicioUsuarioBanco.jsp">
          <img src="images/logo.png" alt="Logo" class="icon" />
        </a>
      </div>
      <!--
      <nav class="IniciarSesion">
        <a href="Login.jsp">Iniciar Sesion</a>
      </nav>  -->
        <div class="namecard">
            <img src="images/fotoUsuario.jpg" class="pfp">
            <p class="line1"><%= name + " " + surname %></p>
            <p class="line2"><a href="Login.jsp" id="cierre">Cerrar Sesion</a></p>
        </div>
    </header>
    <article class="menus">

        <input
          class="botonInicio"
          id="botonAutorizar"
          type="submit"
          value="Autorizar Prestamos"
          onClick="window.location.href='AutorizarPrestamos.jsp'"
        />


        <input class="botonInicio" type="submit" value="Informacion Cliente" />

		<form action="SIClientes" method="GET">
        <input
          class="botonInicio"
          id="botonABMLCliente"
          type="submit"
          value="Cliente"
          onClick="window.location.href='Clientes.jsp'"
        />
		 </form>

        <input
          class="botonInicio"
          id="botonABMLCuenta"
          type="submit"
          value="Cuentas"
          onClick="window.location.href='Cuentas.jsp'"
        />


        <input class="botonInicio" type="submit" value="Informes Estadísticos" />

    </article>
    <hr />
    <script defer src="./js/goToPage.js"></script>
  </body>
</html>
