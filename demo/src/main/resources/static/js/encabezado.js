document.querySelector('#perfil img').addEventListener('click',()=>{
    window.location = '/sesion/signin';
});

if(document.querySelector('#perfil a:last-of-type').innerHTML === ''){
    document.querySelector('#perfil a:last-of-type').display = 'none';
    document.querySelector('#perfil').style.gridTemplateColumns = 'auto 20%'
}