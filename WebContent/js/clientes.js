// Función para mostrar y ocultar formularios
function mostrarForm(formId) {
    // Ocultar todos los formularios
    document.getElementById('formListarCliente').style.display = 'none';
    document.getElementById('formularioAgregarCliente').style.display = 'none';
    document.getElementById('formularioMoECliente').style.display = 'none';
    
    // Mostrar el formulario correspondiente
    document.getElementById(formId).style.display = 'block';
}

document.addEventListener('DOMContentLoaded', function() {
    var btnModificar = document.getElementById('btnModificar');
    var btnGuardar = document.querySelector('input[name="btnGuardar"]');
    var btnEliminar = document.getElementById('btnEliminar'); 
    var btnCancelar = document.getElementById('btnCancelar'); 
    var btnEliminarSubmit = document.getElementById('btnEliminarSubmit'); 
    var camposEditables = document.querySelectorAll('.editable input');
    var btnBuscarDNI = document.querySelector('input[name="btnBuscarDNI"]'); // Obtén el botón Buscar DNI
    var tablaCliente1 = document.getElementById('tablaCliente1'); // Obtén la tabla 1
    var tablaCliente2 = document.getElementById('tablaCliente2'); // Obtén la tabla 2


    // Botón Modificar
    btnModificar.addEventListener('click', function() {
        camposEditables.forEach(function(input) {
            input.style.display = 'block';
            input.previousElementSibling.style.display = 'none';
        });
        btnGuardar.style.display = 'block';
        btnModificar.style.display = 'none';
        btnCancelar.style.display = 'block';
        btnEliminar.style.display = 'none';
    });

    // Botón Cancelar
    btnCancelar.addEventListener('click', function() {
        camposEditables.forEach(function(input) {
            input.style.display = 'none';
            input.previousElementSibling.style.display = 'block';
        });
        btnGuardar.style.display = 'none';
        btnModificar.style.display = 'block';
        btnCancelar.style.display = 'none';
        btnEliminar.style.display = 'block';
    });

    // Botón Eliminar
    btnEliminar.addEventListener('click', function() {
    	// Deshabilitar los campos editables
        camposEditables.forEach(function(input) {
            input.disabled = true; // Deshabilitar el campo de entrada
            input.style.backgroundColor = '#ddd'; // Cambiar el fondo a gris (opcional)
        });

        // Mostrar el botón "Eliminar" y ocultar los demás
        btnEliminarSubmit.style.display = 'block'; 
        btnModificar.style.display = 'none';
        btnEliminar.style.display = 'none';
        btnCancelar.style.display = 'block'; 
    });
    
    // Botón Buscar DNI
    btnBuscarDNI.addEventListener('click', function() {
        // Oculta la tabla 1
        tablaCliente1.style.display = 'none';
        // Muestra la tabla 2
        tablaCliente2.style.display = 'block';
    });
});

function cargarLocalidades(provId) {
	
	  var form = document.createElement('form');
	  form.method = 'GET';
	  form.action = 'SIClientes';

	  
	  //Generamos un form invisible, y cargamos los datos del form real para que se mantengan
	  
	  var inputDni = document.createElement('input');
	  inputDni.type = 'hidden';
	  inputDni.name = 'DniCliente';
	  inputDni.value = document.querySelector("#DniCliente").textContent;
	  form.appendChild(inputDni);
	  
	  var inputCuil = document.createElement('input');
	  inputCuil.type = 'hidden';
	  inputCuil.name = 'CUILCliente';
	  inputCuil.value = document.querySelector("#CUILCliente").textContent;
	  form.appendChild(inputCuil);
	  
	  var inputNombre = document.createElement('input');
	  inputNombre.type = 'hidden';
	  inputNombre.name = 'nombreCliente';
	  inputNombre.value = document.querySelector("#nombreCliente").textContent;
	  form.appendChild(inputNombre);
	  
	  var inputApe = document.createElement('input');
	  inputApe.type = 'hidden';
	  inputApe.name = 'apellidoCliente';
	  inputApe.value = document.querySelector("#apellidoCliente").textContent;
	  form.appendChild(inputApe);
	  
	  var inputTel = document.createElement('input');
	  inputTel.type = 'hidden';
	  inputTel.name = 'telefonoCliente';
	  inputTel.value = document.querySelector("#telefonoCliente").textContent;
	  form.appendChild(inputTel);
	  
	  var inputGenero = document.createElement('input');
	  inputGenero.type = 'hidden';
	  inputGenero.name = 'SexoCliente';
	  inputGenero.value = document.querySelector("#SexoCliente").selectedOptions[0];
	  form.appendChild(inputGenero);
	  
	  var inputNacion = document.createElement('input');
	  inputNacion.type = 'hidden';
	  inputNacion.name = 'NacioCliente';
	  inputNacion.value = document.querySelector("#NacioCliente").selectedOptions[0];
	  form.appendChild(inputNacion);
	  
	  var inputFecha = document.createElement('input');
	  inputFecha.type = 'hidden';
	  inputFecha.name = 'FNacimientoCliente';
	  inputFecha.value = document.querySelector("#FNacimientoCliente").value;
	  form.appendChild(inputFecha);
	  
	  var inputProv = document.createElement('input');
	  inputProv.type = 'hidden';
	  inputProv.name = 'ProvCliente';
	  inputProv.value = provId;
	  form.appendChild(inputProv);
	  
	  var inputDirecc = document.createElement('input');
	  inputDirecc.type = 'hidden';
	  inputDirecc.name = 'DirecCliente';
	  inputDirecc.value = document.querySelector("#DirecCliente").textContent;
	  form.appendChild(inputDirecc);
	  
	  var inputCorreo = document.createElement('input');
	  inputCorreo.type = 'hidden';
	  inputCorreo.name = 'CorreoCliente';
	  inputCorreo.value = document.querySelector("#CorreoCliente").textContent;
	  form.appendChild(inputCorreo);
	  

	  document.body.appendChild(form);
	  
	  form.submit();
}