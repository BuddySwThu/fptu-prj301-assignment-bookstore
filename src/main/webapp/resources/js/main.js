function search(button) {
    let searchValue = document.getElementsByName('searchValue')[0].value;
    if (searchValue !== ' ') {
        let req = new XMLHttpRequest();
        req.open('GET', './user/search-user?searchValue=' + searchValue, true);
        req.onreadystatechange = function () {
            if (req.readyState === 4 && req.status === 200) {
                renderRegistrationRows(JSON.parse(req.responseText), document.getElementById('result'));
            }
        };
        req.send();
    }
}

function renderHeaderRegistrationRows(table) {
    let tr = document.createElement('tr');
    let thNo = document.createElement('th');
    let thUsername = document.createElement('th');
    //let thPassword = document.createElement('th');
    let thLastName = document.createElement('th');
    let thAdmin = document.createElement('th');
    let thUpdate = document.createElement('th');
    let thDelete = document.createElement('th');

    thNo.innerHTML = 'No.';
    thUsername.innerHTML = 'Username';
    //thPassword.innerHTML = 'Password';
    thLastName.innerHTML = 'Last Name';
    thAdmin.innerHTML = 'Role';
    thUpdate.innerHTML = 'Update';
    thDelete.innerHTML = 'Delete';

    tr.appendChild(thNo);
    tr.appendChild(thUsername);
    //tr.appendChild(thPassword);
    tr.appendChild(thLastName);
    tr.appendChild(thAdmin);
    tr.appendChild(thUpdate);
    tr.appendChild(thDelete);
    table.appendChild(tr);
}

function renderRegistrationRows(registrations, resultTable) {
    resultTable.innerHTML = '';
    let table = document.createElement('table');
    table.setAttribute('border', '3');
    if (registrations != null && registrations.length !== 0) {
        renderHeaderRegistrationRows(table);
        for (let i = 0; i < registrations.length; i++) {
            let registration = registrations[i];
            let tr = document.createElement('tr');
            let tdNo = document.createElement('td');
            let tdUsername = document.createElement('td');
            //let tdPassword = document.createElement('td');
            let tdLastname = document.createElement('td');
            let tdAdmin = document.createElement('td');
            let tdUpdate = document.createElement('td');
            let tdDelete = document.createElement('td');

            tdNo.innerHTML = i + 1;
            tdUsername.innerHTML = '<a class="lbl-username">' + registration.username + '</a>';
            //tdPassword.innerHTML = '<input type="text" class="txt-password" value="' + registration.password + '" />';
            tdLastname.innerHTML = '<input type="text" class="txt-lastname" value="' + registration.lastname + '" />';
            //tdAdmin.innerHTML = '<input type="checkbox" class="chk-role" ' + (registration.admin ? 'checked' : '')
            // + ' />';
            if(registration.admin) tdAdmin.innerHTML = '<a class="lbl-role">Admin</a>';
            else tdAdmin.innerHTML = '<a class="lbl-role">Member</a>';

            let btnUpdate = document.createElement('button');
            tdUpdate.appendChild(btnUpdate);
            btnUpdate.innerHTML = 'Update';
            btnUpdate.onclick = function (e) {
                updateUser(e.target);
            };

            let deleteLink = document.createElement('a');
            tdDelete.appendChild(deleteLink);
            deleteLink.setAttribute('href', '#');
            deleteLink.setAttribute('username', registration.username);
            deleteLink.innerHTML = 'X';
            deleteLink.onclick = function (e) {
                deleteUser(e.target);
            };

            tr.appendChild(tdNo);
            tr.appendChild(tdUsername);
            //tr.appendChild(tdPassword);
            tr.appendChild(tdLastname);
            tr.appendChild(tdAdmin);
            tr.appendChild(tdUpdate);
            tr.appendChild(tdDelete);
            table.appendChild(tr);
        }
        resultTable.appendChild(table);
    } else {
        let noRecordText = document.createElement('h2');
        noRecordText.innerHTML = 'No record(s) matched ! ! !';
        resultTable.appendChild(noRecordText);
    }
}

function updateUser(e) {
    var tr = e.parentNode.parentNode;
    var username = tr.getElementsByClassName('lbl-username')[0];
    var password = tr.getElementsByClassName('txt-password')[0];
    var lastname = tr.getElementsByClassName('txt-lastname')[0];
    var role = tr.getElementsByClassName('chk-role')[0];
    var usernameVal = username.innerHTML;
    var passwordVal = password.value;
    var lastnameVal = lastname.value;
    var roleVal = role.checked;

    var param = {
        username: usernameVal,
        password: passwordVal,
        lastname: lastnameVal,
        role: roleVal
    };

    var req = new XMLHttpRequest();
    req.open('PUT', './user/update-user', true);
    req.setRequestHeader('Content-Type', 'application/json');

    req.onreadystatechange = function () {
        if (req.readyState === XMLHttpRequest.DONE) {
            if (req.status === 200) {
                var res = JSON.parse(req.responseText);
                alert(res.message);
                removeValidError(tr);
                search();
            } else if (req.status === 400) {
                removeValidError(tr);
                var error = JSON.parse(req.responseText);

                if (error.errors.password !== undefined) {
                    var err = document.createElement('div');
                    err.innerHTML = error.errors.password;
                    err.className = 'password-error';
                    password.parentNode.appendChild(err);
                }

                if (error.errors.lastname !== undefined) {
                    var err1 = document.createElement('div');
                    err1.innerHTML = error.errors.lastname;
                    err1.className = 'lastname-error';
                    lastname.parentNode.appendChild(err1);
                }
            } else if (req.status === 204) {
                alert('User is not existed ! ! !');
            } else {
                console.log('        [ERROR]:  ' + req.responseText);
            }
        }
    };

    req.send(JSON.stringify(param));
}

function removeValidError(tr) {
    var passwordError = tr.getElementsByClassName('password-error');
    var lastnameError = tr.getElementsByClassName('lastname-error');
    for (var i = 0; i < passwordError.length; i++) passwordError[i].parentNode.removeChild(passwordError[i]);
    for (var o = 0; i < lastnameError.length; o++) lastnameError[o].parentNode.removeChild(lastnameError[o]);
}

function deleteUser(e) {
    var username = e.getAttribute('username');
    var req = new XMLHttpRequest();
    req.open('DELETE', './user/delete-user?username=' + username, true);

    req.onreadystatechange = function () {
        if (req.readyState === XMLHttpRequest.DONE) {
            if (req.status === 200) {
                var response = JSON.parse(req.responseText);
                alert(response.message);
                search();
            } else {
                console.log('   [ERROR]:   ' + req.responseText);
            }
        }
    };

    req.send();
}
