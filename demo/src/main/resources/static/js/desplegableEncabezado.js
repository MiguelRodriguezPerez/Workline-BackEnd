"use strict"

function abrirDesplegableMenu(){
    document.getElementById('desplegableMenu').style.width = "100%";
}

function cerrarDesplegableMenu(){
    document.getElementById('desplegableMenu').style.width = "0%";
}

document.getElementById('barrasDesplegableMenu').addEventListener('click',abrirDesplegableMenu);
document.getElementById('cerrarDesplegableMenu').addEventListener('click',cerrarDesplegableMenu);