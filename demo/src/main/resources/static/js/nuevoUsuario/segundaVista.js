"use strict"

document.getElementById('subirConocimiento').addEventListener('click',validarConocimiento);

function validarConocimiento(e){
    let esValido = true;

    e.preventDefault();
    //Array: ???,"",Titulo,Centro,Fecha Inicio,Fecha fin
    const inputsFormulario = Array.from(document.querySelectorAll('#formNuevoBuscaConocimiento input'));
    inputsFormulario.splice(0,2);
    console.log(inputsFormulario);

    const fallosFormulario = document.querySelectorAll('#formNuevoBuscaConocimiento .mensajeError');
    console.log(fallosFormulario);
    const valoresInput = [];

    for(const i of inputsFormulario){
        valoresInput.push(i.value);
    }

    console.log(valoresInput);

    if(!validar40caracteresYVacio(valoresInput[0])){
            fallosFormulario[0].removeAttribute('hidden');

            // document.getElementById('tituloForm').className='mensajeErrorConocimiento';
            esValido = false;
    }

    if(!validar40caracteresYVacio(valoresInput[1])){
        fallosFormulario[1].removeAttribute('hidden');
        // document.getElementById('centroForm').className='mensajeErrorConocimiento';
        esValido = false;
    }

    if(!validarFecha(valoresInput[2])){
            fallosFormulario[2].removeAttribute('hidden');
            // document.getElementById('fechaInicioForm').className='mensajeErrorConocimiento';
            esValido = false;
    }

    if(!validarFecha(valoresInput[3])){
        fallosFormulario[3].removeAttribute('hidden');
            // document.getElementById('fechaInicioForm').className='mensajeErrorConocimiento';
            esValido = false;
    }

    if(!fechasCoherentes(valoresInput[2],valoresInput[3])){
        esValido = false;
    }

    return esValido;
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

function fechasCoherentes(texto1, texto2){
    const fechaInicio = new Date(texto1);
    const fechaFin = new Date(texto2);

    if(fechaFin<fechaInicio){
        fallosFormulario[5].removeAttribute('hidden');
        // document.getElementById('fechaInicioForm').className='mensajeErrorConocimiento';
        // document.getElementById('fechaFinForm').className='mensajeErrorConocimiento';
        return false;
    }
    else return true;
}