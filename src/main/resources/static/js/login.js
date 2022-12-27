/*
const form = document.getElementById('loginForm');
const usernameField = document.getElementById('username');
const passwordField = document.getElementById('password');


form.addEventListener('submit', e => {
    if (!validateInputs())
        e.preventDefault();
});

const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');
    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success');
}

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');
    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');

}

function validateInputs() {
    const username = usernameField.value.trim();
    const password = passwordField.value.trim();

    if (username === '' || password == '') {
        // validating username
        if (username === '') {
            setError(usernameField, 'Username obbligatorio');
        }
        else {
            setSuccess(usernameField);
        }

        // validating password
        if (password === '') {
            setError(passwordField, 'Password obbligatoria');
        }
        else {
            setSuccess(passwordField);
        }

        return false;
    }

    return true;
}

 */