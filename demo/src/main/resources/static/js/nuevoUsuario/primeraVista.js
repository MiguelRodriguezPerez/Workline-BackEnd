"use strict"
/*NOTA PARA MI MISMO: Cuando declaras un texto sin comillas simples y lo rodeas
de // estas creando una instancia de objeto regex 
NOTA 2: Tuviste que crear una función para cada validación porque si el usuario
hacía click en input submit sin pasar (escribir) por los campos los errores no se mostraban
contraseña validada:
*/

function subrayarError(arg){
    arg.classList.add('inputError');
}

function limpiarSubrayadoError(arg){
    arg.classList.remove('inputError');
}

async function validarNombreUsuario(){
    
    const tag = document.getElementById('nombreUsuario');
    const nombreUsuario = document.getElementById('nombreUsuario').value;
    console.log(nombreUsuario)
    

    if(nombreUsuario.length > 25 || nombreUsuario === ''){
        document.getElementById('err1').hidden = false;
        subrayarError(tag);
    } 
    else{
        limpiarSubrayadoError(tag);
        document.getElementById('err1').hidden = true;

        /*La petición a la api se realiza después de comprobar que el nombre no esta vacío, porque
        si no fallaría al enviar un nombre vacío */
        const peticionNombreRepetido = await fetch('/nuevoUsuarioCreacion/esRepetido/' + nombreUsuario);
        const esNombreRepetido = await peticionNombreRepetido.json();

        if(esNombreRepetido === true){
            document.getElementById('err2').hidden = false;
            subrayarError(tag);
        }
        else{
            document.getElementById('err2').hidden = true;
            limpiarSubrayadoError(tag);
        } 
    }   
}
document.getElementById('nombreUsuario').addEventListener('input', validarNombreUsuario);


function validarEmail(){
    const email = document.getElementById('email').value;
    const tag = document.getElementById('email');

    if(email.length > 35){
        document.getElementById('err4').hidden = false;
        subrayarError(tag);
    }
    else{
        document.getElementById('err4').hidden = true;
        limpiarSubrayadoError(tag);
    } 

    if(!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)){
        document.getElementById('err5').hidden = false;
        subrayarError(tag);
    } 
    else{
        document.getElementById('err5').hidden = true;
        limpiarSubrayadoError(tag);
    } 
}
document.getElementById('email').addEventListener('input', validarEmail);

function validarPassword(){
    const password = document.getElementById('password').value;
    const tag = document.getElementById('password');

    if(password.length > 30 || password.length < 14){
        document.getElementById('err6').hidden = false;
        subrayarError(tag);
    } 
    else{
        document.getElementById('err6').hidden = true;
        limpiarSubrayadoError(tag);
    } 

    if(!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\w\s]).*$/.test(password)){
        document.getElementById('err7').hidden = false;
        subrayarError(tag);
    } 
    else{
        document.getElementById('err7').hidden = true;
        limpiarSubrayadoError(tag);
    } 
}

document.getElementById('password').addEventListener('input', validarPassword);

function validarTelefono(){
    const telefono = document.getElementById('telefono').value;
    const tag = document.getElementById('telefono');

    if (!/^(6|7|8|9)\d{8}$/.test(telefono)) {
        document.getElementById('err8').hidden = false;
        subrayarError(tag);
    } 
    else{
        document.getElementById('err8').hidden = true;
        limpiarSubrayadoError(tag);
    } 
}

document.getElementById('telefono').addEventListener('input',validarTelefono);

function validarRol(){
    const tag = document.getElementById('rol');
    const rolSeleccionado = document.getElementById('rol').value;
    console.log(rolSeleccionado)

    if(rolSeleccionado === 'Selecciona una opcion'){
        document.getElementById('err9').hidden = false;
        subrayarError(tag);
    }
    else{
        limpiarSubrayadoError(tag);
    } 
}

document.getElementById('rol').addEventListener('change',validarRol);

document.getElementById('crearNuevoUsuario').addEventListener('click',(e) =>{
    e.preventDefault();
    
    const primerFormulario = document.getElementById('primerFormulario');
    validarNombreUsuario();
    validarEmail();
    validarPassword();
    validarTelefono();
    validarRol();
    /*Llama a todas las validaciones, si alguna es errónea se mostrará el mensaje de error
    y no se ejecutará el submit*/

    const mensajesError = document.querySelector('.mensajeError');
    const mensajesErrorOcultos = true;

    // for(const mensaje of mensajesError){
    //     if(mensaje.hidden === false) mensajesErrorOcultos = false;
    //     break;
    // }

    console.log(mensajesErrorOcultos);

    if(mensajesErrorOcultos) primerFormulario.submit();
});

