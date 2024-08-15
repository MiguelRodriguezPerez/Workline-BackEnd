export function mostrarError(fallo,input){
    fallo.style.display = 'block';
    input.classList.add('inputError');
}

export function limpiarError(fallo,input){
    fallo.style.display = 'none';
    input.classList.remove('inputError');
}

export function validarNombreUsuario(nombre) {
    return (nombre.length < 25 && nombre !== '');
}

/*Esta función se encarga de validar si un nombre de usuario ya esta cogido o no envíando una petición
a una api del lado servidor que utiliza un método para averiguar si existe un usuario con dicho nombre*/
export async function esNombreRepetido(nombre) {
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

export function validarLongitudEmail(email) {
    return (email.length < 35);
}

export function validarExpresionEmail(email) {
    return (/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email));
}

export function validarPassword(password) {
    return (password.length < 30 && password.length > 14 
    && /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\w\s]).*$/.test(password));
}

export function validarTelefono(telefono) {
    console.log('AAAAAAAAAAAAAAAAAAAAAAA')
    return (/^(6|7|8|9)\d{8}$/.test(telefono));
}

export function validarCiudad(ciudad) {
    return (ciudad !== '');
}

export function validarRol(rol){
    return (rol === 'ADMIN' || rol === 'CONTRATA' || rol === 'BUSCA')
}