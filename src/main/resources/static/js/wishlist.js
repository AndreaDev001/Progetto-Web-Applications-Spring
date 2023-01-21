let wishlistCards = document.querySelectorAll(".card")



function removeWishlistEntry(event, value)
{
    console.log(value);

    document.querySelector(`div[gameid="${value}"]`).delete();
}

wishlistCards.forEach((card) => {

});


