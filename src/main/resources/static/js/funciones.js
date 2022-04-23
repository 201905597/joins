let div;
let divShowPsic;
let psicBorrar;
let userId;
let psicNuevo;
let withPsic;
let divShowCentros;
let withCentro;
let newName;
let newPwd;

// POST REQUEST
async function insertarUsuario(){

    event.preventDefault();
    newName = document.getElementById("name").value;
    newName = newName.toString();
    newPwd = document.getElementById("pwd").value;
    newPwd = newPwd.toString();
    console.log(newName + newPwd) + newName;
    //console.log(newName);

    if (newName == "" || newPwd == ""){
        alert("Por favor, completa todos los campos");
    }else if (newPwd.length < 8){
        alert("La contraseña debe ser de 8 caracteres");
    }else{
        const dataObj = {
            "userName" : newName,
             "userPwd" : newPwd,
             "idPsic" : 0
        };

        let res = await fetch("/api/v1/usuarios",{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dataObj)
        });

        console.log(res);
        console.log(res.status);

        if (res.status == 201){
            alert("Todo ha ido bien :)");
        }else{
            alert("¡Vaya! Parece que algo ha ido mal :(");
        }
    }


}

// GET REQUEST - USER BY ID
async function getUserById(userId){

    let api = "/api/v1/usuarios/" + userId;

    let res = await fetch(api,{
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
    }});

    console.log("res de getuserbyid:");
    console.log(res);

    let data = null;
    if (res.status == 200){
        data = await res.json();
        console.log(data);
        /*let user = data[0];
        let userName = user["userName"];
        let userPwd = user["userPwd"];
        let idPsic = user["idPsic"];
        console.log(idPsic);*/
    }else{
        alert("¡Vaya! No se ha podido resolver tu petición");
    }
    return data;
}

// PUT REQUEST -> CAMBIAR EL PSICOLOGO DE UN USUARIO
async function updateUsuario(){
    //Evito que recargue la página
    //event.preventDefault();

    userId = document.getElementById("userIdChange").value;
    userId = userId.toString();

    psicNuevo = document.getElementById("psicIdChange").value;
    psicNuevo = psicNuevo.toString();

    let userInfo = await getUserById(userId);

    console.log("userInfo content");
    console.log(userInfo);

    if (userInfo != null){
        //let user = userInfo[0];
        //console.log(user);
        let userName = userInfo["userName"];
        let userPwd = userInfo["userPwd"];
        let idPsic = userInfo["idPsic"];

        const dataObj = {
                    "id" : userId,
                    "userName" : userName,
                    "userPwd" : userPwd,
                    "idPsic" : psicNuevo
                };

            let res = await fetch("/api/v1/usuarios/10005",{
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dataObj)
            });

            console.log(res);
            showUsers();
    }else{
        alert("¡Vaya! No se ha podido resolver tu petición");
    }

    /*const dataObj = {
        "id" : userId,
        "userName" : "Pepe",
        "userPwd" : "password5",
        "idPsic" : 20001
    };*/


}

// DELETE REQUEST
async function deletePsicologo(){
    //Evito que recargue la página
    //event.preventDefault();

    psicBorrar = document.getElementById("psicBorrar").value;
    psicBorrar = psicBorrar.toString();
    console.log(psicBorrar);
    let api = "/api/v1/psicologos/" + psicBorrar;

    let res = await fetch(api,{
        method: 'DELETE',
    });
    console.log(res);

    if (res.status == 204){
        console.log(res);
        showPsicologos();
    }else{
        alert("¡Vaya! No se ha podido resolver tu petición");
    }


}

// GET REQUEST - USUARIOS
async function showUsers(){
    //Evito que recargue la página
        //event.preventDefault();

        let res = await fetch("/api/v1/usuarios",{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
        }});

        //console.log(res); //ok

        if (res.status == 200){
            const data = await res.json();
            //console.log(data); //ok
            //let user1 = data[0]; //ok
            div = document.getElementById("mostrar");
            let content = "";
            for (let i = 0; i<data.length; i++){
                let user = data[i];
                let id = user["id"];
                let userName = user["userName"];
                let userPwd = user["userPwd"];
                let idPsic = user["idPsic"];
                content = content + '<div class="card"><div class="card-body"><h4 class="card-title">' + userName + ', ' + id + '</h4><p class="card-text">Psicólogo: ' + idPsic + '</p></div></div><br>';
            }

            div.innerHTML = content;
        }else{
            alert("¡Vaya! No se ha podido resolver tu petición");
        }
}

