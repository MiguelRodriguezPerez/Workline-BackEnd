"use strict"

function mostrarError(input){
    input.classList.add('inputError');
}

function limpiarError(input){
    input.classList.remove('inputError');
}

export function validarPuesto(input, pFallo) {
    const texto = input.value;

    if (texto.length > 30) {
        pFallo.textContent = 'El puesto no puede tener más de 30 caracteres';
        mostrarError(input);
        return false;
    } else if (texto === '') {
        pFallo.textContent = 'El puesto no puede quedar vacío';
        mostrarError(input);
        return false;
    } else {
        pFallo.textContent = '';
        limpiarError(input);
        return true;
    }
}


export function validarEmpresa(input, pFallo) {
    const texto = input.value;

    if (texto.length > 20) {
        pFallo.textContent = 'El nombre de la empresa no puede tener más de 20 caracteres';
        mostrarError(input);
        return false;
    } 
    else if (texto === '') {
        pFallo.textContent = 'El nombre de la empresa no puede quedar vacío';
        mostrarError(input);
        return false;
    } 
    else {
        pFallo.textContent = '';
        limpiarError(input);
        return true;
    }
}
