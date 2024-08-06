"use strict"

//Este eventListener gestiona que ocurre cuando haces click en siguiente página
document.getElementById('siguientePag').addEventListener('click',async ()=>{

    const url = window.location.href.toString();
    /*Si la url no contiene criterios de búsqueda se ejecutara esta búsqueda. NO TOCAR*/
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
        /*Este código se ejecuta cada vez que se avanza a la siguiente página en una
        búsqueda con criterios.
        Su objetivo es averiguar si existe una siguiente página de ofertas 
        con los criterios de búsqueda*/

        const parametros = new URLSearchParams(window.location.search);
        // Esta línea obtiene los parámetros de consulta de la ruta get y los convierte en un array

        let numPag = window.location.pathname.toString().substring(window.location.pathname.toString().length - 1,);
        /*Obtiene el número de la página de la url basándose en el último carácter 
        de la url que es el número de la página. Esta mal planteado, pero ignoralo por el momento*/
        
        const busquedaObjeto = new BusquedaOferta(parametros);
        /*Se crea un objeto busquedaObjeto usando los parametros de la ruta url. El constructor de BusquedaOferta
        esta diseñado para que si no le llega uno de sus paramteros este sea null*/
        busquedaObjeto.numPag = numPag;
        /*Se fija el numero de página del objeto busquedaOferta*/

        const siguientePag = await existeSiguientePagConCriterios(busquedaObjeto);
        /*Guarda un valor booleano que define si existe una siguiente página o no, 
        que debería devolver la api*/
        
        if(siguientePag){
            /*Si siguientePag es true avanzará a la siguiente página (Código de chatgpt) */
            window.location.href =  window.location.href.replace(/\/(\d+)\?/, (match, p1) => `/${parseInt(p1) + 1}?`);
        }
    }
});

//Esta función realiza la petición api para averiguar si existe la siguiente página
async function existeSiguientePagConCriterios(busqueda){ 
    /*busqueda es el objeto busquedaOferta*/
    busqueda = JSON.stringify(busqueda);
    //Se convierte a JSON para enviarlo a la api
    console.log(busqueda);
    //Este console.log demuestra que el json que envía coincide con el objeto busquedaOferta
    

    const respuesta = await fetch('http://localhost:9001/solicitudOfertas/existeSiguientePaginaConCriterios', {
        method: 'POST',
        body: busqueda
    });
    //Esta es la petición post que no funciona
    

    if(!respuesta.ok){ throw new Error('Error en la api ' + respuesta)}

    const resultado = await respuesta.json();
    return resultado;
    
}

// Este el el objeto BusquedaOferta. Si no recibe un campo se convertirá a null
class BusquedaOferta {
    constructor({puestoB, sectorB, tipoContratoB, ciudadB, requisitos, salarioAnual, modalidadB, pagina: numPag} = {}) {
        this.puestoB = puestoB !== undefined ? puestoB : null;
        this.sectorB = sectorB !== undefined ? sectorB : null;
        this.tipoContratoB = tipoContratoB !== undefined ? tipoContratoB : null;
        this.ciudadB = ciudadB !== undefined ? ciudadB : null;
        this.requisitos = requisitos !== undefined ? requisitos : null;
        this.salarioAnual = salarioAnual !== undefined ? salarioAnual : null;
        this.modalidadB = modalidadB !== undefined ? modalidadB : null;
        this.numPag = numPag !== undefined ? numPag : null;
    }
}

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
