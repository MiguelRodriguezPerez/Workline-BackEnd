'use strict'

// export function validarNombreUsuario(nombre) {
//     return (nombre.length < 25 && nombre !== '');
// }

export async function validarNombreUsuario(input, pFallo) {
    const nombre = input.value;

    switch(true) {
        case (nombre.length > 25):
            pFallo.textContent = 'El nombre no puede tener más de 25 caracteres';
            input.classList.add('inputError');
            return false;
        case (nombre.length === 0): // Aquí estaba la condición mal puesta, se compara con 0 en lugar de ''
            pFallo.textContent = 'El nombre de usuario no puede estar vacío';
            input.classList.add('inputError');
            return false;
        case (await esNombreRepetido(nombre)): // Usar await para esperar a que se resuelva la promesa
            pFallo.textContent = 'Ya existe un usuario con este nombre';
            input.classList.add('inputError');
            return false;
        default:
            pFallo.textContent = '';
            input.classList.remove('inputError');
            return true;
    }
}


/*Esta función se encarga de validar si un nombre de usuario ya esta cogido o no envíando una petición
a una api del lado servidor que utiliza un método para averiguar si existe un usuario con dicho nombre*/
async function esNombreRepetido(nombre) {
    console.log(nombre)
    if(nombre === '') return false;
    /*Los nombres vacíos devuelven false para evitar que la api falle por recibir un valor nulo*/
    const peticionNombreRepetido = await fetch('/nuevoUsuarioCreacion/esRepetido/' + nombre);
    /*Supuestamente aquí debería ejecutarse la petición api y almacenarse su resultado en una variable
    Además esta ruta esta configurada en el SecurityFilterChain para que admita peticiones de usuarios
    sin loguearse*/
    const esNombreRepetido = await peticionNombreRepetido.json();
    return esNombreRepetido;
}

export function validarEmail(input, pFallo){
    const email = input.value;

    switch(true){
        case(email.length > 35):
            pFallo.textContent = 'El email no puede tener más de 35 carácteres';
            input.classList.add('inputError');
            return false;

        case(email === ''):
            pFallo.textContent = 'El email no puede estar vacío';
            input.classList.add('inputError');
            return false;

        case (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)):
            pFallo.textContent = 'Email inválido';
            input.classList.add('inputError');
            return false;
        
        default:
            pFallo.textContent = '';
            input.classList.remove('inputError');
            return true;
    }
}

export function validarPassword(input, pFallo) {
    const password = input.value;

    switch (true) {
        case (password.length > 30):
            pFallo.textContent = 'La contraseña no puede tener más de 30 caracteres';
            input.classList.add('inputError');
            return false;

        case (password.length < 15):
            pFallo.textContent = 'La contraseña debe tener al menos 15 caracteres';
            input.classList.add('inputError');
            return false;

        case (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\w\s]).*$/.test(password)):
            pFallo.textContent = 'La contraseña debe contener al menos una letra minúscula, una letra mayúscula, un número y un carácter especial';
            input.classList.add('inputError');
            return false;

        default:
            pFallo.textContent = '';
            input.classList.remove('inputError');
            return true;
    }
}


export function validarTelefono(input,pFallo) {
    if(!/^(6|7|8|9)\d{8}$/.test(input.value)){
        pFallo.textContent = 'El teléfono debe tener formato español';
        input.classList.add('inputError');
        return false;
    }
    else {
        pFallo.textContent = '';
        input.classList.remove('inputError');
        return true;
    }
}

export function validarCiudad(input, pFallo) {
    const ciudad = input.value;

    if (ciudad === '') {
        pFallo.textContent = 'La ciudad no puede estar vacía';
        input.classList.add('inputError');
        return false;
    } 
    else {
        pFallo.textContent = '';
        input.classList.remove('inputError');
        return true;
    }
}


export function validarRol(input,pFallo){
    if (rol.value !== 'CONTRATA' && rol.value !== 'BUSCA'){
        pFallo.textContent = 'Selecciona un rol';
        input.classList.add('inputError');
        return false;
    }
    else {
        pFallo.textContent = '';
        input.classList.remove('inputError');
        return true;
    }
}