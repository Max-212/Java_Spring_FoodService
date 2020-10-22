async function ShowAllfoods()
{
    let response = await fetch("api/foods",
    {
        method: 'GET', mode: 'no-cors',
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'}
    });
    let data = await response.json();
    data.forEach(el =>
    {
        let div = document.createElement('div');
        div.setAttribute('class', 'content__element');

        let img = document.createElement('img');
        img.src = el.image;

        let innerDiv = document.createElement('div');
        innerDiv.setAttribute('class', 'element__title');

        let h3 = document.createElement('h3');
        h3.innerHTML = el.title;

        let label = document.createElement('label');
        label.innerHTML = el.weight + 'г';

        innerDiv.appendChild(h3);
        innerDiv.appendChild(label);

        let h2 = document.createElement('h2');
        h2.innerHTML = el.price + ' BYN';

        div.appendChild(img);
        div.appendChild(innerDiv);
        div.appendChild(h2);

        document.querySelector('#content').appendChild(div);
    });
}

ShowAllfoods();
