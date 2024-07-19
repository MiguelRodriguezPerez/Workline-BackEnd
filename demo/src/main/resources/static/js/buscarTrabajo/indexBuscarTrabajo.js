"use strict"

document.getElementById('siguientePag').addEventListener('click',()=>{

    const url = window.location.href.toString();
    
    if(/^http:\/\/localhost:9001\/ofertasDeTrabajo\/\d$/.test(window.location.href)){

        let numPag = parseInt(url.substring(url.length - 2, url.length - 1));

        const siguientePag = existeSiguientePag(null,numPag);
        
        if(siguientePag) window.location = url.replace(url.substring(url.length - 2, url.length - 1), numPag + 1);
    }
});

async function existeSiguientePag(busqueda,pag){
    console.log('aaaaaaaaaaaaaa');
    const resultado = await fetch('/solicitudOfertas/existeSiguientePag/' + busqueda + '/' + pag);
    console.log(resultado);
    return resultado;
}
