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

    thNo.innerHTML = 'No.';
    thUsername.innerHTML = 'Username';
    thPassword.innerHTML = 'Password';
    thLastName.innerHTML = 'Last Name';
    thAdmin.innerHTML = 'Role';

    tr.appendChild(thNo);
    tr.appendChild(thUsername);
    tr.appendChild(thPassword);
    tr.appendChild(thLastName);
    tr.appendChild(thAdmin);
    table.appendChild(tr);
}

function renderRegistrationRows(registrations, resultTable) {
    resultTable.innerHTML = '';
    var table = document.createElement('table');
    table.setAttribute('border', 2);
    renderHeaderRegistrationRows(table);
    for (var i = 0; i < registrations.length; i++) {
        var registration = registrations[i];
        var tr = document.createElement('tr');
        var tdNo = document.createElement('td');
        var tdUsername = document.createElement('td');
        var tdPassword = document.createElement('td');
        var tdLastname = document.createElement('td');
        var tdAdmin = document.createElement('td');

        tdNo.innerHTML = i + 1;
        tdUsername.innerHTML = registration.username;
        tdPassword.innerHTML = registration.password;
        tdLastname.innerHTML = registration.lastname;
        tdAdmin.innerHTML = registration.role ? 'Admin' : 'User';

        tr.appendChild(tdNo);
        tr.appendChild(tdUsername);
        tr.appendChild(tdPassword);
        tr.appendChild(tdLastname);
        tr.appendChild(tdAdmin);
        table.appendChild(tr);
    }
    resultTable.appendChild(table);
}

//
// function updateUser(e) {
//     var tr = e.parentNode.parentNode;
//     var username = tr.getElementsByClassName('lbl-username')[0];
//     var password = tr.getElementsByClassName('txt-password')[0];
//     var lastName = tr.getElementsByClassName('txt-lastName')[0];
//     var role = tr.getElementsByClassName('chk-role')[0];
//     var usernameVal = username.innerHTML;
//     var passwordVal = password.value;
//     var lastNameVal = lastName.value;
//     var roleVal = role.checked;
//     var param = {
//         username: usernameVal,
//         password: passwordVal,
//         lastName: lastNameVal,
//         role: roleVal
//     };
//
//     var req = new XMLHttpRequest();
//     req.open('PUT', './user/update-user', true);
//     req.setRequestHeader('Content-Type', 'application/json');
//
//     req.onreadystatechange = function () {
//         if (req.readyState === XMLHttpRequest.DONE) {
//             if (req.status === 200) {
//                 var res = JSON.parse(req.responseText);
//                 alert(res.message);
//                 removeValidError(tr);
//                 search();
//             } else if (req.status === 400) {
//                 removeValidError(tr);
//                 var error = JSON.parse(req.responseText);
//                 if (error.errors.password !== undefined) {
//                     var err = document.createElement('div');
//                     err.innerHTML = error.errors.password;
//                     err.className = 'password-error';
//                     password.parentNode.appendChild(err);
//                 }
//
//                 if (error.errors.lastName !== undefined) {
//                     var err = document.createElement('div');
//                     err.innerHTML = error.errors.lastName;
//                     err.className = 'lastName-error';
//                     lastName.parentNode.appendChild(err);
//                 }
//             } else if (req.status === 204) {
//                 alert('User is not existed !!!');
//             } else {
//                 console.log('       [ERROR]:     ' + req.responseText);
//             }
//         }
//     };
//
//     req.send(JSON.stringify(param));
// }
//
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
//
// function removeValidError(tr) {
//     var passwordError = tr.getElementsByClassName('password-error');
//     var lastNameError = tr.getElementsByClassName('lastName-error');
//
//     for (var i = 0; i < passwordError.length; i++) {
//         passwordError[i].parentNode.removeChild(passwordError[i]);
//     }
//
//     for (var i = 0; i < lastNameError.length; i++) {
//         lastNameError[i].parentNode.removeChild(lastNameError[i]);
//     }
// }
