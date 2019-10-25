$(document).ready(function(){
    setupNavlink();
});

function setupNavlink(){
    let body = document.getElementsByTagName("body")[0];
    let nav = document.createElement("nav");
    nav.setAttribute("class", "site-header sticky-top py-1");
    nav.setAttribute("style", "background-color: black");
    nav.innerHTML = 
    //body.innerHTML =
    //`<nav class="site-header sticky-top py-1" style="background-color: black">
        `<div class="container d-flex flex-column flex-md-row justify-content-between">
            <a class="py-2" href="home">
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="d-block mx-auto" role="img" viewBox="0 0 24 24" focusable="false"><title>Product</title><circle cx="12" cy="12" r="10"></circle><path d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94"></path></svg>
            </a>
            <a class="nav-link" href="home">HOME</a>
            <a class="nav-link" href="reimb_form.html">CREATE REIMBURSEMENT</a>
            <div class="text-right">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">ACCOUNT</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="logout">LOGOUT</a>
                </div>
            </div>
        </div>
    `;//</nav>;
    body.prepend(nav);
}