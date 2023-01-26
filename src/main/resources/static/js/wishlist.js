let wishlistCards = document.querySelectorAll(".card")
function removeWishlistEntry(event, value)
{
    console.log(value);
    let element = document.getElementById(value);
    element.delete();
}
wishlistCards.forEach((card) => {

});


