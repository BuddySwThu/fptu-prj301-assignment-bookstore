function register(e) {
    var username = document.getElementById("txtUsername").value;
    var password = document.getElementById("txtPassword").value;
    var confirmPassword = document.getElementById("txtConfirmPassword").value;
    var lastname = document.getElementById("txtLastName").value;

    var formField = {
        username: username,
        password: password,
        confirmPassword: confirmPassword,
        lastname: lastname
    };

    var req = new XMLHttpRequest();
    req.open('POST', './user/create-user', true);
    req.setRequestHeader('Content-Type', 'application/json');

    req.onreadystatechange = function () {
        if (req.readyState === XMLHttpRequest.DONE) {
            if (req.status === 200) {
                removeError();
                location.replace("./");
            } else if (req.status === 400) {
                var error = JSON.parse(req.responseText);
                removeError();
                displayRegisterError(error);
            } else {
                console.log('   [ERROR]:   ' + req.responseText);
            }
        }
    };

    req.send(JSON.stringify(formField));
}

function displayRegisterError(error) {
    var usernameRow = document.getElementById("usernameRow");
    var passwordRow = document.getElementById("passwordRow");
    var confirmPasswordRow = document.getElementById("confirmPasswordRow");
    var lastNameRow = document.getElementById("lastNameRow");

    if (error.errors.username !== undefined) {
        let err = document.createElement('div');
        err.innerHTML = error.errors.username;
        err.className = 'error';
        usernameRow.parentNode.appendChild(err);
    }

    if (error.errors.UsernameExisted !== undefined) {
        let err = document.createElement('div');
        err.innerHTML = error.errors.UsernameExisted;
        err.className = 'error';
        usernameRow.parentNode.appendChild(err);
    }

    if (error.errors.password !== undefined) {
        let err = document.createElement('div');
        err.innerHTML = error.errors.password;
        err.className = 'error';
        passwordRow.parentNode.appendChild(err);
    }

    if (error.errors.ConfirmPasswordNotMatch !== undefined) {
        let err = document.createElement('div');
        err.innerHTML = error.errors.ConfirmPasswordNotMatch;
        err.className = 'error';
        confirmPasswordRow.parentNode.appendChild(err);
    }

    if (error.errors.lastname !== undefined) {
        let err = document.createElement('div');
        err.innerHTML = error.errors.lastname;
        err.className = 'error';
        lastNameRow.parentNode.appendChild(err);
    }
}

function removeError() {
    var errorElements = document.getElementsByClassName('error');
    while (errorElements.length > 0) {
        errorElements[0].parentNode.removeChild(errorElements[0]);
    }
}
