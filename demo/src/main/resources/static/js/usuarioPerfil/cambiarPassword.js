import * as valFunciones from '/js/functionSnippets/validarUsuario.js'

'use strict'
const mensajeError = Array.from(document.getElementsByClassName('mensajeError'));
const formPassword = document.getElementById('formPassword');
const passwords = Array.from(document.getElementsByClassName('verificarPassword'));

nuevoPassword.addEventListener('input', () => {
    valFunciones.validarPassword(nuevoPassword,mensajeError[0]);
})

document.getElementById('confirmarPassword').addEventListener('click', (e) => {
    e.preventDefault();

    if(valFunciones.validarPassword(passwords[0],passwords[1],mensajeError[0])) formPassword.submit();
})