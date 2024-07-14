"use strict"

document.addEventListener("DOMContentLoaded", function() {
    let dropdown = document.querySelector(".dropbtn");
    let dropdownContent = document.getElementById("desplegable");
  
    function toggleDropdown() {
      if (dropdownContent.style.display === "block") {
        dropdownContent.style.display = "none";
      } else {
        dropdownContent.style.display = "block";
      }
    }
  
    function closeDropdown(event) {
      if (!dropdown.contains(event.target) && !dropdownContent.contains(event.target)) {
        dropdownContent.style.display = "none";
      }
    }
  
    dropdown.addEventListener("click", function(event) {
      event.stopPropagation();
      toggleDropdown();
    });
  
    document.addEventListener("click", closeDropdown);
  });
  

