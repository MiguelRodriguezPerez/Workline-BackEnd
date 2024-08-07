import * as valEstudio from '/js/functionSnippets/validarConocimiento.js';

"use strict"

const formularioConocimiento = document.getElementById('formNuevoBuscaConocimiento');
const inputsConocimiento = Array.from(document.querySelectorAll('.conocimientoInput'));
const fallosConocimiento = Array.from(document.querySelectorAll('.falloConocimiento'));
let arrayValidaciones = [];

console.log(inputsConocimiento)
console.log(fallosConocimiento)

const valTitulo = () =>{
    if(!valEstudio.validarTitulo(inputsConocimiento[0].value)){
        valEstudio.mostrarError(fallosConocimiento[0],inputsConocimiento[0]);
        return false;
    }
    else{
        valEstudio.limpiarError(fallosConocimiento[0],inputsConocimiento[0]);
        return true;
    }
}
// inputsConocimiento[0].addEventListener('input',valTitulo);
arrayValidaciones.push(valTitulo);

const valCentro = () =>{
    if(!valEstudio.validarCentro(inputsConocimiento[1].value)){
        valEstudio.mostrarError(fallosConocimiento[1],inputsConocimiento[1]);
        return false;
    }
    else{
        valEstudio.limpiarError(fallosConocimiento[1],inputsConocimiento[1]);
        return true;
    }
}
// inputsConocimiento[1].addEventListener('input',valCentro);
arrayValidaciones.push(valCentro);

const valFecha = () =>{
    if(!valEstudio.validarFecha(inputsConocimiento[2].value)){
        valEstudio.mostrarError(fallosConocimiento[2],inputsConocimiento[2]);
        return false;
    }
    else{
        valEstudio.limpiarError(fallosConocimiento[2],inputsConocimiento[2]);
        return true;
    }
}
// inputsConocimiento[2].addEventListener('input',valFecha);
arrayValidaciones.push(valFecha);

const valFechaDos = () =>{
    if(!valEstudio.validarFecha(inputsConocimiento[3].value)){
        valEstudio.mostrarError(fallosConocimiento[3],inputsConocimiento[3]);
        return false;
    }
    else{
        valEstudio.limpiarError(fallosConocimiento[3],inputsConocimiento[3]);
    }

    if(!valEstudio.compararFechas(inputsConocimiento[2].value,inputsConocimiento[3].value)){
        valEstudio.mostrarError(fallosConocimiento[4],inputsConocimiento[3]);
        return false;
    }
    else{
        valEstudio.limpiarError(fallosConocimiento[4],inputsConocimiento[3]);
        return true;
    }
}
// inputsConocimiento[3].addEventListener('input',valFechaDos);
arrayValidaciones.push(valFechaDos);

document.getElementById('subirConocimiento').addEventListener('click',(e)=>{
    e.preventDefault();
    let validacionesCorrectas = true;

    for(const val of arrayValidaciones){
        validacionesCorrectas = val();
    }

    if(validacionesCorrectas === true){
        inputsConocimiento[2].value = valEstudio.convertirFechaJava(inputsConocimiento[2].value);
        inputsConocimiento[3].value = valEstudio.convertirFechaJava(inputsConocimiento[3].value); 

        formularioConocimiento.submit();
    } 
});