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
    return (/^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/.test(fecha) && fecha !== '');
}

function convertirFecha(fecha) {
    const [dia, mes, año] = fecha.split('/').map(Number);
    return new Date(año, mes - 1, dia); // Los meses en JavaScript van de 0 (enero) a 11 (diciembre)
}

export function compararFechas(fecha1, fecha2) {
    const date1 = convertirFecha(fecha1);
    const date2 = convertirFecha(fecha2);

    return (date1 < date2);
}