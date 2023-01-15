

    const container1El = document.getElementById("container1")
    const container2El = document.getElementById("container2")
    const container3El = document.getElementById("container3")

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

    function creaCard(data, parent){
        const card = document.createElement("div")
        card.classList.add("card")

        const innerDiv = document.createElement("div")
        card.appendChild(innerDiv)

        const imm = document.createElement("img")
        imm.setAttribute("src", data.short_screenshots[0].image)
        innerDiv.appendChild(imm)

        const title = document.createElement("h3")
        title.appendChild(document.createTextNode(data.name))
        innerDiv.appendChild(title)

        const rat = document.createElement("p")
        rat.appendChild(document.createTextNode(data.metacritic))
        innerDiv.appendChild(rat)

        const date = document.createElement("p")
        date.appendChild(document.createTextNode(data.released))
        innerDiv.appendChild(date)

        parent.append(card)

        card.addEventListener("click", ()=> {
            window.open(data.short_screenshots[0].image)
        })
    }
