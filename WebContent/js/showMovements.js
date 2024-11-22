/*

// Funcion placeholder para mostrar valores. Hacer funcional en Parte 2 con java. 
let sel = document.querySelectorAll("#account-select");
sel.addEventListener("onchange", showMovements(sel.value));


function showMovements(cbu) {
        const movements = [
          { fecha: "2024-10-01", descripcion: "Deposito", monto: "$200.00" },
          { fecha: "2024-10-05", descripcion: "Retiro", monto: "$50.00" },
          {
            fecha: "2024-10-10",
            descripcion: "Transferencia",
            monto: "$100.00",
          },
        ];

        const tbody = document.getElementById("movements-table-body");
        tbody.innerHTML = "";

        movements.forEach(function (movement) {
          const row =
            "<tr>" +
            "<td>" +
            movement.fecha +
            "</td>" +
            "<td>" +
            movement.descripcion +
            "</td>" +
            "<td>" +
            movement.monto +
            "</td>" +
            "</tr>";
          tbody.innerHTML += row;
        });

        document.getElementById("movement-history").style.display = "block";
}
        */