document.addEventListener("DOMContentLoaded", (event)=>{

    setTimeout(() =>{
        document.querySelector("#load-iframe-map").innerHTML = `
        <iframe class="contact__iframe" frameborder="0" scrolling="no" margingheight="0" marginwidth="0" loading="lazy" referrerpolicy="no-referrer-when-downgrade" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d25153.367130381037!2d-1.1506253323408264!3d37.99647424128594!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd63819b8c23bf0d%3A0xff37fcea4b9ed0e4!2sSercotel%20JC1%20Murcia!5e0!3m2!1ses!2ses!4v1749724799785!5m2!1ses!2ses"></iframe>
        `;
    }, 500);

});