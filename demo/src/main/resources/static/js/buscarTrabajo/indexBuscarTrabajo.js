"use strict"

document.getElementById('siguientePag').addEventListener('click',async ()=>{

    const url = window.location.href.toString();
    
    if(url.match(/\/ofertasDeTrabajo\/\d$/)){

        let numPag = parseInt(url.substring(url.length - 1, url.length));
        console.log(numPag);

        const siguientePag = await existeSiguientePag(null,numPag);
        console.log(siguientePag);
        
        if(siguientePag === true){
            numPag++;
            window.location = '/ofertasDeTrabajo/' + numPag;
        } 
        
    }
});

async function existeSiguientePag(busqueda,pag){
    const respuesta = await fetch('/solicitudOfertas/existeSiguientePagina/' + busqueda + '/' + pag);
    if (!respuesta.ok) {
        console.error("Ha ocurrido un error en el resultado de la api");
        return false;
    }
    const resultado = await respuesta.json(); 
    console.log(resultado);
    return resultado; 
}

document.getElementById('anteriorPag').addEventListener('click',()=>{

    let url = window.location.href.toString();
    url = url.substring(21,url.length);
    let numPag = parseInt(url.substring(url.length - 1, url.length));
    console.log(numPag)

    if(numPag > 0){
        numPag--;
        url = url.substring(0,url.length - 1);
        console.log(url);
        window.location.href = url + numPag;
    }
});
