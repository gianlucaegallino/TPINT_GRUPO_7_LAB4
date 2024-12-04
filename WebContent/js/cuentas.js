document.addEventListener('DOMContentLoaded', function() {
    let btnModificar = document.getElementById('btnModificar');
    let btnGuardar = document.querySelector('.botones-modificar-eliminar > input[name="btnGuardar"]');
    let btnEliminar = document.getElementById('btnEliminar');
    let btnCancelar = document.getElementById('btnCancelar');
    let EliminarSubmit = document.getElementById('Eliminar');
    let camposEditables = document.querySelectorAll('.editable input');

    // BOTON MODIFICAR
    if (btnModificar != null) {
        btnModificar.addEventListener('click', function() {
            camposEditables.forEach(function(input) {
                input.style.display = 'block';

                // Verifica si el elemento hermano anterior existe
                if (input.previousElementSibling) {
                    input.previousElementSibling.style.display = 'none';
                }
            });
            btnGuardar.style.display = 'block';
            btnModificar.style.display = 'none';
            btnCancelar.style.display = 'block';
            btnEliminar.style.display = 'none';
        });
    }

    // Botón Cancelar
    if (btnCancelar != null) {
        btnCancelar.addEventListener('click', function() {
            camposEditables.forEach(function(input) {
                input.style.display = 'none';

                // Verifica si el elemento hermano anterior existe
                if (input.previousElementSibling) {
                    input.previousElementSibling.style.display = 'block';
                }
            });
            btnGuardar.style.display = 'none';
            btnModificar.style.display = 'block';
            btnCancelar.style.display = 'none';
            btnEliminar.style.display = 'block';
        });
    }

    // Botón Eliminar
    if (btnEliminar != null) {
        btnEliminar.addEventListener('click', function() {
            camposEditables.forEach(function(input) {
                input.disabled = true;
                input.style.backgroundColor = '#ddd';
            });
            btnModificar.style.display = 'none';
            btnEliminar.style.display = 'none';
            btnCancelar.style.display = 'block';
            EliminarSubmit.style.display = 'block';
        });
    }
});