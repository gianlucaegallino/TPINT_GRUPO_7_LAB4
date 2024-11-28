<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="Clientes.jsp" %>
<%@ page import="entidades.Sexo" %>
<%@ page import="entidades.Nacionalidad" %>
<%@ page import="entidades.Provincia" %>
<%@ page import="entidades.Localidad" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <link rel="StyleSheet" href="./css/EstiloClientes.css" />
    <link rel="stylesheet" href="./css/normalize.css" />
    <link rel="icon" type="image/x-icon" href="/images/favicon.ico" />
    <title>Gestión de Clientes | Banco Liberación</title>
  </head>
  <body class="bodyInicio">
    <!-- Form de agregar cliente -->
    <form
      action="SIClientes"
      method="GET"
      style="display: none"
      id="formCargarListas"
    ></form>

    <%
      if (request.getAttribute("mensajeCarga")!="Cargadas"){
          %>

          <script type="text/javascript">
          document.getElementById('formCargarListas').submit();
          </script>

          <% 
      }
     %>

    <div
      class="formulario-agregar"
      id="formularioAgregarCliente"
      style="display: block"
    >
      <h2>Agregar Cliente</h2>
      <form action="SIClientes" method="POST">
        <input type="hidden" name="action" value="agregarCliente" />
        <div class="form-container">
          <div class="fila" id="DNI_CUIL">
            <div class="form-element">
              <label for="DniCliente">DNI:</label>
              <input type="text" id="DniCliente" name="DniCliente"
              placeholder="Ingrese un DNI" pattern="[0-9]{7,8}" maxlength="8"
              title="El DNI debe tener entre 7 y 8 dígitos."
              value="<%= request.getAttribute("DniCliente") != null ? request.getAttribute("DniCliente") : "" %>"  required>
            </div>
            <div class="form-element">
              <label for="CUILCliente">CUIL:</label>
              <input type="text" id="CUILCliente" name="CUILCliente"
              placeholder="Ingrese un CUIL" pattern= "(20|23|24|27)[0-9]{9}"
              maxlength="11" title="El CUIL debe tener 11 dígitos y comenzar con 20, 23, 24 o 27 y sin ' - '."
              value="<%= request.getAttribute("CUILCliente") != null ? request.getAttribute("CUILCliente") : "" %>"  required>
            </div>
          </div>
          <div class="fila" id="Nombre_Apellido">
            <div class="form-element">
              <label for="nombreCliente">NOMBRE:</label>
              <input type="text" id="nombreCliente" name="nombreCliente"
              placeholder="Ingrese un nombre"
              value="<%= request.getAttribute("nombreCliente") != null ? request.getAttribute("nombreCliente") : "" %>"  required>
            </div>
            <div class="form-element">
              <label for="apellidoCliente">APELLIDO:</label>
              <input type="text" id="apellidoCliente" name="apellidoCliente" placeholder="Ingrese un apellido" 
              value="<%= request.getAttribute("apellidoCliente") != null ? request.getAttribute("apellidoCliente") : "" %>"  required>
            </div>
            <div class="form-element">
              <label for="telefonoCliente">TELEFONO:</label>
              <input type="text" id="telefonoCliente" name="telefonoCliente" placeholder="Ingrese su telefono" 
              pattern="[0-9]{8,10}" 
              maxlength="10" 
              title="El teléfono debe tener entre 8 y 10 dígitos."
              value="<%= request.getAttribute("telefonoCliente") != null ? request.getAttribute("telefonoCliente") : "" %>" required>
            </div>
          </div>
          <div class="fila">
            <div class="form-element">
              <label for="SexoCliente">GENERO:</label>
              <select id="SexoCliente" name="SexoCliente" required>
                <option value="" disabled selected>Seleccione el sexo</option>
                  <% 
                    // Obtener el valor de generoCliente como String
                    String generoCliente = (String) request.getAttribute("generoCliente");
                        
                    // Convertir generoCliente a int (si es posible)
                    int generoClienteId = (generoCliente != null && !generoCliente.isEmpty()) ? Integer.parseInt(generoCliente) : -1;
                        
                    ArrayList<Sexo> sexos = (ArrayList<Sexo>) request.getAttribute("sexos");
                    if (sexos != null) {
                      for (Sexo sexo : sexos) {
                        %>

                   <option value="<%= sexo.getId() %>" <%= (sexo.getId() == generoClienteId) ? "selected" : "" %>><%= sexo.getDescripcion() %></option>
                       
                        <% 
                      }
                    } else { 
                      %>
                        <option value="" disabled>No hay datos disponibles</option>

                  <% } 
                %>
                </select>
            </div>
            <div class="form-element">
              <label for="NacioCliente">NACIONALIDAD:</label>

							<% 
							    // Obtener el valor de nacCliente como String
							    String nacClienteStr = (String) request.getAttribute("nacCliente");
							    
							    // Convertir nacCliente a int (si es posible)
							    int nacCliente = (nacClienteStr != null && !nacClienteStr.isEmpty()) ? Integer.parseInt(nacClienteStr) : -1;
							%>

              <select id="NacioCliente" name="NacioCliente" required>
                <option value="" disabled selected>
                  Seleccione la nacionalidad
                </option>

                <% 
                ArrayList<Nacionalidad> nacionalidades = (ArrayList<Nacionalidad>) request.getAttribute("nacionalidad");
                if (nacionalidades != null) {
                    for (Nacionalidad nacionalidad : nacionalidades) {
                      %>
                      <option value="<%= nacionalidad.getId() %>"<%=(nacionalidad.getId()== nacCliente)?" selected":"" %>><%= nacionalidad.getNombre() %></option>
                      <% 
							      }
							  } else { 
							    %>
							  <option value="" disabled>No hay datos disponibles</option>
							    <% } 
                %>
							
              </select>
            </div>
            <div class="form-element">
              <label for="FNacimientoCliente">FECHA DE NACIMIENTO:</label>
              <input type="date" id="FNacimientoCliente" name="FNacimientoCliente" 
              value="<%= request.getAttribute("FNacimientoCliente") != null ? request.getAttribute("FNacimientoCliente") : "" %>"
              max="<%= java.time.LocalDate.now().minusYears(18) %>" 
              title="Debe tener al menos 18 años."
              required>
            </div>
          </div>
          <div class="fila">
            <div class="form-element">
              <label for="ProvCliente">PROVINCIA:</label>
              <select id="ProvCliente" name="ProvCliente" onchange="cargarLocalidades(this.value)" required>
                <option value=""disabled selected> Seleccione la Provincia</option>

                  <% 
                    // Obtener el valor de provCliente como String
                    String Prov = (String) request.getAttribute("prov");
                    System.out.println("prov: ");
                    System.out.println(Prov);

                    // Convertir provCliente a int (si es posible)
                    int provCliente = (Prov != null && !Prov.isEmpty()) ? Integer.parseInt(Prov) : -1;
                          
                    // Obtener las provincias desde el request
                    ArrayList<Provincia> provincias = (ArrayList<Provincia>) request.getAttribute("Provincia");

                    if (provincias != null && !provincias.isEmpty()) {
                      // Recorrer las provincias y agregarlas al select
                      for (Provincia provincia : provincias) { %>
                        <option value="<%= provincia.getId() %>" <%= (provincia.getId() == provCliente) ? "selected" : "" %> >
                        <%= provincia.getNombre() %>
                        </option>
                      <% 
                      }
                    } else { %>
                      <option value="" disabled>No hay provincias disponibles</option>
                    <% } 
                  %>
              </select>
            </div>
            <div class="form-element">
              <!-- Select de Localidades -->
              <label for="LocCliente">LOCALIDAD:</label>
              <select id="LocCliente" name="LocCliente" required>
                <option value="">Seleccione una Localidad</option>
                <% 
                  // Obtener las localidades desde el request
                  ArrayList<Localidad> localidades = (ArrayList<Localidad>) request.getAttribute("localidades");
                  if (localidades != null && !localidades.isEmpty()) {
                    for (Localidad localidad : localidades) { %>
                        <option value="<%= localidad.getId() %>" ><%= localidad.getNombre() %> (<%= localidad.getProvincia().getNombre() %>)</option>
                    <% 
                    }
                  } else { %>
                        <option value="" disabled>No hay localidades disponibles</option>
                    <% 
                  }
                %>
              </select>
            </div>
            <div class="form-element">
              <label for="DirecCliente">DIRECCION:</label>
              <input type="text" id="DirecCliente" name="DirecCliente" placeholder="Ingrese una Direccion" 
              value="<%= request.getAttribute("DirecCliente") != null ? request.getAttribute("DirecCliente") : "" %>" required>
            </div>
          </div>
          <div class="fila">
            <div class="form-element">
              <label for="CorreoCliente">CORREO:</label>
              <input type="email" id="CorreoCliente" name="CorreoCliente" placeholder="Ingrese un Correo Electronico" 
              value="<%= request.getAttribute("CorreoCliente") != null ? request.getAttribute("CorreoCliente") : "" %>" required>
            </div>
          </div>
        </div>
        <div class="button-container">
          <input
            type="submit"
            value="Guardar Cliente"
            name="btnAgregarCliente"
          />
        </div>
      </form>

      <div class="formulario-mensaje" style="<%= request.getAttribute("mensajeExito") != null && !request.getAttribute("mensajeExito").toString().isEmpty() ? "display: block;" : "display: none;" %>">
        <form>
          <h3 style="color: green;"><%= request.getAttribute("mensajeExito") %></h3> 
        </form>
      </div>

      <div class="formulario-mensaje" style="<%= request.getAttribute("mensajeError") != null && !request.getAttribute("mensajeError").toString().isEmpty() ? "display: block;" : "display: none;" %>">
        <form>
          <h3 style="color: red;"><%= request.getAttribute("mensajeError") %></h3>
        </form>
      </div>

    </div>
    <script defer src="js/clientes.js"></script>
  </body>
</html>
