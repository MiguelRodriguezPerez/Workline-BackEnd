export function validarPuesto(input,p){
    if(input.value.length > 30 || input.value.length < 1){
        input.classList.add('descripcionError');
        p.textContent = 'El puesto no puede tener más de 30 carácteres ni estar vacío';
        return false;
    }
    else{
        input.classList.remove('descripcionError');
        p.textContent = '';
        return true;
    }
}

export function valMenorQue20(input,p){
    if(input.value.length > 20 || input.value.length < 1){
        input.classList.add('descripcionError');
        p.textContent = 'Este campo no puede tener más de 20 carácteres ni estar vacío';
        return false;
    }
    else{
        input.classList.remove('descripcionError');
        p.textContent = '';
        return true;
    }
}

export function validarDescripcion(input,p){
    if(input.value.length > 80 || input.value.length < 1){
        input.classList.add('descripcionError');
        p.textContent = 'La descripción no puede tener más de 80 carácteres ni estar vacío';
        return false;
    }
    else{
        input.classList.remove('descripcionError');
        p.textContent = '';
        return true;
    }
}

export function validarSelect(select,p){
    if(select.value === 'default'){
        select.classList.add('descripcionError');
        p.textContent = 'Seleccione una opción';
        return false;
    }
    else{
        select.classList.remove('descripcionError');
        p.textContent = '';
        return true;
    }
}
