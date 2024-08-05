"use strict"

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
        const parametros = new URLSearchParams(window.location.search);
        let parametrosConsulta = {};
        let numPag = window.location.pathname.toString().substring(window.location.pathname.toString().length - 1,);

        parametros.forEach((valor, clave) => {
            if (valor && valor !== 'placeholder') {
                parametrosConsulta[clave] = valor;
            }
        });

        parametrosConsulta['pagina'] = numPag;
        console.log(parametrosConsulta);
        const busquedaObjeto = new BusquedaOferta(parametrosConsulta)
        busquedaObjeto.numPag = numPag;
        const siguientePag = await existeSiguientePagConCriterios(busquedaObjeto);

        if(siguientePag === true){
            alert('AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA');
        } 
    }
});

async function existeSiguientePagConCriterios(busqueda){ 
    busqueda = JSON.stringify(busqueda);
    console.log(busqueda)
    
    const respuesta = await fetch('/solicitudOfertas/existeSiguientePaginaConCriterios', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(busqueda)
    });
    

    if(!respuesta.ok){ throw new Error('Error en la api ' + respuesta)}

    const resultado = await respuesta.json();
    return resultado;
    
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
