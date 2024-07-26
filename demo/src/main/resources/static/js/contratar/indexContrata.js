'use strict'

for(const oferta of document.getElementsByClassName('oferta')){
    oferta.addEventListener('click',redirigir)
}

function redirigir(){
    const url = window.location.toString();
    const numPag = parseInt(url.substring(url.length - 1, url.length));
    window.location.href='/seccionContrata/pagina/' + numPag + '/detallesOferta/' + this.id;
}