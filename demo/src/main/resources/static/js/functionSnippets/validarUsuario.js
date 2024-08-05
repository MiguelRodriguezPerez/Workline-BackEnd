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

export async function esNombreRepetido(nombre) {
    const peticionNombreRepetido = await fetch('/nuevoUsuarioCreacion/esRepetido/' + nombre);
    const esNombreRepetido = await peticionNombreRepetido.json();
    console.log(esNombreRepetido);
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
    return (/^(6|7|8|9)\d{8}$/.test(telefono));
}

export function validarCiudad(ciudad) {
    return (ciudad !== '');
}

export function validarRol(rol){
    return (rol === 'ADMIN' || rol === 'CONTRATA' || rol === 'BUSCA')
}