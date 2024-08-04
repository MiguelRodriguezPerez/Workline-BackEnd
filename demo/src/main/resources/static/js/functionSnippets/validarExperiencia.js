"use strict"

export function mostrarError(fallo,input){
    fallo.style.display = 'block';
    input.classList.add('inputError');
}

export function limpiarError(fallo,input){
    fallo.style.display = 'none';
    input.classList.remove('inputError');
}

export function validarTextoConocimiento(texto){
    console.log(texto)
    return (texto.length < 40 && texto !== '');
}

export function validarFecha(fecha){
    fecha = fecha.split("-").reverse().join("-");
    console.log(fecha)
    return(/^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\d{4}$/.test(fecha) && fecha !== '');
}