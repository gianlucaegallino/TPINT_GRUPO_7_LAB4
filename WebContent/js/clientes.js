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




    // Botón Modificar
    if (btnModificar != null){
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
    }

    // Botón Cancelar
    if (btnCancelar != null){ 
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
    }


    // Botón Eliminar
    
    if (btnEliminar != null){ 
    btnEliminar.addEventListener('click', function() {
    	// Deshabilitar los campos editables
        camposEditables.forEach(function(input) {
            input.disabled = true; // Deshabilitar el campo de entrada
            input.style.backgroundColor = '#ddd'; // Cambiar el fondo a gris (opcional)
        });
        // Mostrar el botón "Eliminar" y ocultar los demás
        btnModificar.style.display = 'none';
        btnEliminar.style.display = 'none';
        btnCancelar.style.display = 'block'; 
        btnEliminarSubmit.style.display = 'block'; 
    });
    }


});

function cargarLocalidades(provId) {
	
	  console.log(hi);
	
	  var form = document.createElement('form');
	  form.method = 'GET';
	  form.action = 'SIClientes';

	  
	  //Generamos un form invisible, y cargamos los datos del form real para que se mantengan
	  

	  let inputProv = document.createElement('input');
	  inputProv.type = 'hidden';
	  inputProv.name = 'ProvCliente';
	  inputProv.value = provId;
	  form.appendChild(inputProv);
	  console.log(inputProv);
	
	  document.body.appendChild(form);
	  
	  form.submit();
}