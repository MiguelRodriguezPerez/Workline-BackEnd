"use strict"

const pagActual = parseInt(window.location.pathname.toString().substring(18,window.location.pathname.length));;
const numPags = parseInt(document.getElementById('numPaginas').textContent);

/*Al borrar dinámicamente los botones siguientePag y anteriorPag con thymeleaf, js impide la ejecución de
los dos eventos si no encuentra las etiquetas html. Cada uno de estos if(document.getElementById) 
comprueba si existen en el DOM para decidir si crea el evento*/
if(document.getElementById('anteriorPag')){
    document.getElementById('anteriorPag').addEventListener('click',() => {
        if(pagActual > 0) cambiarPaginaUrl(pagActual - 1);
    });
}

if(document.getElementById('siguientePag')){
    document.getElementById('siguientePag').addEventListener('click',() => {
        if((pagActual + 1) < numPags) cambiarPaginaUrl(pagActual + 1);
    });
};

function cambiarPaginaUrl(numPag){
    window.location.pathname = window.location.pathname.toString().replace(
        '/ofertasDeTrabajo/' + pagActual,
        '/ofertasDeTrabajo/' + numPag
    )
};

const ofertas = document.getElementsByClassName('oferta');
for(const oferta of ofertas){
    oferta.addEventListener('click',()=>{
        window.location = '/ofertasDeTrabajo/verOferta/' + oferta.id;
    });
};


