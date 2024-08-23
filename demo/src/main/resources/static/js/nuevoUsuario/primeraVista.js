import * as valBusca from '/js/functionSnippets/validarUsuario.js';

'use strict'

const formulario = document.getElementById('formularioDatos');
const arrInputs = Array.from(document.querySelectorAll('.usuarioInput'));
const arrFallos = Array.from(document.querySelectorAll('.usuarioError'));
const arrFunciones = [
    valBusca.validarNombreUsuario,
    valBusca.validarEmail,
    valBusca.validarPassword,
    valBusca.validarCiudad,
    valBusca.validarTelefono
];

for(let i = 0; i < arrFunciones.length; i++){
    arrInputs[i].addEventListener('input', () => {
        arrFunciones[i](arrInputs[i], arrFallos[i]);
    });
};

document.getElementById('rol').addEventListener('change', () => {
    valBusca.validarRol(document.getElementById('rol'), arrFallos[arrFallos.length - 1]);
});


document.getElementById('subirUsuario').addEventListener('click', async (e) => {
    e.preventDefault();

    let confirmarValidaciones = true;

    for(let i = 0; i < arrFunciones.length; i++){
        let resultado = await arrFunciones[i](arrInputs[i], arrFallos[i]);
        if(resultado === false) confirmarValidaciones = false;
    }

    if(valBusca.validarRol(document.getElementById('rol'), arrFallos[arrFallos.length - 1]) === false){
        confirmarValidaciones = false;
    }
    
    if(confirmarValidaciones === true) formulario.submit();
});