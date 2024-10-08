

const cuentaAtras = () => {
    const h3 = document.getElementById('tiempo');
    const segundos = 20;

    const intervalo = setInterval(() => {
        h3.textContent = ;
        segundos--;

        // Si llega a 0, detiene la cuenta atrás
        if (contador < 0) {
            clearInterval(intervalo);
        }
    }, 1000)
    intervalo();
}


document.getElementById('copy').addEventListener('click', () => {
  navigator.clipboard.writeText(document.getElementById('key').textContent);
})