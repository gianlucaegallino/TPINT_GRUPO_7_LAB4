// Función para mostrar y ocultar formularios
function mostrarForm(formId) {
    // Ocultar todos los formularios
    document.getElementById('formListarCliente').style.display = 'none';
    document.getElementById('formularioAgregarCliente').style.display = 'none';
    document.getElementById('formularioMoECliente').style.display = 'none';
    
    // Mostrar el formulario correspondiente
    document.getElementById(formId).style.display = 'block';
}


//document.addEventListener('DOMContentLoaded', function() {
//    var btnbuscar = document.getElementById('btnbuscar');
//    var btnModificar = document.querySelector('.botonModificar'); // Selecciona el botón Cancelar
//    var tablaCliente = document.getElementById('tablaCliente');
//    var tablaEditar = document.getElementById('tablaEditar');
//
//    btnbuscar.addEventListener('click', function() {
//        tablaCliente.style.display = 'none';
//        tablaEditar.style.display = 'block';
//    });
//
//    btnModificar.addEventListener('click', function() {
//        tablaCliente.style.display = 'block';
//        tablaEditar.style.display = 'none';
//    });
//});


document.addEventListener('DOMContentLoaded', function() {
    var btnModificar = document.getElementById('btnModificar');
    var btnGuardar = document.querySelector('input[name="btnGuardar"]');
    var btnEliminar = document.getElementById('btnEliminar'); // Selecciona el botón Eliminar
    var btnCancelar = document.getElementById('btnCancelar'); // Selecciona el botón Cancelar
    var btnEliminarSubmit = document.querySelector('input[name="btnEliminarSubmit"]');
    var camposEditables = document.querySelectorAll('.editable input');

    btnModificar.addEventListener('click', function() {
        camposEditables.forEach(function(input) {
            input.style.display = 'block';
            input.previousElementSibling.style.display = 'none';
        });
        btnGuardar.style.display = 'block';
        btnModificar.style.display = 'none';
        btnCancelar.style.display = 'block'; // Muestra el botón Cancelar
        btnEliminar.style.display = 'none'; // Oculta el botón Eliminar
    });

    btnCancelar.addEventListener('click', function() {
        camposEditables.forEach(function(input) {
            input.style.display = 'none';
            input.previousElementSibling.style.display = 'block';
        });
        btnGuardar.style.display = 'none';
        btnModificar.style.display = 'block';
        btnCancelar.style.display = 'none'; // Oculta el botón Cancelar
        btnEliminar.style.display = 'block'; // Muestra el botón Eliminar
    });
    
    btnEliminar.addEventListener('click', function() {
        camposEditables.forEach(function(input) {
            input.style.display = 'block';
            input.previousElementSibling.style.display = 'none';
        });
        btnGuardar.style.display = 'block';
        btnModificar.style.display = 'none';
        btnCancelar.style.display = 'block';
        btnEliminar.style.display = 'none'; // Ocultar el botón Eliminar
    });
 // Eliminar cliente
    btnEliminar.addEventListener('click', function() {
        // Ocultar botones de modificar y guardar
        btnModificar.style.display = 'none';
        btnGuardar.style.display = 'none';

        // Mostrar botones de confirmación de eliminación
        btnEliminarSubmit.style.display = 'block'; // Mostrar el submit para eliminar
        btnCancelar.style.display = 'block'; // Mostrar el botón Cancelar
    });
});