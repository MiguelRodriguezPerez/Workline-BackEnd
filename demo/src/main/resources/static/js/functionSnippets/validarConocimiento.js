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


