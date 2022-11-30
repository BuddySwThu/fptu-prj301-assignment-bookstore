<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./resources/css/main.css">
    <script src="./resources/js/register.js"></script>
    <title>Register Page</title>
</head>
<body>
    <h1>Register</h1>
    <div id="registerForm">
        <table>
            <tr>
                <td>Username</td>
                <td id="usernameRow"><input type="text" id="txtUsername"/></td>
            </tr>

            <tr>
                <td>Password</td>
                <td id="passwordRow"><input type="password" id="txtPassword"/></td>
            </tr>

            <tr>
                <td>Confirm Password</td>
                <td id="confirmPasswordRow"><input type="password" id="txtConfirmPassword"/></td>
            </tr>

            <tr>
                <td>Last name</td>
                <td id="lastNameRow"><input type="text" id="txtLastName"/></td>
            </tr>

            <tr>
                <td colspan="2">
                    <br/>
                    <button type="submit" onclick="register(function(p1,p2){return undefined;})">Register</button>
                    <input type="reset" value="Reset">
                </td>
            </tr>
        </table>
    </div>
    <a href="login">Click here to return to login</a>
</body>
</html>
