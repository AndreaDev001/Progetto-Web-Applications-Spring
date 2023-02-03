

    const container1El = document.getElementById("container1")
    const container2El = document.getElementById("container2")
    const container3El = document.getElementById("container3")
    const container4El = document.getElementById("container4")

    function funzione(value, parent) {
    console.log(value)
    fetch(value)
    .then(response => response.json())
    .then(data => {
    data.results.forEach(element => {
    creaCard(element, parent)
        })
    })
}


    funzione(document.getElementById("api1").textContent, container1El)
    funzione(document.getElementById("api2").textContent, container2El)
    funzione(document.getElementById("api3").textContent, container3El)
    funzione(document.getElementById("api4").textContent, container4El)

    function creaCard(data, parent){
        const card = document.createElement("div")
        card.classList.add("card")
        card.setAttribute("class", "card")
        card.setAttribute("style", "width: 18rem; background-color: var(--background-secondary);")

        const imm = document.createElement("img")
        imm.setAttribute("class", "card-img-top")
        imm.setAttribute("src", data.short_screenshots[0].image)
        imm.setAttribute("alt", "Card image cap")
        card.appendChild(imm)

        const innerDiv = document.createElement("div")
        innerDiv.setAttribute("class", "card-body")
        card.appendChild(innerDiv)
        style="color: whitesmoke"

        const title = document.createElement("h5")
        title.setAttribute("class", "card-title")
        title.setAttribute("style", "color: whitesmoke")
        title.appendChild(document.createTextNode(data.name))
        innerDiv.appendChild(title)

        const date = document.createElement("p")
        date.setAttribute("class", "card-text")
        date.setAttribute("style", "color: whitesmoke")
        date.appendChild(document.createTextNode(data.released))
        innerDiv.appendChild(date)
        parent.append(card)
        card.addEventListener("click", ()=> {
            window.open("http://localhost:4200/games/" + data.id + "?" + "jsessionid=" + document.getElementById("sessionId").innerText);
        })
    }
