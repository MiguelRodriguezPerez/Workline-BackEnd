import * as valOferta from '/js/functionSnippets/validarOferta.js'

"use strict"
/*En el css de la edición de estilos, el botón para subir la oferta
tiene por defecto display:none. Para evitar duplicar dicha hoja de estilos,
declaro esta línea */
// document.getElementById('subirCambios').style.display = 'block';

const formulario = document.getElementById('formularioOferta');
const arrayInputs = Array.from(document.querySelectorAll('.ofertaInput'));
const arraySelects = Array.from(document.getElementsByTagName('select'));
const mensajesError = Array.from(document.querySelectorAll('.falloOferta'));
const mensajesErrorSelect = Array.from(document.querySelectorAll('.falloOfertaSelect'));
const arrayValidaciones = [
    valOferta.validarPuesto,
    valOferta.validarMenorQue20,
    valOferta.validarDescripcion
];

for(let i = 0; i < arrayValidaciones.length; i++){
    arrayInputs[i].addEventListener('input', () => {
        arrayValidaciones[i](arrayInputs[i], mensajesError[i]);
    });
}

for(let i = 0; i < arraySelects.length; i++){
    arraySelects[i].addEventListener('change', () => {
        valOferta.validarSelect(arraySelects[i],mensajesErrorSelect[i]);
    });
}

document.getElementById('subirOferta').addEventListener('click', (e) => {
    e.preventDefault();

    let confirmarValidacion = true;

    for(let i = 0; i < arrayValidaciones.length; i++){
        let resultado = arrayValidaciones[i](arrayInputs[i], mensajesError[i]);
        if(resultado === false) confirmarValidacion = false;
    }
    
    for(let i = 0; i < arraySelects.length; i++){
        let resultado = valOferta.validarSelect(arraySelects[i], mensajesErrorSelect[i]);
        if(resultado === false) confirmarValidacion = false;
    }

    if(confirmarValidacion === true) {
        formulario.submit();
    }
})





