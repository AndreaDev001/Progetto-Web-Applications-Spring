

$(document).ready(() => {

    const togglePassword = document.getElementById('togglePassword');
    const password = document.getElementById('password');
    console.log(togglePassword);
    console.log(password);
    togglePassword.addEventListener('click', function(e) {
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        this.className = this.className === "far fa-eye-slash align-middle pt-2" ? "far fa-eye align-middle pt-2" : "far fa-eye-slash align-middle pt-2";
    })
});





