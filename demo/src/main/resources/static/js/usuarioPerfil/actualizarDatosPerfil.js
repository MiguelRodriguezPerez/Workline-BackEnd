import * as valBusca from '/js/functionSnippets/validarUsuario.js';

'use strict'

const formulario = document.getElementById('formularioDatos');
const arrInputs = Array.from(document.querySelectorAll('.usuarioInput'));
const arrFallos = Array.from(document.querySelectorAll('.usuarioError'));
const arrFunciones = [
    valBusca.validarNombreUsuario,
    valBusca.validarEmail,
    valBusca.validarTelefono,
    valBusca.validarCiudad
];

for(let i = 0; i < arrFunciones.length; i++){
    arrInputs[i].addEventListener('input', () => {
        arrFunciones[i](arrInputs[i], arrFallos[i]);
    });
};


document.getElementById('editarUsuario').addEventListener('click', async (e) => {
    e.preventDefault();

    let confirmarValidaciones = true;

    
    for(let i = 0; i < arrFunciones.length; i++){
        let resultado = await arrFunciones[i](arrInputs[i], arrFallos[i]);
        if(resultado === false) confirmarValidaciones = false;
    }

    if(confirmarValidaciones === true) formulario.submit();
});