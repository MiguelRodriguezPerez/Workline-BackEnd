import * as valExp from '/js/functionSnippets/validarExperiencia.js'

"use strict"

const formulario = document.getElementById('formNuevoBuscaExperiencia');
const inputsExp = Array.from(document.querySelectorAll('.experienciaInput'));
const errores = Array.from(document.querySelectorAll('.falloExperiencia'));
let arrayValidaciones = [];

const valPuesto = () =>{
    if(!valExp.validarPuesto(inputsExp[0].value)){
        valExp.mostrarError(errores[0],inputsExp[0]);
        return false;
    }
    else{
        valExp.limpiarError(errores[0],inputsExp[0]);
        return true;
    }
}
arrayValidaciones.push(valPuesto);

const valEmpresa = () =>{
    if(!valExp.validarEmpresa(inputsExp[1].value)){
        valExp.mostrarError(errores[1],inputsExp[1]);
        return false;
    }
    else{
        valExp.limpiarError(errores[1],inputsExp[1]);
        return true;
    }
}
arrayValidaciones.push(valEmpresa);

const valInicioExp = () =>{
    if(!valExp.validarFecha(inputsExp[2].value)){
        valExp.mostrarError(errores[2],inputsExp[2]);
        return false;
    }
    else{
        valExp.limpiarError(errores[2],inputsExp[2]);
        return true;
    }
}
arrayValidaciones.push(valInicioExp);

const valFinExp = () =>{

    if(!valExp.validarFecha(inputsExp[3].value)){
        valExp.mostrarError(errores[3],inputsExp[3]);
        return false;
    }
    else{
        valExp.limpiarError(errores[3],inputsExp[3]);
    }

    if(!valExp.compararFechas(inputsExp[2].value,inputsExp[3].value)){
        valExp.mostrarError(errores[4],inputsExp[3]);
        return false;
    }
    else{
        valExp.limpiarError(errores[4],inputsExp[3]); 
        return true;
    } 
}
arrayValidaciones.push(valFinExp);

document.getElementById('subirExperiencia').addEventListener('click',(e)=>{
    e.preventDefault();
    let validacionesCorrectas = true;

    for(const val of arrayValidaciones){
        validacionesCorrectas = val();
    }

    if(validacionesCorrectas === true){
        inputsExp[2].value = valExp.convertirFechaJava(inputsExp[2].value);
        inputsExp[3].value = valExp.convertirFechaJava(inputsExp[3].value); 

        formulario.submit();
    } 
});