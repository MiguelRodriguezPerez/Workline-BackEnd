"use strict"

function openNav() {
    if(window.innerWidth > 700) document.getElementById("mySidenav").style.width = "300px";
    else document.getElementById("mySidenav").style.width = "100%";
    
  }
  
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}

document.getElementById('buttonNav').addEventListener('click',openNav);

document.getElementById('closebtn').addEventListener('click',closeNav);

document.addEventListener('click',(e)=>{
  if(e.layerX > 300) closeNav();
})