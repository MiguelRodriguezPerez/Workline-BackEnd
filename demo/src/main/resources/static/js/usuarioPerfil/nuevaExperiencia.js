import * as validaciones from '/js/functionSnippets/validarExperiencia.js'

"use strict"

const formulario = document.getElementById('formNuevoConocimiento');
/*El primer indice estaba vacío y como no se puede modificar un NodeList, se pasa a array
y se modifica*/
const arrayInputsNodos = document.querySelectorAll('#formNuevoConocimiento input:not([hidden]):not([type="submit"])');
const arrayInputs = Array.from(arrayInputsNodos);
arrayInputs.splice(0,1);

console.log(arrayInputs);

const arrayFallos = document.querySelectorAll('.mensajeError');

const valPuesto = () =>{
    if(!validaciones.validarTextoConocimiento(arrayInputs[0].value)){
        validaciones.mostrarError(arrayFallos[0],arrayInputs[0]);
        return false;
    }
    else{
        validaciones.limpiarError(arrayFallos[0],arrayInputs[0]);
        return true;
    }
}
arrayInputs[0].addEventListener('input',valPuesto);

const valEmpresa = () =>{
    if(!validaciones.validarTextoConocimiento(arrayInputs[1].value)){
        validaciones.mostrarError(arrayFallos[1],arrayInputs[1]);
        return false;
    }
    else{
        validaciones.limpiarError(arrayFallos[1],arrayInputs[1]);
        return true;
    }
}
arrayInputs[1].addEventListener('input',valEmpresa);

const valFechaInicio = () => {
    console.log(arrayInputs[2].value)
    if(!validaciones.validarFecha(arrayInputs[2].value)){
        validaciones.mostrarError(arrayFallos[2],arrayInputs[2]);
        return false;
    }
    else{
        validaciones.limpiarError(arrayFallos[2],arrayInputs[2]);
        return true;
    }
}
arrayInputs[2].addEventListener('input',valFechaInicio);

const valFechaFin = () =>{
    if(!validaciones.validarFecha(arrayInputs[3].value)){
        validaciones.mostrarError(arrayFallos[3],arrayInputs[3]);
        return false;
    }
    else{
        validaciones.limpiarError(arrayFallos[3],arrayInputs[3]);
        return true;
    }
}
arrayInputs[3].addEventListener('input',valFechaFin);

document.getElementById('subirConocimiento').addEventListener('click', (e) =>{
    e.preventDefault();
    let confirmarSubmit = true;
    if(!valPuesto()) confirmarSubmit = false;
    if(!valEmpresa()) confirmarSubmit = false;
    if(!valFechaInicio()) confirmarSubmit = false;
    if(!valFechaFin()) confirmarSubmit = false;

    if(confirmarSubmit) formulario.submit();
});