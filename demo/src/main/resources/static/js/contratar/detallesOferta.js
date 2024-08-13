'use strict'
const numPag = document.getElementById('numPag').textContent;
const idOferta = document.getElementById('ofertaId').textContent;

for(const can of document.querySelectorAll('.candidato')){
    can.addEventListener('click', () =>{
        window.location.href = '/seccionContrata/pagina/' + numPag + '/detallesOferta/' + idOferta + '/verCandidato/' + can.id;
    })
};