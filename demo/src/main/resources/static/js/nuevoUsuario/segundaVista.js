"use strict"

document.getElementById('subirConocimiento').addEventListener('click',(e) =>{
    e.preventDefault();
    if(validarConocimiento()) document.getElementById('formNuevoBuscaConocimiento').submit();
});




function validarConocimiento(){
    let esValido = true;

    const inputsConocimiento = Array.from(document.getElementsByClassName('conocimientoInput'));
    console.log(inputsConocimiento);

    const fallosConocimiento = document.querySelectorAll('#formNuevoBuscaConocimiento .mensajeError');
    console.log(fallosConocimiento);
    const valoresConocimiento = [];

    for(const e of fallosConocimiento){
        e.setAttribute('hidden','');
        e.classList.remove('inputError');
    }

    //Array: Titulo,Centro,Fecha Inicio,Fecha fin
    for(const i of inputsConocimiento){
        valoresConocimiento.push(i.value);
    }

    console.log(valoresConocimiento);

    if(!validar40caracteresYVacio(valoresConocimiento[0])){
            inputsConocimiento[0].classList.add('inputError');
            fallosConocimiento[0].removeAttribute('hidden');
            esValido = false;
    }

    if(!validar40caracteresYVacio(valoresConocimiento[1])){
        inputsConocimiento[1].classList.add('inputError');
        fallosConocimiento[1].removeAttribute('hidden');
        esValido = false;
    }

    if(!validarFecha(valoresConocimiento[2])){
        inputsConocimiento[2].classList.add('inputError');
        fallosConocimiento[2].removeAttribute('hidden');
        esValido = false;
    }

    if(!validarFecha(valoresConocimiento[3])){
        inputsConocimiento[3].classList.add('inputError');
        fallosConocimiento[3].removeAttribute('hidden');
        esValido = false;
    }

    if(!fechasCoherentes(valoresConocimiento[2],valoresConocimiento[3])){
        inputsConocimiento[2].classList.add('inputError');
        inputsConocimiento[3].classList.add('inputError');
        fallosConocimiento[3].removeAttribute('hidden');
        esValido = false;
    }

    return esValido;
}

document.getElementById('subirExperiencia').addEventListener('click',(e) =>{
    e.preventDefault();
    if(validarExperiencia()) document.getElementById('formNuevoBuscaExperiencia').submit();
});


function validarExperiencia(){

    let resultado = true;
    const inputsExperiencia = Array.from(document.getElementsByClassName('experienciaInput'));
    const fallosExperiencia = document.querySelectorAll('#formNuevoBuscaExperiencia .mensajeError');
    const valoresExperiencia = [];
    console.log(fallosExperiencia)

    for(const e of fallosExperiencia){
        e.setAttribute('hidden','');
        e.classList.remove('inputError');
    }

    //Array: Puesto,Empresa,Fecha Inicio, Fecha fin
    for(const i of inputsExperiencia){
        valoresExperiencia.push(i.value);
    }

    if(!validar40caracteresYVacio(valoresExperiencia[0])){
        inputsExperiencia[0].classList.add('inputError');
        fallosExperiencia[0].removeAttribute('hidden');
        resultado = false;
    }
    if(!validar40caracteresYVacio(valoresExperiencia[1])){
        inputsExperiencia[1].classList.add('inputError');
        fallosExperiencia[1].removeAttribute('hidden');
        resultado = false;
    }
    if(!validarFecha(valoresExperiencia[2])){
        inputsExperiencia[2].classList.add('inputError');
        fallosExperiencia[2].removeAttribute('hidden');
        resultado = false;
    }
    if(!validarFecha(valoresExperiencia[3])){
        inputsExperiencia[3].classList.add('inputError');
        fallosExperiencia[3].removeAttribute('hidden');
        resultado = false;
    }
    if(!fechasCoherentes(valoresExperiencia[2],valoresExperiencia[3])){
        inputsExperiencia[2].classList.add('inputError');
        inputsExperiencia[3].classList.add('inputError');
        fallosExperiencia[4].removeAttribute('hidden');
        resultado = false;
    } 

    return resultado;

}

function validar40caracteresYVacio(texto){
    if(texto.length > 40
        ||!/^(?!\s*$).+/.test(texto)//Comprueba que no sean solo espacios en blanco
        || texto === '') return false;
    else return true;
}

function validarFecha(texto){
    if(!/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/.test(texto)
        || texto === '') return false;
    else return true;
}

function fechasCoherentes(fInicio, fFin){
    const fechaInicio = new Date(fInicio);
    const fechaFin = new Date(fFin);

    if(fechaFin < fechaInicio) return false;
    else return true;
}