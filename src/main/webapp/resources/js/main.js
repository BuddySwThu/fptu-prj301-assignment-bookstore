function search(button) {
    var txtSearch = document.getElementsByName('searchValue')[0];
    var resultTable = document.getElementById('result');
    var searchValue = txtSearch.value;
    if (searchValue !== ' ') {
        var req = new XMLHttpRequest();                                           // + "&timestamp=" + new Date().getTime(), 
        req.open('GET', './user/search-user?searchValue=' + searchValue, true);
        req.onreadystatechange = function () {
            if (req.readyState === 4 && req.status === 200) {
                var userAccounts = JSON.parse(req.responseText);
                renderRegistrationRows(userAccounts, resultTable);
            }
        };
        req.send();
    }
}

function renderHeaderRegistrationRows(table) {
    var tr = document.createElement('tr');
    var thNo = document.createElement('th');
    var thUsername = document.createElement('th');
    var thPassword = document.createElement('th');
    var thLastName = document.createElement('th');
    var thAdmin = document.createElement('th');
    var thUpdate = document.createElement('th');

    thNo.innerHTML = 'No.';
    thUsername.innerHTML = 'Username';
    thPassword.innerHTML = 'Password';
    thLastName.innerHTML = 'Last Name';
    thAdmin.innerHTML = 'Role';
    thUpdate.innerHTML = 'Update';

    tr.appendChild(thNo);
    tr.appendChild(thUsername);
    tr.appendChild(thPassword);
    tr.appendChild(thLastName);
    tr.appendChild(thAdmin);
    tr.appendChild(thUpdate);
    table.appendChild(tr);
}

function renderRegistrationRows(registrations, resultTable) {
    resultTable.innerHTML = '';
    var table = document.createElement('table');
    table.setAttribute('border', 2);
    if (registrations != null && registrations.length !== 0) {
        renderHeaderRegistrationRows(table);
        for (var i = 0; i < registrations.length; i++) {
            var registration = registrations[i];
            var tr = document.createElement('tr');
            var tdNo = document.createElement('td');
            var tdUsername = document.createElement('td');
            var tdPassword = document.createElement('td');
            var tdLastname = document.createElement('td');
            var tdAdmin = document.createElement('td');
            var tdUpdate = document.createElement('td');

            tdNo.innerHTML = i + 1;
            tdUsername.innerHTML = '<a class="lbl-username">' + registration.username + '</a>';
            tdPassword.innerHTML = '<input type="text" class="txt-password" value="' + registration.password + '" />';
            tdLastname.innerHTML = '<input type="text" class="txt-lastname" value="' + registration.lastname + '" />';
            tdAdmin.innerHTML = '<input type="checkbox" class="chk-role" ' + (registration.role ? 'checked' : '') + ' />';

            var btnUpdate = document.createElement('button');
            tdUpdate.appendChild(btnUpdate);
            btnUpdate.innerHTML = 'Update';
            btnUpdate.onclick = function (e) {
                updateUser(e.target);
            };

            tr.appendChild(tdNo);
            tr.appendChild(tdUsername);
            tr.appendChild(tdPassword);
            tr.appendChild(tdLastname);
            tr.appendChild(tdAdmin);
            tr.appendChild(tdUpdate);
            table.appendChild(tr);
        }
        resultTable.appendChild(table);
    } else {
        var noRecordText = document.createElement('h2');
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
    for (var o = 0; i < lastnameError.length; o++)  lastnameError[o].parentNode.removeChild(lastnameError[o]);
}

// function deleteUser(e) {
//     var username = e.getAttribute('username');
//     var req = new XMLHttpRequest();
//     req.open('DELETE', './user/delete-user?username=' + username, true);
//
//     req.onreadystatechange = function () {
//         if (req.readyState === XMLHttpRequest.DONE) {
//             if (req.status === 200) {
//                 var response = JSON.parse(req.responseText);
//                 alert(response.message);
//                 search();
//             } else {
//                 console.log('   [ERROR]:   ' + req.responseText);
//             }
//         }
//     };
//
//     req.send();
// }
