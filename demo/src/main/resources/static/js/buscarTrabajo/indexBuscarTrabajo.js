"use strict"

document.getElementById('siguientePag').addEventListener('click',async ()=>{

    const url = window.location.href.toString();
    if(url.match(/\/ofertasDeTrabajo\/\d$/)){
        let numPag = parseInt(url.substring(url.length - 1, url.length));
        const siguientePag = await existeSiguientePaginaSinCriterios(numPag);
        console.log(siguientePag);
        
        if(siguientePag === true){
            numPag++;
            window.location = '/ofertasDeTrabajo/' + numPag;
        } 
    }
    else {
        let url = window.location.href;
        let numeroActual = parseInt(url.match(/resultadosBusqueda\/(\d+)/)[1]);

        let nuevoNumero = numeroActual + 1;
        let nuevaUrl = url.replace(/resultadosBusqueda\/\d+/, `resultadosBusqueda/${nuevoNumero}`);
        console.log(nuevaUrl);
        window.location.href = nuevaUrl;

    }
});

document.getElementById('anteriorPag').addEventListener('click', async () => {

    const url = window.location.href.toString();
    
    if (url.match(/\/ofertasDeTrabajo\/\d$/)) {
        let numPag = parseInt(url.substring(url.length - 1, url.length));
        const anteriorPag = await existePaginaAnteriorSinCriterios(numPag);
        console.log(anteriorPag);
        
        if (anteriorPag === true && numPag > 0) {
            numPag--;
            window.location = '/ofertasDeTrabajo/' + numPag;
        } 
    } 
    else {
        let url = window.location.href;
        let numeroActual = parseInt(url.match(/resultadosBusqueda\/(\d+)/)[1]);

        if (numeroActual > 0) {
            let nuevoNumero = numeroActual - 1;
            let nuevaUrl = url.replace(/resultadosBusqueda\/\d+/, `resultadosBusqueda/${nuevoNumero}`);
            console.log(nuevaUrl);
            window.location.href = nuevaUrl;
        }
    }
});



async function existeSiguientePaginaSinCriterios(pag){
    const respuesta = await fetch('/solicitudOfertas/existeSiguientePaginaSinCriterios/' + pag);
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



const ofertas = document.getElementsByClassName('oferta');
for(const oferta of ofertas){
    oferta.addEventListener('click',()=>{
        window.location = '/ofertasDeTrabajo/verOferta/' + oferta.id;
    })
}


