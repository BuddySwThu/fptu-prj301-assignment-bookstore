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
                <td id="usernameRow"><label for="txtUsername"><input type="text" id="txtUsername"/></label></td>
            </tr>
            <tr>
                <td>Password</td>
                <td id="passwordRow"><label for="txtPassword"><input type="password" id="txtPassword"/></label></td>
            </tr>
            <tr>
                <td>Confirm Password</td>
                <td id="confirmPasswordRow"><label for="txtConfirmPassword"><input type="password"
                                                                                   id="txtConfirmPassword"/></label>
                </td>
            </tr>
            <tr>
                <td>Last name</td>
                <td id="lastNameRow"><label for="txtLastName"><input type="text" id="txtLastName"/></label></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit" onclick="register()">Register</button>
                    <input type="reset" value="Reset">
                </td>
            </tr>
        </table>
    </div>
    <a href="login">Click here to return to login</a>
</body>
</html>
