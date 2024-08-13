import * as valFunciones from '/js/functionSnippets/validarOferta.js'

"use strict"
/*En el css de la edición de estilos, el botón para subir la oferta
tiene por defecto display:none. Para evitar duplicar dicha hoja de estilos,
declaro esta línea */
document.getElementById('subirCambios').style.display = 'block';

const validarInputs = Array.from(document.querySelectorAll('.validarCampo'));
const mensajesError = Array.from(document.querySelectorAll('.mensajeError'));
const arrayValidaciones = [];
const formulario = document.getElementById('formularioEdicion');

console.log(validarInputs)


const valPuesto = () => {
    valFunciones.validarPuesto(validarInputs[0],mensajesError[0]);
}
arrayValidaciones.push(valPuesto);
validarInputs[0].addEventListener('input',valPuesto);

const valSector = () => {
    valFunciones.valMenorQue20(validarInputs[1],mensajesError[1]);
}
arrayValidaciones.push(valSector);
validarInputs[1].addEventListener('input',valSector);


const valModTrabajo = () => {
    valFunciones.validarSelect(validarInputs[2],mensajesError[2]);
}
arrayValidaciones.push(valModTrabajo);
validarInputs[2].addEventListener('change',valModTrabajo);


const valCiudad = () =>{
    valFunciones.valMenorQue20(validarInputs[3],mensajesError[3]);
}
arrayValidaciones.push(valCiudad);
validarInputs[3].addEventListener('change',valCiudad);

const valTipoContrato = () =>{
    valFunciones.validarSelect(validarInputs[4],mensajesError[4]);
}
arrayValidaciones.push(valTipoContrato);
validarInputs[4].addEventListener('change',valTipoContrato);

document.getElementById('subirCambios').addEventListener('click', (e) => {
    e.preventDefault();

    let confirmarValidaciones = true;
    for(const val of arrayValidaciones){
        confirmarValidaciones = val();
    }

    if(!mensajesError.some(p => p.textContent.trim() !== '')) formulario.submit();
});


