<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register Using Spring Taglib</title>
    <link rel="stylesheet" href="./resources/css/main.css">
</head>
<body>
    <h1>Register</h1>
    <form:form method="POST" action="registerTaglib_Action" modelAttribute="user">
        <table>
            <tbody>
            <tr>
                <td><form:label path="username">Username</form:label></td>
                <td><form:input path="username"/></td>
                <td><form:errors path="username" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="password">Password</form:label></td>
                <td><form:password path="password"/></td>
                <td><form:errors path="password" cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="confirmPassword">Confirm Password</form:label></td>
                <td><form:password path="confirmPassword"/></td>
                <td><form:errors cssClass="error"/></td>
            </tr>
            <tr>
                <td><form:label path="lastname">Last name</form:label></td>
                <td><form:input path="lastname"/></td>
                <td><form:errors path="lastname" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <br/><input type="submit" value="Register"/>
                    <input type="reset" value="Reset"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form:form>
    <a href="login" style="color: coral">Go back to login page.</a>
</body>
</html>
