async function Register()
{
    let login = document.querySelector("#login").value;
    let email = document.querySelector("#email").value;
    let phone = document.querySelector("#phone").value;
    let password = document.querySelector("#password").value;

    if(password !== document.querySelector("#submitPassword").value)
    {
        document.querySelector("#submitPassword").className = "error";
        return;
    }
    
    let response = await fetch("/api/users/",
    {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        body: JSON.stringify({
            login: login,
            email: email,
            phone: phone,
            password: password
            })
    });

    let data = await response.json();

    document.querySelector("#result").innerHTML = "";
    if(response.status === 201)
    {
        let div = document.createElement('div');
        div.setAttribute('class', 'log-reg__result__element');
        div.style.backgroundColor = "#ABFFCD";
        div.innerHTML = "You are successfully registered";
        document.querySelector("#result").appendChild(div) ;
    }
    else
    {
        data.errors.forEach(err =>
        {
            document.querySelector("#" + err.field).className = "error";
            document.querySelector("#label-" + err.field).innerHTML = err.message;
        });

    }
}

document.querySelector('#login').onfocus = RemoveClassError;
document.querySelector('#email').onfocus = RemoveClassError;
document.querySelector('#phone').onfocus = RemoveClassError;
document.querySelector('#password').onfocus = RemoveClassError;
document.querySelector('#submitPassword').onfocus = RemoveClassError;

function RemoveClassError()
{
    this.previousSibling.previousSibling.innerHTML = "";
    // удаляем индикатор ошибки, т.к. пользователь хочет ввести данные заново
    this.classList.remove('error');
}