// GET REQUEST - PSICOLOGOS
async function showPsicologos(){

        let res = await fetch("/api/v1/psicologos",{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
        }});

        //console.log(res); //ok

        if (res.status == 200){
            const data = await res.json();
            //console.log(data); //ok
            //let user1 = data[0]; //ok
            divShowPsic = document.getElementById("mostrarPsic");
            let content = "";
            for (let i = 0; i<data.length; i++){
                let user = data[i];
                let id = user["id"];
                let psicName = user["psicName"];
                let psicPwd = user["psicPwd"];
                let employerId = user["employerId"];
                content = content + '<div class="card"><div class="card-body"><h4 class="card-title">' + psicName + ', ' + id + '</h4><p class="card-text">Employer: ' + employerId + '</p></div></div><br>';
            }

            divShowPsic.innerHTML = content;
        }else{
            alert("¡Vaya! No se ha podido resolver tu petición");
        }
}

// GET REQUEST - CENTROS
async function showCentros(){

        let res = await fetch("/api/v1/centros",{
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
        }});

        console.log(res); //ok

        if (res.status == 200){
            const data = await res.json();
            //console.log(data); //ok
            //let user1 = data[0]; //ok
            divShowCentros = document.getElementById("mostrarCentros");
            let content = "";
            for (let i = 0; i<data.length; i++){
                let user = data[i];
                let employerId = user["employerId"];
                let employerName = user["employerName"];
                let postalCode = user["postalCode"];
                content = content + '<div class="card"><div class="card-body"><h4 class="card-title">' + employerName + ', ' + employerId + '</h4><p class="card-text">CP: ' + postalCode + '</p></div></div><br>';
            }

            divShowCentros.innerHTML = content;
        }else{
            alert("¡Vaya! No se ha podido resolver tu petición");
        }
}

// GET REQUEST - USUARIOS QUE TIENEN UN PSICÓLOGO ASOCIADO
async function showWithPsic(){
    let res = await fetch("/api/v1/usuarios/conpsic",{
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
    }});

    if (res.status == 200){
        const data = await res.json();
        withPsic = document.getElementById("withPsic");
        let content = "";
        for (let i = 0; i<data.length; i++){
            let user = data[i];
            let id = user["id"];
            let userName = user["userName"];
            let userPwd = user["userPwd"];
            let idPsic = user["idPsic"];
            content = content + '<div class="card"><div class="card-body"><h4 class="card-title">' + userName + ', ' + id + '</h4><p class="card-text">Psicólogo: ' + idPsic + '</p></div></div><br>';
        }
        withPsic.innerHTML = content;
    }else{
        alert("¡Vaya! No se ha podido resolver tu petición");
    }
}

// GET REQUEST - PSICOLOGOS ASOCIADOS A ALGUN CENTRO MEDICO
async function showPsicWithCentro(){
    let res = await fetch("/api/v1/psicologos/concentro",{
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
    }});

    if (res.status == 200){
        const data = await res.json();
        console.log(data); //ok
        //let user1 = data[0]; //ok
        withCentro = document.getElementById("withCentro");
        let content = "";
        for (let i = 0; i<data.length; i++){
            let user = data[i];
            let id = user["id"];
            let psicName = user["psicName"];
            let psicPwd = user["psicPwd"];
            let employerId = user["employerId"];
            content = content + '<div class="card"><div class="card-body"><h4 class="card-title">' + psicName + ', ' + id + '</h4><p class="card-text">Employer: ' + employerId + '</p></div></div><br>';
        }

        withCentro.innerHTML = content;
    }else{
        alert("¡Vaya! No se ha podido resolver tu petición")
    }
}