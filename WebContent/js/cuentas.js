document.addEventListener('DOMContentLoaded', function(){
	let btnModificar = document.getElementById('btnModificar');
	let btnGuardar = document.querySelector('form > input[name="btnGuardar"]');
	let btnEliminar = document.getElementById('btnEliminar');
	let btnCancelar = document.getElementById('btnCancelar');
	let EliminarSubmit = document.getElementById('Eliminar');
	let camposEditables = document.querySelectorAll('.editable input');
	
	
	// BOTON MODIFICAR
	if(btnModificar != null){
		btnModificar.addEventListener('click', function(){
			camposEditables.forEach(function(input){
				input.style.display = 'block';
				input.previousElementSibiling.style.display = 'none';
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
        EliminarSubmit.style.display = 'block'; 
    });
    }
});

