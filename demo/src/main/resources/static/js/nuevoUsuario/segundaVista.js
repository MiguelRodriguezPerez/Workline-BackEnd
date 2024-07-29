"use strict"

document.getElementById('subirConocimiento').addEventListener('click',validarConocimiento);

function validarConocimiento(e){

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
    

    // if(valoresInput[2].length > 40
    //     ||!/^(?!\s*$).+/.test(valoresInput[2])//Comprueba que no sean solo espacios en blanco
    //     || valoresInput[2] === ''){
    //         document.getElementById('tituloFallo').removeAttribute('hidden');
    //         // document.getElementById('tituloForm').className='mensajeErrorConocimiento';
    //         return false;
    // }

    // if(valoresInput[3].length > 40
    //     ||!/^(?!\s*$).+/.test(valoresInput[3])//Comprueba que no sean solo espacios en blanco
    //     || valoresInput[3] === ''){
    //     document.getElementById('centroFallo').removeAttribute('hidden');
    //     // document.getElementById('centroForm').className='mensajeErrorConocimiento';
    //     return false;
    // }

    // if(!/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/.test(valoresInput[4])
    //     || valoresInput[4] === null){
    //     document.getElementById('fechaInicioConocimientoFallo').removeAttribute('hidden');
    //     document.getElementById('fechaInicioForm').className='mensajeErrorConocimiento';
    //     return false;
    // }

    // if(!/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/.test(valoresInput[5])
    //     || valoresInput[5] === null){
    //     document.getElementById('fechaInicioConocimientoFallo').removeAttribute('hidden');
    //     document.getElementById('fechaInicioForm').className='mensajeErrorConocimiento';
    //     return false;
    // }

    // const fechaInicio = new Date(document.getElementById('fechaInicioForm').value);
    // const fechaFin = new Date(document.getElementById('fechaFinForm').value);

    // if(fechaFin<fechaInicio){
    //     document.getElementById('fechaFinConocimientoFalloDos').removeAttribute('hidden');
    //     document.getElementById('fechaInicioForm').className='mensajeErrorConocimiento';
    //     document.getElementById('fechaFinForm').className='mensajeErrorConocimiento';
    //     return false;
    // }

    // return true;

}