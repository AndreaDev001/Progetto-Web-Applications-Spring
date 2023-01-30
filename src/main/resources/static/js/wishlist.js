$(document).ready(() => updateValues());
function updateValues()
{
    let card = document.getElementsByClassName("card");
    let missingGameDiv = document.getElementById("noGameDiv");
    let gameHolder = document.getElementById("gameHolder");
    missingGameDiv.style.display = card.length > 0 ? "none" : "block";
    gameHolder.style.display = card.length > 0 ? "flex" : "none";
    console.log(missingGameDiv);
}
function removeWishlistEntry(value)
{
    $.post("http://localhost:8080/removeGameWishlistSpring", {gameID: value}
    ).done(function() {
        document.querySelector(`div[gameid="${value}"]`).remove();
        alert("Wishlist entry successfully removed");
        updateValues();
    });

}



