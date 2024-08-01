import * as funciones from '/js/functionSnippets/validarUsuario.js';

document.querySelectorAll('#formularioDatos input:not([hidden]):not([type="submit"])')[2].value = '';

const arrayInputs = document.querySelectorAll('#formularioDatos input:not([hidden]):not([type="submit"])');
const arrayFallos = document.querySelectorAll('.mensajeError');
const formulario = document.getElementById('formularioDatos');

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


document.getElementById('editarUsuario').addEventListener('click', async (e) => {
    e.preventDefault();
    const nombreValido = await valNombre(); // Garantiza que esta promesa se resuelva
    if(nombreValido && valEmail() && valPassword() && valTelefono() && valCiudad()) formulario.submit();
});
