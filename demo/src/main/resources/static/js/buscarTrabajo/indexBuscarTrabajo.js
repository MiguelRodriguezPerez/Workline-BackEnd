"use strict"

/* Set the width of the side navigation to 250px */
function openNav() {
    if(window.innerWidth > 700) document.getElementById("mySidenav").style.width = "300px";
    else document.getElementById("mySidenav").style.width = "100%";
    
  }
  
/* Set the width of the side navigation to 0 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}

document.getElementById('buttonNav').addEventListener('click',openNav);

document.addEventListener('click',(e)=>{
  if(e.layerX > 300) closeNav();
})