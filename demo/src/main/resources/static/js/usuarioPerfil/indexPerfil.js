"use strict"

const ofertas = document.getElementsByClassName('oferta');
for(const oferta of ofertas){
    oferta.addEventListener('click',() => {
        window.location = '/ofertasDeTrabajo/verOferta/' + oferta.id;
    });
};