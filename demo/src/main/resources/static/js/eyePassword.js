let ojo = document.getElementById('eyeImg');

ojo.addEventListener('click', () => {
    console.log(ojo);
    
    // Verifica solo el final de la URL de la imagen
    if(ojo.src.endsWith('/images/password/show.png')){
        for(const inputPassword of Array.from(document.querySelectorAll('.verificarPassword'))){
            inputPassword.type = 'text';
        }
        ojo.src = '/images/password/notShow.png';
    } else {
        for(const inputPassword of Array.from(document.querySelectorAll('.verificarPassword'))){
            inputPassword.type = 'password';
        }
        ojo.src = '/images/password/show.png';
    }
});