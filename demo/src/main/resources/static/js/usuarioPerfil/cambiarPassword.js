import * as valFunciones from '/js/functionSnippets/validarUsuario.js'

'use strict'
const mensajeError = Array.from(document.getElementsByClassName('mensajeError'));
const formPassword = document.getElementById('formPassword');
const password = document.getElementById('nuevoPassword');


const valNuevoPassword = () =>{
    if(!valFunciones.validarPassword(password.value)){
        mensajeError[0].textContent = 'La contraseña debe tener entre 14 y 30 carácteres combinando mayusculas minusculas números y caracteres especiales';
        password.classList.add('inputError');
        return false;
    }
    else{
        mensajeError[0].textContent = '';
        password.classList.remove('inputError');
        return true;
    }
}

document.getElementById('nuevoPassword').addEventListener('input',valNuevoPassword);

document.getElementById('confirmarPassword').addEventListener('click', (e) => {
    e.preventDefault();

    if(valNuevoPassword()) formPassword.submit();
})