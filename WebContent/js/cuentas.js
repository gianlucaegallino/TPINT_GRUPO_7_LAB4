// Función para mostrar y ocultar formularios
function mostrarFormulario(formularioId) {
    // Ocultar todos los formularios
    document.getElementById('formularioAgregar').style.display = 'none';
    document.getElementById('formularioEditarEliminar').style.display = 'none';
    document.getElementById('formularioListar').style.display = 'none';
    document.getElementById('tablaCuentas').style.display = 'none'; // Ocultar tabla al cambiar formularios
    
    // Ocultar el formulario de mensaje
    document.querySelector('.formulario-mensaje').style.display = 'none';

    // Mostrar el formulario correspondiente
    document.getElementById(formularioId).style.display = 'block';
}

// Mostrar el formulario de listar cuentas cuando se hace clic en el botón "Listar"
document.getElementById('botonListar').addEventListener('click', function() {
    mostrarFormulario('formularioListar');
    document.getElementById('tablaCuentas').style.display = 'table'; // Mostrar la tabla con las cuentas
});

// Mostrar el formulario de editar/eliminar cuando se hace clic en el botón "Editar/Eliminar"
document.getElementById('botonEditarEliminar').addEventListener('click', function() {
    mostrarFormulario('formularioEditarEliminar');
    document.getElementById('tablaCuentasEditarEliminar').style.display = 'table'; // Mostrar la tabla
});

// Mostrar el formulario de agregar cuenta cuando se hace clic en el botón "Agregar"
document.getElementById('botonAgregar').addEventListener('click', function() {
    mostrarFormulario('formularioAgregar');
});
