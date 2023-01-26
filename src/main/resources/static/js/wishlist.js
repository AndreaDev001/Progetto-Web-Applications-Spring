
function removeWishlistEntry(value)
{

    $.post("http://localhost:8080/removeGameWishlistSpring", {gameID: value}
    ).done(function() {
        document.querySelector(`div[gameid="${value}"]`).remove();
        alert("Wishlist entry successfully removed");
    });

}



