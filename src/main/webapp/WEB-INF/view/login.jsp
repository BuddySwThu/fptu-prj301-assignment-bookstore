<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>
    <h1>Login</h1>
    <form action="login" method="POST">
        <table>
            <tbody>
            <tr>
                <td>Username</td>
                <td><input type="text" name="username" value=""></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" value=""></td>
            </tr>
            <tr>
                <td colspan="2">
                    <br/><input type="submit" value="Login">
                    <input type="reset" value="Reset">
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <a href="register">Click here to create new account.</a><br/>
    <%--    <a href="shop">Click here to buy book.</a>--%>
</body>
</html>
