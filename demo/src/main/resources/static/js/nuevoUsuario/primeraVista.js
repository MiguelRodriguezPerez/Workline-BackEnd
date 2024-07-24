"use strict"
/*NOTA PARA MI MISMO: Cuando declaras un texto sin comillas simples y lo rodeas
de // estas creando una instancia de objeto regex 
NOTA 2: Tuviste que crear una función para cada validación porque si el usuario
hacía click en input submit sin pasar (escribir) por los campos los errores no se mostraban*/

async function validarNombreUsuario(){
    const nombreUsuario = document.getElementById('nombreUsuario').value;
    const esNombreRepetido = await fetch('/nuevoUsuarioCreacion/esRepetido/' + nombreUsuario);

    if(nombreUsuario.length > 25) document.getElementById('err1').hidden = false;
    else document.getElementById('err1').hidden = true;

    if(esNombreRepetido) document.getElementById('err2').hidden = false;
    else document.getElementById('err2').hidden = true;
}
document.getElementById('nombreUsuario').addEventListener('input', validarNombreUsuario);


function validarEmail(){
    const email = document.getElementById('email').value;

    if(email.length > 35)document.getElementById('err4').hidden = false;
    else document.getElementById('err4').hidden = true;

    if(!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)) document.getElementById('err5').hidden = false;
    else document.getElementById('err5').hidden = true;
}
document.getElementById('email').addEventListener('input', validarEmail);

function validarPassword(){
    const password = document.getElementById('password').value;

    if(password.length > 30 || password.length < 14) document.getElementById('err6').hidden = false;
    else document.getElementById('err6').hidden = true;

    if(!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).*$/.test(password)) document.getElementById('err7').hidden = false;
    else document.getElementById('err7').hidden = true;
}

document.getElementById('password').addEventListener('input', validarPassword);

function validarTelefono(){
    const telefono = document.getElementById('telefono').value;

    if(!/^(6|7|8|9)\\d{8}$/.test(telefono)) document.getElementById('err8').hidden = false;
    else document.getElementById('err8').hidden = true;
}

document.getElementById('telefono').addEventListener('input',validarTelefono);

function validacionDefinitiva(){
    validarNombreUsuario();
    validarEmail();
    validarPassword();
    validarTelefono();

    const rolSeleccionado = document.getElementById('rol').value;
    console.log(rolSeleccionado);

    if(rolSeleccionado === 'Selecciona una opcion')document.getElementById('err9').hidden = false;
    else document.getElementById('err9').hidden = true;
}

document.getElementById('crearNuevoUsuario').addEventListener('click',(e) =>{
    e.preventDefault();
    
        const primerFormulario = document.getElementById('primerFormulario');
        validacionDefinitiva();
        /*Llama a todas las validaciones, si alguna es errónea se mostrará el mensaje de error
        y no se ejecutará el submit*/

        const mensajesError = document.querySelector('.mensajeError');
        const mensajesErrorOcultos = Array.from(mensajesError).some(p => p.hidden);

        if(mensajesErrorOcultos) primerFormulario.submit();
    });

