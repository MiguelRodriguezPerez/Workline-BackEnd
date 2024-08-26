"use strict"

function abrirDesplegableMenu(){
    document.getElementById('desplegableMenu').style.width = "100%";
}

function cerrarDesplegableMenu(){
    document.getElementById('desplegableMenu').style.width = "0%";
}

document.getElementById('barrasDesplegableMenu').addEventListener('click',abrirDesplegableMenu);
document.getElementById('cerrarDesplegableMenu').addEventListener('click',cerrarDesplegableMenu);

//Colorear url actual
window.addEventListener('DOMContentLoaded',() =>{
    const pestayas = Array.from(document.querySelectorAll('#pestayas > a'));

    if(window.location.href.toString() === pestayas[0].href) pestayas[0].classList.add('seleccionado');
    else pestayas[0].classList.remove('seleccionado');

    for(let i = 1; i < pestayas.length; i++){
        if(window.location.pathname.toString().includes(obtenerRuta(pestayas[i].href))){
            pestayas[i].classList.add('seleccionado');
        }
        else pestayas[i].classList.remove('seleccionado');
    }
});

function obtenerRuta(ruta){
    ruta = ruta.replace('http://localhost:9001','');
    const resultado = ruta.match(/\/([^\/]*)\//);
    return resultado ? resultado[0] : null;
}

if(document.getElementById('menuTelefono').children.length === 2){
    const menuTelefono = document.getElementById('menuTelefono');
    menuTelefono.style.display = 'grid';
    menuTelefono.style.gridTemplateColumns = '85% 15%';
}