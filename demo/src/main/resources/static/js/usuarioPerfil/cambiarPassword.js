import * as valFunciones from '/js/functionSnippets/validarUsuario.js'

'use strict'
const mensajeError = Array.from(document.getElementsByClassName('mensajeError'));
const formPassword = document.getElementById('formPassword');
const nuevoPassword = document.getElementById('nuevoPassword');

nuevoPassword.addEventListener('input', () => {
    valFunciones.validarPassword(nuevoPassword,mensajeError[0]);
})

document.getElementById('confirmarPassword').addEventListener('click', (e) => {
    e.preventDefault();

    if(valFunciones.validarPassword(nuevoPassword,mensajeError[0])) formPassword.submit();
})