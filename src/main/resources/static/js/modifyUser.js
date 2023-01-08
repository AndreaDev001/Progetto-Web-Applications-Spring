
function formValidate() {
    const password = document.getElementById("password")
    const email = document.getElementById("email")
    const form = document.getElementById("form")

    var mailformat2 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,50}$/;
    if(!password.value.match(mailformat2)){
        alert('Password must contain at least:\n'+
            '- a lowercase character,\n' +
            '- an uppercase character,\n' +
            '- a special character,\n' +
            '- a digit\n' +
            'Password can\'t contain space and must be at least 8 characters long');


        form.setAttribute("action", "openModifyUser")
    }


    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if(!email.value.match(mailformat)){
        alert('Invalid email');


        form.setAttribute("action", "openModifyUser")
    }


}



function alertDelete() {
    alert("Sei sicuro di voler eliminare questo account?");
}