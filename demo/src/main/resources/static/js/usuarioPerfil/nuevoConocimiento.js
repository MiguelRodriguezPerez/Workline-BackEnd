import * as valConocimiento from '/js/functionSnippets/validarConocimiento.js'

'use strict'

const formulario = document.getElementById('formNuevoBuscaConocimiento');
const arrInputs = Array.from(document.querySelectorAll('.conocimientoInput'));
const arrFallos = Array.from(document.querySelectorAll('.falloConocimiento'));
const arrFunciones = [
    valConocimiento.validarTitulo,
    valConocimiento.validarCentro,
    valConocimiento.validarFecha,
    valConocimiento.validarFecha
]

for(let i = 0; i < arrFunciones.length; i++){
    arrInputs[i].addEventListener('input',() => {
        arrFunciones[i](arrInputs[i],arrFallos[i])
    });
}

//Realiza la validación de comparar fechas
arrInputs[arrInputs.length - 1].addEventListener('input', () => {
    valConocimiento.compararFechas(
        arrInputs[arrInputs.length - 2],
        arrInputs[arrInputs.length - 1],
        arrFallos[arrFallos.length - 2],
        arrFallos[arrFallos.length - 1]
    );
});

document.getElementById('subirConocimiento').addEventListener('click' , (e) => {
    e.preventDefault();

    let confirmarValidaciones = true;
    for(let i = 0; i < arrFunciones.length && confirmarValidaciones === true; i++){
        confirmarValidaciones = arrFunciones[i](arrInputs[i],arrFallos[i]);
    }

    if(confirmarValidaciones === true){
        confirmarValidaciones = valConocimiento.compararFechas(
            arrInputs[arrInputs.length - 2],
            arrInputs[arrInputs.length - 1],
            arrFallos[arrFallos.length - 2],
            arrFallos[arrFallos.length - 1]
        );
    }

    if(confirmarValidaciones === true){
        valConocimiento.prepararFechas(arrInputs);
        formulario.submit();
    } 
});
