document.addEventListener("DOMContentLoaded", (event) => {

    // Seleccionar los elementos principales

    let mobile_btn = document.querySelector(".navbar__mobile-btn");
    let mobile__menu = document.querySelector(".menu-mobile");

    const showHiddenMenu = () => {
        let show = document.querySelector(".menu-mobile--show");
        
        if(show){
            mobile__menu.classList.remove("menu-mobile--show");
        }else{
            mobile__menu.classList.add("menu-mobile--show");
        }

    };

    mobile_btn.addEventListener("click", showHiddenMenu);

    window.addEventListener("resize", () => {
        let window_width = document.body.clientWidth; 

        if(window_width >= 1000){
            mobile__menu.classList.remove("menu-mobile--show");
        }

    })

    let btn_close = document.querySelector(".menu-mobile__close");

    btn_close.addEventListener("click", showHiddenMenu);

    // Desplegar submenus
    let menu_item = document.querySelectorAll(".menu-mobile__item");

    menu_item.forEach(item => {
        
        item.addEventListener("click", (event) =>{
            let submenu = item.lastElementChild;
            
            if(submenu.className === "menu-mobile__submenu-mobile"){
                if(submenu.style.display === "block"){
                    submenu.style.display = "none";
                }else{
                    submenu.style.display = "block";
                }
            }
        });

    });

});