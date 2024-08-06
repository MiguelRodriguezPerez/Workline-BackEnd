"use strict"

import * as funciones from '/js/functionSnippets/validarUsuario.js';


/*El primer indice estaba vacío y como no se puede modificar un NodeList, se pasa a array
y se modifica*/

const arrayInputs = Array.from(document.querySelectorAll("#primerFormulario input[type='text']:not([hidden])"));
const arrayFallos = document.querySelectorAll('.mensajeError');
const formulario = document.getElementById('formularioDatos');
const rolSelect = document.querySelector('select');
const validacionesArray = [];

console.log(arrayInputs)

const valNombre = async () =>{
    if(!funciones.validarNombreUsuario(arrayInputs[0].value)){
        funciones.mostrarError(arrayFallos[0],arrayInputs[0]);
        return false;
    }
    else if(await funciones.esNombreRepetido(arrayInputs[0].value) === true){
        funciones.limpiarError(arrayFallos[0],arrayInputs[0]);
        funciones.mostrarError(arrayFallos[1],arrayInputs[0]);
        return false;
    }
    else{
        funciones.limpiarError(arrayFallos[0],arrayInputs[0]);
        funciones.limpiarError(arrayFallos[1],arrayInputs[0]);
        return true;
    }
}
arrayInputs[0].addEventListener('input',valNombre);
validacionesArray.push(valNombre);

const valEmail = () =>{
    if(!funciones.validarLongitudEmail(arrayInputs[1].value)){
        funciones.mostrarError(arrayFallos[2],arrayInputs[1]);
        return false;
    }
    else if(!funciones.validarExpresionEmail(arrayInputs[1].value)){
        funciones.limpiarError(arrayFallos[2],arrayInputs[1]);
        funciones.mostrarError(arrayFallos[3],arrayInputs[1]);
        return false;
    }
    else{
        funciones.limpiarError(arrayFallos[2],arrayInputs[1]);
        funciones.limpiarError(arrayFallos[3],arrayInputs[1]);
        return true;
    }
}
arrayInputs[1].addEventListener('input',valEmail);
validacionesArray.push(valEmail);

const valPassword = () =>{
    if(!funciones.validarPassword(arrayInputs[2].value)){
        funciones.mostrarError(arrayFallos[4],arrayInputs[2]);
        return false;
    }
    else{
        funciones.limpiarError(arrayFallos[4],arrayInputs[2]);
        return true;
    }
}
arrayInputs[2].addEventListener('input',valPassword);
validacionesArray.push(valPassword);

const valTelefono = () =>{
    if(!funciones.validarTelefono(arrayInputs[3].value)){
        funciones.mostrarError(arrayFallos[5],arrayInputs[3]);
        return false;
    }
    else{
        funciones.limpiarError(arrayFallos[5],arrayInputs[3]);
        return true;
    }
}
arrayInputs[3].addEventListener('input',valTelefono);
validacionesArray.push(valTelefono);

const valCiudad = () =>{
    if(!funciones.validarCiudad(arrayInputs[4].value)){
        funciones.mostrarError(arrayFallos[6],arrayInputs[4]);
        return false;
    }
    else{
        funciones.limpiarError(arrayFallos[6],arrayInputs[4]);
        return true;
    }
}
arrayInputs[4].addEventListener('input',valCiudad);
validacionesArray.push(valCiudad);

const valRol = () =>{
    if(rolSelect.value !== undefined){
        if(!funciones.validarRol(rolSelect.value)){
            funciones.mostrarError(arrayFallos[7],rolSelect);
            return false;
        }
        else{
            funciones.limpiarError(arrayFallos[7],rolSelect);
            return true;
        }
    }
}
rolSelect.addEventListener('change',valRol);
validacionesArray.push(valRol);


document.getElementById('editarUsuario').addEventListener('click', async (e) => {
    e.preventDefault();
    let verificarSubmit = true;

    for(const val of validacionesArray){
        verificarSubmit = val();
    }

    if(verificarSubmit) formulario.submit();
});
