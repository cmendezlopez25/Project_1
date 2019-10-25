function validateLogin(){
    let username = document.getElementsByName("username")[0];
    let password = document.getElementsByName("password")[0];

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 404) {
                // User was not found or password was incorrect
                let invalidUser = document.getElementById("invalid-msg");
                invalidUser.setAttribute("class", "invalid-msg-show");
                console.log(invalidUser);
            }
            /// User and password correct!
            else if(xhr.status === 200){
                window.location.replace("home");
            }
        }
    }
    let userDetails = username.value + "\n" + password.value;
    xhr.open("POST", "login", true);
    xhr.send(userDetails);
}