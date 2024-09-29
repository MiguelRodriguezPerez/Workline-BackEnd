document.getElementById('generarApiKey').addEventListener('click' ,  async () => {

    const resultado = await fetch('/api/contrata/generarApiKey');

    if(!resultado) alert('Maaal');
    else location.reload();
})