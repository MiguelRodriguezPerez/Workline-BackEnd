import * as valExperiencia from '/js/functionSnippets/validarExperiencia.js';
import * as valFecha from '/js/functionSnippets/validarFechas.js';

'use strict'

const formulario = document.getElementById('formNuevoBuscaExperiencia');
const arrInputs = Array.from(document.querySelectorAll('.experienciaInput'));
const arrFallos = Array.from(document.querySelectorAll('.falloExperiencia'));
const arrFunciones = [
    valExperiencia.validarPuesto,
    valExperiencia.validarEmpresa,
    valFecha.validarFecha,
    valFecha.validarFecha
];

for(let i = 0; i < arrFunciones.length; i++){
    arrInputs[i].addEventListener('input', () => {
        arrFunciones[i](arrInputs[i],arrFallos[i]);
    });
}

arrInputs[arrInputs.length - 1].addEventListener('input', () => {
    valFecha.compararFechas(
        arrInputs[arrInputs.length - 2],
        arrInputs[arrInputs.length - 1],
        arrFallos[arrFallos.length - 2],
        arrFallos[arrFallos.length - 1]
    );
});

document.getElementById('subirExperiencia').addEventListener('click' , (e) => {
    e.preventDefault();

    let confirmarValidaciones = true;
    for(let i = 0; i < arrFunciones.length && confirmarValidaciones === true; i++){
        confirmarValidaciones = arrFunciones[i](arrInputs[i],arrFallos[i]);
    }

    if(confirmarValidaciones === true){
        confirmarValidaciones = valFecha.compararFechas(
            arrInputs[arrInputs.length - 2],
            arrInputs[arrInputs.length - 1],
            arrFallos[arrFallos.length - 2],
            arrFallos[arrFallos.length - 1]
        );
    }

    if(confirmarValidaciones === true){
        valFecha.prepararFechas(arrInputs);
        formulario.submit();
    } 
});