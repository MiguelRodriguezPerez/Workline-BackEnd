"use strict"
/*En el css de la edición de estilos, el botón para subir la oferta
tiene por defecto display:none. Para evitar duplicar dicha hoja de estilos,
declaro esta línea */
document.getElementById('subirCambios').style.display = 'block';

document.getElementById('subirCambios').addEventListener('click', (e) =>{
    e.preventDefault();
    const formulario = document.getElementById('formularioEdicion');
    console.log(document.getElementsByTagName('datosOcultos'));

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
        document.getElementById('descripcion').classList.add('descripcionError');
        return false;
    } 
    else{
        document.getElementById('err2').style.display = 'none';
        document.getElementById('descripcion').classList.remove('descripcionError');
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