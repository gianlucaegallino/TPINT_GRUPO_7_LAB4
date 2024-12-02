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
	
	  var form = document.createElement('form');
	  form.method = 'GET';
	  form.action = 'SIClientes';

	  
	  //Generamos un form invisible, y cargamos los datos del form real para que se mantengan
	  
	  let inputDni = document.createElement('input');
	  inputDni.type = 'hidden';
	  inputDni.name = 'DniCliente';
	  inputDni.value = document.querySelector("#DniCliente").value;
	  form.appendChild(inputDni);
	  console.log(document.getElementById("#DniCliente"));
	  
	  let inputCuil = document.createElement('input');
	  inputCuil.type = 'hidden';
	  inputCuil.name = 'CUILCliente2';
	  inputCuil.value = document.querySelector("#CUILCliente").value;
	  form.appendChild(inputCuil);
	  console.log(document.getElementById("#CUILCliente"));
	  
	  let inputNombre = document.createElement('input');
	  inputNombre.type = 'hidden';
	  inputNombre.name = 'nombreCliente';
	  inputNombre.value = document.querySelector("#nombreCliente").value;
	  form.appendChild(inputNombre);
	  console.log(document.getElementById("#nombreCliente"));
	  
	  let inputApe = document.createElement('input');
	  inputApe.type = 'hidden';
	  inputApe.name = 'apellidoCliente';
	  inputApe.value = document.querySelector("#apellidoCliente").textContent;
	  form.appendChild(inputApe);
	  console.log(inputApe);
	  
	  let inputTel = document.createElement('input');
	  inputTel.type = 'hidden';
	  inputTel.name = 'telefonoCliente';
	  inputTel.value = document.querySelector("#telefonoCliente").textContent;
	  form.appendChild(inputTel);
	  console.log(inputTel);
	  
	  let inputGenero = document.createElement('input');
	  inputGenero.type = 'hidden';
	  inputGenero.name = 'SexoCliente';
	  inputGenero.value = document.querySelector("#SexoCliente").selectedOptions[0];
	  form.appendChild(inputGenero);
	  console.log(inputGenero);
	  
	  let inputNacion = document.createElement('input');
	  inputNacion.type = 'hidden';
	  inputNacion.name = 'NacioCliente';
	  inputNacion.value = document.querySelector("#NacioCliente").selectedOptions[0];
	  form.appendChild(inputNacion);
	  console.log(inputNacion);
	  
	  let inputFecha = document.createElement('input');
	  inputFecha.type = 'hidden';
	  inputFecha.name = 'FNacimientoCliente';
	  inputFecha.value = document.querySelector("#FNacimientoCliente").value;
	  form.appendChild(inputFecha);
	  console.log(inputFecha);
	  
	  let inputProv = document.createElement('input');
	  inputProv.type = 'hidden';
	  inputProv.name = 'ProvCliente';
	  inputProv.value = provId;
	  form.appendChild(inputProv);
	  console.log(inputProv);
	  
	  let inputDirecc = document.createElement('input');
	  inputDirecc.type = 'hidden';
	  inputDirecc.name = 'DirecCliente';
	  inputDirecc.value = document.querySelector("#DirecCliente").textContent;
	  form.appendChild(inputDirecc);
	  console.log(inputDirecc);
	  
	  let inputCorreo = document.createElement('input');
	  inputCorreo.type = 'hidden';
	  inputCorreo.name = 'CorreoCliente';
	  inputCorreo.value = document.querySelector("#CorreoCliente").textContent;
	  form.appendChild(inputCorreo);
	  console.log(inputCorreo);
	  

	  document.body.appendChild(form);
	  
	  form.submit();
}