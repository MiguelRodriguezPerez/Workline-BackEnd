import * as funciones from '/js/functionSnippets/validarUsuario.js';

document.querySelectorAll('#formularioDatos input:not([hidden]):not([type="submit"])')[2].value = '';

const arrayInputs = document.querySelectorAll('#formularioDatos input:not([hidden]):not([type="submit"])');
const arrayFallos = document.querySelectorAll('.mensajeError');
console.log(arrayFallos);

arrayInputs[0].addEventListener('input',function(){
    funciones.validarNombreUsuario(this,arrayFallos[0],arrayFallos[1]);
});

arrayInputs[1].addEventListener('input',function(){
    funciones.validarEmail(this,arrayFallos[2],arrayFallos[3]);
});

arrayInputs[2].addEventListener('input',function(){
    funciones.validarPassword(this,arrayFallos[4]);
});

arrayInputs[3].addEventListener('input',function(){
    funciones.validarTelefono(this,arrayFallos[5]);
})
