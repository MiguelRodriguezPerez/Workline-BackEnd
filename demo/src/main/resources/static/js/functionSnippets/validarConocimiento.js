"use strict"

function mostrarError(input){
    input.classList.add('inputError');
}

function limpiarError(input){
    input.classList.remove('inputError');
}

export function validarTitulo(input,pFallo){
    const titulo = input.value;

    if(titulo.length > 40){
        pFallo.textContent = 'El título no puede tener más de 40 carácteres';
        mostrarError(input);
        return false;
    }
    else if(titulo === ''){
        pFallo.textContent = 'El título no puede quedar vacío';
        mostrarError(input);
        return false;
    }
    else{
        pFallo.textContent = '';
        limpiarError(input);
        return true;
    }
}

export function validarCentro(input, pFallo) {
    const centro = input.value;

    if (centro.length > 30) {
        pFallo.textContent = 'El centro educativo no puede tener más de 30 carácteres';
        mostrarError(input);
        return false;
    } 
    else if (centro === '') {
        pFallo.textContent = 'El centro educativo no puede quedar vacío';
        mostrarError(input);
        return false;
    } 
    else {
        pFallo.textContent = '';
        limpiarError(input);
        return true;
    }
}

export function validarFecha(input, pFallo) {
    const fecha = input.value;

    if (!/^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/.test(fecha)) {
        pFallo.textContent = 'La fecha debe estar en formato dd/mm/yyyy';
        mostrarError(input);
        return false;
    } 
    else if (fecha === '') {
        pFallo.textContent = 'La fecha no puede quedar vacía';
        mostrarError(input);
        return false;
    } 
    else {
        pFallo.textContent = '';
        limpiarError(input);
        return true;
    }
}

export function convertirFecha(fecha) {
    const [dia, mes, año] = fecha.split('/').map(Number);
    return new Date(año, mes - 1, dia); // Los meses en JavaScript van de 0 (enero) a 11 (diciembre)
}

function convertirFechaJava(fecha){
    const [dia, mes, año] = fecha.split('/').map(Number);
    return año + '-' + mes + '-' + dia;
}

export function prepararFechas(arrInputs){
    const fechaRegex = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/(19|20)\d{2}$/;

    for(const input of arrInputs){
        if(fechaRegex.test(input.value)){
            input.value = convertirFechaJava(input.value)
        }
    }
}

export function compararFechas(fecha1Input, fecha2Input, pFallo, pFallo2) {
    const fecha1 = fecha1Input.value;
    const fecha2 = fecha2Input.value;

    if(!validarFecha(fecha1Input, pFallo) || !validarFecha(fecha2Input , pFallo2)) return false;

    const date1 = convertirFecha(fecha1);
    const date2 = convertirFecha(fecha2);

    if (date1 >= date2) {
        pFallo2.textContent = 'La fecha de inicio no puede ser superior o igual a la fecha de finalización';
        mostrarError(fecha1Input);
        mostrarError(fecha2Input);
        return false;
    } 
    else {
        pFallo.textContent = '';
        limpiarError(fecha1Input);
        limpiarError(fecha2Input);
        return true;
    }
}
