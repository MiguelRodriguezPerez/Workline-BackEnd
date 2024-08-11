'use strict'

for(const oferta of document.getElementsByClassName('oferta')){
    oferta.addEventListener('click',redirigir)
}

function redirigir(){
    const numPag = parseInt(document.getElementById('numPag').textContent);
    window.location.href='/seccionContrata/pagina/' + numPag + '/detallesOferta/' + this.id;
}