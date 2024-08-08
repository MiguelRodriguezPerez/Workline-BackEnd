"use strict"

function abrirDesplegableOfertas() {
    document.getElementById("desplegableOfertas").style.width = "100%";
    
}
  
function cerrarDesplegableOfertas() {
    document.getElementById("desplegableOfertas").style.width = "0";
}

document.getElementById('botonDesplegableOfertas').addEventListener('click',abrirDesplegableOfertas);

document.getElementById('cerrarBotonOfertas').addEventListener('click',cerrarDesplegableOfertas);

// document.addEventListener('click',(e)=>{
//   if(e.layerX > 300) closeNav();
// });