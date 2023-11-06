let btn = document.querySelector(".btn");
let IDfocus = document.getElementById("id");
let ErrorID = document.getElementById("Wid");
let password = document.getElementById("pasw");
let Errorpaw = document.getElementById("Wpasw");
let Erroridpaw = document.getElementById("WidPasw");


btn.onclick = () => {
    event.preventDefault();
    if(IDfocus.value.length === 0){
        ErrorID.style.display = 'block';
    }

    if(password.value.length === 0){
        Errorpaw.style.display = 'block';
    }

    if(IDfocus.value.length === 0 && password.value.length === 0){
        Erroridpaw.style.display = 'block';
    }
};

//아이디값 한개라도 들어오면 지워지게
IDfocus.addEventListener('input', function() {
    if (IDfocus.value.length > 0) {
        ErrorID.style.display = 'none';
        if (password.value.length > 0 && IDfocus.value.length > 0) {
            Erroridpaw.style.display = 'none';
        }
    }
});

password.addEventListener('input', function() {
    if (password.value.length > 0) {
        Errorpaw.style.display = 'none';
        if (password.value.length > 0 && IDfocus.value.length > 0) {
            Erroridpaw.style.display = 'none';
        }
    }
});

