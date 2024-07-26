"use strict"

document.getElementById('editar').addEventListener('click',() =>{

    for(const i of document.getElementsByTagName('input')){
        if(i.readOnly === true) i.readOnly = false;
    }

    for(const s of document.getElementsByTagName('select')){
        s.disabled = false;
    }

    document.getElementById('subirCambios').style.visibility = 'visible';

    document.getElementById('editar').textContent = 'Cancelar';
    document.getElementById('editar').id = 'cancelar';
});

document.getElementById('subirCambios').addEventListener('click', (e) =>{
    e.preventDefault();
    const formulario = document.getElementById('formularioEdicion');

    if(validarOferta()) formulario.submit();
});

function validarOferta(){
    const puesto = document.getElementById('puesto').value;
    const descripcion = document.getElementById('descripcion').value;

    if(puesto.length > 30 || puesto === ''){
        document.getElementById('err1').style.display = 'block';
        document.getElementById('puesto').classList.add('inputError');
        return false;
    }
    else{
        document.getElementById('err1').style.display = 'none';
        document.getElementById('puesto').classList.remove('inputError');
    }

    if(descripcion.length > 50){
        document.getElementById('err2').style.display = 'block';
        document.getElementById('descripcion').classList.add('inputError');
        return false;
    } 
    else{
        document.getElementById('err2').style.display = 'block';
        document.getElementById('descripcion').classList.add('inputError');
    }

    if(document.getElementById('ciudad') === ''){
        document.getElementById('err3').style.display = 'block';
        document.getElementById('ciudad').classList.add('inputError');
        return false;
    }
    else{
        document.getElementById('err3').style.display = 'none';
        document.getElementById('ciudad').classList.remove('inputError');
    }

    return true;
}