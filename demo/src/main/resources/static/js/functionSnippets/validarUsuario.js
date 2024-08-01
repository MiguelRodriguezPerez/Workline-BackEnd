export function subrayarError(arg){
    arg.classList.add('inputError');
}

export function limpiarSubrayadoError(arg){
    arg.classList.remove('inputError');
}

export async function validarNombreUsuario(inputTag,error1,error2){
    const valorInput = inputTag.value;
    
    if(valorInput > 25 || valorInput === ''){
        inputTag.classList.add('inputError');
        error1.style.display = 'block';
        return false;
    } 
    /*La petición a la api se realiza después de comprobar que el nombre no esta vacío, porque
    si no fallaría al enviar un nombre vacío */
    else{
        inputTag.classList.remove('inputError');
        error1.style.display = 'none';

        const peticionNombreRepetido = await fetch('/nuevoUsuarioCreacion/esRepetido/' + valorInput);
        const esNombreRepetido = await peticionNombreRepetido.json();
        console.log(esNombreRepetido);

        if(esNombreRepetido === true){
            inputTag.classList.add('inputError');
            error2.style.display = 'block';
            return false;
        } 
        else{
            
            inputTag.classList.remove('inputError');
            error2.style.display = 'none';
            return true;
        } 
    }

}

export function validarEmail(inputTag,error,error2){   
    if(inputTag.value.length > 35){
        inputTag.classList.add('inputError');
        error.style.display = 'block';
        return false;
    } 
    else if(!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(inputTag.value)){
        inputTag.classList.add('inputError');
        error2.style.display = 'block';
        return false;
    }
    else{
        inputTag.classList.remove('inputError');
        error.style.display = 'none';
        error2.style.display = 'none';
        return true;
    }
}

export function validarPassword(inputTag,error){
    if(inputTag.value.length > 30 || inputTag.value.length < 14 || !/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\w\s]).*$/.test(inputTag.value)){
        inputTag.classList.add('inputError');
        error.style.display = 'block';
        return false;
    }
    else{
        inputTag.classList.remove('inputError');
        error.style.display = 'none';
        return true;
    }
}
export function validarTelefono(inputTag,error){
    if (!/^(6|7|8|9)\d{8}$/.test(inputTag)){
        inputTag.classList.add('inputError');
        error.style.display = 'block';
        return false;
    }
    else{
        inputTag.classList.remove('inputError');
        error.style.display = 'none';
        return true;
    }
}


    


