import * as valUsuario from '/js/functionSnippets/validarUsuario.js';

'use strict'

const formulario = document.getElementById('formularioDatos');
const arrInputs = Array.from(document.querySelectorAll('.usuarioInput'));
const arrFallos = Array.from(document.querySelectorAll('.usuarioError'));
const arrFunciones = [
    valUsuario.validarNombreUsuario,
    valUsuario.validarEmail,
    valUsuario.validarCiudad,
    valUsuario.validarTelefono
];


for(let i = 0; i < arrFunciones.length; i++){
    arrInputs[i].addEventListener('input', () => {
        arrFunciones[i](arrInputs[i], arrFallos[i]);
    });
};

const arrPasswords = Array.from(document.querySelectorAll('.verificarPassword'));

document.getElementById('passwordReal').addEventListener('input', () =>{
    valUsuario.validarPassword(arrPasswords[0], arrPasswords[1], document.getElementsByClassName('passwordError')[0]);
})

document.getElementById('rol').addEventListener('change', () => {
    valUsuario.validarRol(document.getElementById('rol'), arrFallos[arrFallos.length - 1]);
});


document.getElementById('subirUsuario').addEventListener('click', async (e) => {
    e.preventDefault();

    let confirmarValidaciones = true;

    for(let i = 0; i < arrFunciones.length; i++){
        let resultado = await arrFunciones[i](arrInputs[i], arrFallos[i]);
        if(resultado === false) confirmarValidaciones = false;
    }

    if(!valUsuario.validarPassword(arrPasswords[0], arrPasswords[1], document.getElementsByClassName('passwordError')[0])){
        confirmarValidaciones = false;
    }

    if(!valUsuario.validarRol(document.getElementById('rol'), arrFallos[arrFallos.length - 1])){
        confirmarValidaciones = false;
    }
    
    if(confirmarValidaciones === true) formulario.submit();
});